import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together  dollars for ice cream. On any given day, the parlor offers a line of  flavors. Each flavor, , is numbered sequentially with a unique ID number from  to and has a cost, , associated with it.
 *
 * Given the value of  and the cost of each flavor for  trips to the Ice Cream Parlor, help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money during each visit. For each trip to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line. You must print the smaller ID first and the larger ID second.
 *
 * Note: Two ice creams having unique IDs  and  may have the same cost (i.e., ).
 *
 * Input Format
 *
 * The first line contains an integer, , denoting the number of trips to the ice cream parlor. The  subsequent lines describe all of Sunny and Johnny's trips to the parlor; each trip is described as follows:
 *
 * The first line contains .
 * The second line contains .
 * The third line contains  space-separated integers denoting the cost of each respective flavor. The  integer corresponds to the cost, , for the ice cream with ID number  (where ).
 * Constraints
 *
 * , where
 * It is guaranteed that there will always be a unique solution.
 * Output Format
 *
 * Print two space-separated integers denoting the respective ID numbers for the two distinct flavors they choose to purchase, where the smaller ID is printed first and the larger ID is printed second. Recall that each ice cream flavor has a unique ID number in the inclusive range from  to .
 *
 * Sample Input
 *
 * 2
 * 4
 * 5
 * 1 4 5 3 2
 * 4
 * 4
 * 2 2 4 3
 * Sample Output
 *
 * 1 4
 * 1 2
 * Explanation
 *
 * Sunny and Johnny make the following two trips to the parlor:
 *
 * The first time, they pool together  dollars. There are five flavors available that day and flavors  and  have a total cost of . Thus, we print 1 4 on a new line.
 * The second time, they pool together  dollars. There are four flavors available that day and flavors  and  have a total cost of . Thus, we print 1 2 on a new line.
 * Medium
 * Submitted 18313 times
 * Max Score 35
 * Need Help?
 *
 * View Discussions
 * View Editorial Solution
 * View Top Submissions
 * RATE THIS CHALLENGE
 *
 * Resources
 *
 * YouTube connection issue.6:22
 * Binary Search
 * Download problem statement
 * Download sample test cases
 * Suggest Edits
 *
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class SumOf2Numbers {

    static int indexOf(int[] array, int value, int excludeThis) {

        for(int i = 0; i <  array.length; i++) {
            if(array[i] == value && i != excludeThis)
                return i;
        }

        return -1;
    }

    /**
     *
     * @param array
     * @param money
     */
    static void solveUsingBinarySearch(int[] array, int money) {
        int[] arraySorted = array.clone();
        Arrays.sort(arraySorted);

        // The idea is that we go through the array
        // and search the complement in the cloned sorted array.
        // If we find it, then we try to get it's index.
        // it's important that the found element, is not the one we already have.

        for(int i = 0; i < arraySorted.length; i++) {
            int complement = money - arraySorted[i];
            int position = Arrays.binarySearch(arraySorted, i + 1, arraySorted.length, complement); // doing N searches.

            if(position >= 0 && position < arraySorted.length && complement == arraySorted[position]) {
                // find the indexes now.
                int indexCrtElement = indexOf(array, arraySorted[i], -1) + 1;
                int indexOtherElement = indexOf(array, complement, indexCrtElement - 1) + 1;

                System.out.println(Math.min(indexCrtElement, indexOtherElement) + " " + Math.max(indexCrtElement, indexOtherElement));
            }
        }

    }

    /**
     * We will try to find if 2 numbers sum up to a total amount.
     * We will use a HashMap, to see if the other number (total - selectedNumber), already exists in the HashMap. If yes, then we return their index.
     * In case we need to find more than 2 numbers which would sum up to that specific number, then the problem is a bit more complex.
     *
     * @param arr       - Array of costs
     * @param money     - The total money available
     */
    static void solveUsingHashTables(int[] arr, int money) {
        // maps cost to index - the problem can be that 2 elements will have the same cost.
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < arr.length; i++) {
            int crt = arr[i];
            int complement = money - crt;

            if(map.containsKey(complement)) {
                if(crt < complement) // printing them in the right order, the small one has to be first
                    System.out.println((i + 1) + " " + (map.get(complement) + 1));
                else
                    System.out.println((map.get(complement) + 1) + " " + (i + 1));
            } else {
                map.put(crt, i);
            }
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            //solveUsingHashTables(arr, money);
            solveUsingBinarySearch(arr, money);
        }
        in.close();
    }
}
