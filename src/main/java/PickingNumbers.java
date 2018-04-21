import java.util.HashMap;
import java.util.Scanner;

/**
 * Given an array of integers, find and print the maximum number of integers you can select from the array such that the absolute difference between any two of the chosen integers is less than or equal to . For example, if your array is , you can create two subarrays meeting the criterion:  and . The maximum length subarray has  elements.
 *
 * Input Format
 *
 * The first line contains a single integer , the size of the array .
 * The second line contains  space-separated integers .
 *
 * Constraints
 *
 * The answer will be .
 * Output Format
 *
 * A single integer denoting the maximum number of integers you can choose from the array such that the absolute difference between any two of the chosen integers is .
 *
 * Sample Input 0
 *
 * 6
 * 4 6 5 3 3 1
 * Sample Output 0
 *
 * 3
 * Explanation 0
 *
 * We choose the following multiset of integers from the array: . Each pair in the multiset has an absolute difference  (i.e.,  and ), so we print the number of chosen integers, , as our answer.
 *
 * Sample Input 1
 *
 * 6
 * 1 2 2 3 1 2
 * Sample Output 1
 *
 * 5
 * Explanation 1
 *
 * We choose the following multiset of integers from the array: . Each pair in the multiset has an absolute difference  (i.e., , , and ), so we print the number of chosen integers, , as our answer.
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class PickingNumbers {

    /**
     * Will calculate if there are 2 subset elements with the maximum frequency.
     *
     * @param a     - an array of elements, unsorted
     * @return
     */
    static int picky(int[] a) {

        int k, maxk = 0, max = 0;
        int[] freq = new int[100];      // Array containing frequency of integers

        // go through the main array and fill in the frequency of each element
        // e.g freq = [_, 2, _, _ , 5, ...] -> which means that element with value [1] has frequency 2,
        // and element with value [4] has frequency 5.
        // in this way, we can iterate through the array

        // We have to be careful, to have freq array big enough, since there can be a huge number e.g 200, which will need to be positioned at the index 200.
        for(int i = 0;i < a.length; i++)
        {
            k = a[i];
            // maxk stores the value of largest integer
            if(k > maxk)
                maxk = k;
            freq[k]++;          //Update the frequency of the integer k
        }

        // As soon as we've filled this frequency array, we will go through the the items one by one
        // freq[i] + freq[i + 1] -> makes sure that the elements are one after another...
        // which means their difference is actually 1 maximum.
        // i < maxk is used in place of i < n-1
        // freq array   -> index keeps the value of the element added
        //              -> value keeps the frequency value
        // in this case the array works really nicely as a hashMap, even better, since it's sorted in the correct manner.
        // on a HashMap, you won't be able to sort the elements, since you don;t know where are the keys located.
        // However, an Array can act as a HashMap, but an !!! ordered HashMap !!! The order will be enforced by the position of the elements.
        //
        // e.g
        // HashMap -> { 1:20, 6:30, 3:70}
        //
        // Array -> [_, 20, _, 70, _, _, 30] -> the frequencies of the array, stored in the array at the position of the key value <3 <3 <3
        //           0   1  2  3   4  5   6  -> the index of the array, in this case used as substitute for the values above.
        // By using the array as a HashMap, with the key's being the indexes of the array, then we get a nicely sorted HashMap <3 <3 <3
        // which can be used in several cases. Of course, the array is not completely filled -> the key's are a bit sparse, and they must keep their index (since that represents the value)
        // but thought, you can still use them in a nicely sorted order (e.g if you iterate it in any way).
        //

        for(int i = 0; i < maxk; i++)
            if(freq[i] + freq[i + 1] > max)
                max = freq[i] + freq[i+1];


        return max;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int result = picky(a);
        System.out.println(result);
        in.close();
    }



}
