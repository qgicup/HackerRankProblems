package slidingwindow;

import java.util.Scanner;

/**
 * Given an array of integers of size ‘n’.
 * Our aim is to calculate the maximum sum of ‘k’
 * consecutive elements in the array.
 *
 * Input  : arr[] = {100, 200, 300, 400}
 *          k = 2
 * Output : 700
 *
 * Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
 *          k = 4
 * Output : 39
 * We get maximum sum by adding subarray {4, 2, 10, 23}
 * of size 4.
 *
 * Input  : arr[] = {2, 3}
 *          k = 3
 * Output : Invalid
 * There is no subarray of size 3 as size of whole
 * array is 2.
 *
 * Created with ♥ by georgeplaton on 22.04.18.
 */
public class FindMaxSumOfKElements {

    /**
     * It will find the largest sum of k elements from the given array s.
     * @param array     - Cannot be null or empty
     * @param k         - K elements to consider
     * @return
     */
    static int findSum(int[] array, int k) {
        if(array.length == 0)
            return 0;

        /**
         * We will use sliding window technique.
         * We keep 2 pointers, which will slide over the array, and check the maximum sum of these elements.
         */

        int maxSum = 0;
        int left = 0;
        int right = 0;
        right += k;

        // find the first sum
        for (int i = 0; i < k; i++)
            maxSum += array[i];

        while(right < array.length) {
            int localSum = maxSum - array[left] + array[right];
            if(localSum > maxSum)
                maxSum = localSum;
            left++;
            right++;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int result = findSum(a, k);
        System.out.println(result);
        in.close();
    }
}
