import java.util.Scanner;

/**
 * Consider an array of  integers, , where all but one of the integers occur in pairs. In other words, every element in  occurs exactly twice except for one unique element.
 *
 * Given , find and print the unique element.
 *
 * Input Format
 *
 * The first line contains a single integer, , denoting the number of integers in the array.
 * The second line contains  space-separated integers describing the respective values in .
 *
 * Constraints
 *
 * It is guaranteed that  is an odd number.
 * , where .
 * Output Format
 *
 * Print the unique number that occurs only once in  on a new line.
 *
 * Sample Input 0
 *
 * 1
 * 1
 * Sample Output 0
 *
 * 1
 * Explanation 0
 * The array only contains a single , so we print  as our answer.
 *
 * Sample Input 1
 *
 * 3
 * 1 1 2
 * Sample Output 1
 *
 * 2
 * Explanation 1
 * We have two 's and one . We print , because that's the only unique element in the array.
 *
 * Sample Input 2
 *
 * 5
 * 0 0 1 2 1
 * Sample Output 2
 *
 * 2
 * Explanation 2
 * We have two 's, two 's, and one . We print , because that's the only unique element in the array.
 *
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class BitManipulationLonelyInteger {

    /**
     * Will find the lonely integer in O(n) time and O(1) space
     *
     * The code works in similar line with the question of "finding the element which appears once in an array - containing other elements each appearing twice". Solution is to XOR all the elements and you get the answer.
     *
     * Basically, it makes use of the fact that x^x = 0. So all paired elements get XOR'd and vanish leaving the lonely element.
     * Since XOR operation is associative, commutative.. it does not matter in what fashion elements appear in array, we still get the answer.
     *
     * Now, in the current question - if we apply the above idea, it will not work because - we got to have every unique element appearing even number of times. So instead of getting the answer, we will end up getting XOR of all unique elements which is not what we want.
     *
     * To rectify this mistake, the code makes use of 2 variables.
     * ones - At any point of time, this variable holds XOR of all the elements which have
     * appeared "only" once.
     * twos - At any point of time, this variable holds XOR of all the elements which have
     * appeared "only" twice.
     *
     * So if at any point time,
     * 1. A new number appears - It gets XOR'd to the variable "ones".
     * 2. A number gets repeated(appears twice) - It is removed from "ones" and XOR'd to the
     * variable "twice".
     * 3. A number appears for the third time - It gets removed from both "ones" and "twice".
     *
     * The final answer we want is the value present in "ones" - coz, it holds the unique element.
     *
     * So if we explain how steps 1 to 3 happens in the code, we are done.
     * Before explaining above 3 steps, lets look at last three lines of the code,
     *
     * not_threes = ~(ones & twos)
     * ones & = not_threes
     * twos & = not_threes
     *
     * All it does is, common 1's between "ones" and "twos" are converted to zero.
     *
     * For simplicity, in all the below explanations - consider we have got only 4 elements in the array (one unique element and 3 repeated elements - in any order).
     *
     * Explanation for step 1
     * ------------------------
     * Lets say a new element(x) appears.
     * CURRENT SITUATION - Both variables - "ones" and "twos" has not recorded "x".
     *
     * Observe the statement "twos| = ones & x".
     * Since bit representation of "x" is not present in "ones", AND condition yields nothing. So "twos" does not get bit representation of "x".
     * But, in next step "ones ^= x" - "ones" ends up adding bits of "x". Thus new element gets recorded in "ones" but not in "twos".
     *
     * The last 3 lines of code as explained already, converts common 1's b/w "ones" and "twos" to zeros.
     * Since as of now, only "ones" has "x" and not "twos" - last 3 lines does nothing.
     *
     * Explanation for step 2.
     * ------------------------
     * Lets say an element(x) appears twice.
     * CURRENT SITUATION - "ones" has recorded "x" but not "twos".
     *
     * Now due to the statement, "twos| = ones & x" - "twos" ends up getting bits of x.
     * But due to the statement, "ones ^ = x" - "ones" removes "x" from its binary representation.
     *
     * Again, last 3 lines of code does nothing.
     * So ultimately, "twos" ends up getting bits of "x" and "ones" ends up losing bits of "x".
     *
     * 1st example
     * ------------
     * 2, 2, 2, 4
     *
     * After first iteration,
     * ones = 2, twos = 0
     * After second iteration,
     * ones = 0, twos = 2
     * After third iteration,
     * ones = 0, twos = 0
     * After fourth iteration,
     * ones = 4, twos = 0
     *
     * 2nd example
     * ------------
     * 4, 2, 2, 2
     *
     * After first iteration,
     * ones = 4, twos = 0
     * After second iteration,
     * ones = 6, twos = 0
     * After third iteration,
     * ones = 4, twos = 2
     * After fourth iteration,
     * ones = 4, twos = 0
     *
     * Explanation becomes much more complicated when there are more elements in the array in mixed up fashion. But again due to associativity of XOR operation - We actually end up getting answer.
     *
     * !IMPORTANT! - only one element appears once, all others appear twice.
     *
     *
     * @param array - the array of elements, can also be empty. All elements appear twice, but only one element appears only once.
     * @param n     - the length of the array
     * @return
     */
    static int findLonelyInteger(int array[], int n) {
        if(n == 1)
            return array[0];

        int ones = 0;           // will hold the elements that appear once
        int twos = 0;           // will hold the elements that appear twice
        int others;         // will hold the elements that appear more than 2 times.
        int x;

        for(int i = 0; i < n; i++) {
            x = array[i];

            // in case "ones" contains X, then the "AND" operation will succeed, hence "X" will be added to "twos" as well
            twos |= ones & x;
            // in case "ones" contains X, it will be removed, otherwise it will be added.
            ones ^= x;
            // it will collect the 1's from "ones" and "twos" and transform them into 0, due to the negation ~

            // in case elements appear twice, we need to uncomment the part below,
            // so then both "ones" and "twos" will be zero-ed out.
            /**
             * others = ~(ones & twos);
             * ones &= others;     // it will zero out the element x from ones, in case it was contained
             * twos &= others;
             */

            //System.out.println("Processing: [" + x + "]. Unique element so far :" + ones);
        }

        return ones;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        System.out.println(findLonelyInteger(a, n));
    }
}
