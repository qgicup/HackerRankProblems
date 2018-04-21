import java.util.*;

/**
 * We define a magic square to be an  matrix of distinct positive integers from  to  where the sum of any row, column, or diagonal of length  is always equal to the same number: the magic constant.
 *
 * You will be given a  matrix  of integers in the inclusive range . We can convert any digit  to any other digit  in the range  at cost of . Given , convert it into a magic square at minimal cost. Print this cost on a new line.
 *
 * Note: The resulting magic square must contain distinct integers in the inclusive range .
 *
 * For example, we start with the following matrix :
 *
 * 5 3 4
 * 1 5 8
 * 6 4 2
 * We can convert it to the following magic square:
 *
 * 8 3 4
 * 1 5 9
 * 6 7 2
 * This took three replacements at a cost of .
 *
 * Input Format
 *
 * Each of the lines contains three space-separated integers of row .
 *
 * Constraints
 *
 * Output Format
 *
 * Print an integer denoting the minimum cost of turning matrix  into a magic square.
 *
 * Sample Input 0
 *
 * 4 9 2
 * 3 5 7
 * 8 1 5
 * Sample Output 0
 *
 * 1
 * Explanation 0
 *
 * If we change the bottom right value, , from  to  at a cost of ,  becomes a magic square at the minimum possible cost.
 *
 * Sample Input 1
 *
 * 4 8 2
 * 4 5 7
 * 6 1 6
 * Sample Output 1
 *
 * 4
 * Explanation 1
 *
 * Using 0-based indexing, if we make
 *
 * -> at a cost of
 * -> at a cost of
 * -> at a cost of ,
 * then the total cost will be .
 *
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class MagicSquare {

    static final int NO_COLS = 3;
    static final int NO_ROWS = 3;

    /**
     * Will simply check if the given 2D array is a magic square or not.
     * @param array - Must not be null, should have the size of NO_ROWS x NO_COLS
     * @return
     */
    static boolean isMagicSquare(int[][] array)
    {
        int sum = 0;
        for (int j = 0; j < NO_COLS; ++j)
            sum += array[0][j];

        // Checking if each row sum is same
        for (int i = 1; i < NO_ROWS; ++i) {
            int tmp = 0;
            for (int j = 0; j < NO_COLS; ++j)
                tmp += array[i][j];
            if (tmp != sum)
                return false;
        }

        // Checking if each column sum is same
        for (int j = 0; j < NO_ROWS; ++j) {
            int tmp = 0;
            for (int i = 0; i < NO_COLS; ++i)
                tmp += array[i][j];
            if (tmp != sum)
                return false;
        }

        // Checking if diagonal 1 sum is same
        int tmp = 0;
        for (int i = 0; i < NO_ROWS; ++i)
            tmp += array[i][i]; // going in diagonal downwards
        if (tmp != sum)
            return false;

        // Checking if diagnol 2 sum is same
        tmp = 0;
        for (int i = 0; i < NO_ROWS; ++i)
            tmp += array[2 - i][i];  // going on the other diagonal
        if (tmp != sum)
            return false;

        return true;
    }

    // Generating all magic square

    /**
     * This is a naive approach, where we generate all magic squares and
     * we try to see how much is the difference from our own matrix.
     * The smallest difference will be computed and reported.
     */
    static List<List<Integer>> find_magic_squares(List<List<Integer>> magic_squares)
    {
        List<Integer> vec = new ArrayList<>();
        // Initialing the vector
        for (int i = 1; i < 10; ++i)
            vec.add(i);

        // Producing all permutation of vector
        // and checking if it denote the magic square or not.
        List<List<Integer>> permutations = generatePerm(vec);

        for(List<Integer> list : permutations) {
            int[][] tempArray = new int[3][3];
            // Convert vector into 3 X 3 matrix
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j)
                    tempArray[i][j] = list.get(3 * i + j);

            if (isMagicSquare(tempArray)) {

                List<Integer> result = new ArrayList<>();
                for(int i = 0;i < tempArray.length; i++)
                    for(int j = 0; j < tempArray.length; j++)
                        result.add(tempArray[i][j]);

                magic_squares.add(result);
            }

        }

        return magic_squares;
    }

    // Return sum of difference between each element of two vector
    static int diff(int[] a, int[] b)
    {
        int res = 0;

        for (int i = 0; i < 9; ++i)
            res += Math.abs(a[i] - b[i]);

        return res;
    }

    static public <E> List<List<E>> generatePerm(List<E> original) {
        if (original.size() == 0) {
            List<List<E>> result = new ArrayList<List<E>>();
            result.add(new ArrayList<E>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<List<E>>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<E>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    /**
     * The idea is to find all 3 X 3 magic squares and, for each one, compute the cost of changing mat into a known magic square.
     * The result is the smallest of these costs.
     * We know that s will always be 3 X 3. There are 8 possible magic squares for 3 X 3 matrix.
     * There are two ways to approach this:
     * - So, compute all 8 magic squares by examining all permutations of integers 1, 2, 3, ….., 9
     * and for each one, check if it forms a magic square if the permutation is inserted
     * into the square starting from the upper left hand corner.
     *
     * @param originalArray
     * @return
     */
    static int formingMagicSquare(int[] originalArray) {
        // Complete this function

        int res = Integer.MAX_VALUE;
        List<List<Integer>> magic_squares = new ArrayList<>();

        // generating all magic square
        magic_squares = find_magic_squares(magic_squares);

        for (int i = 0; i < magic_squares.size(); ++i) {

            // Finding the difference with each magic square
            // and assigning the minimum value.
            int[] arr = new int[originalArray.length];
            int index = 0;
            for(Integer elem : magic_squares.get(i))
                arr[index++] = elem;

            res = Math.min(res, diff(originalArray, arr));
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] s = new int[9];
        int i = 0;

        for(int s_i = 0; s_i < 3; s_i++){
            for(int s_j = 0; s_j < 3; s_j++) {
                s[i++] = in.nextInt();
            }
        }

        int result = formingMagicSquare(s);
        System.out.println(result);
        in.close();
    }
}
