import java.util.Scanner;

/**
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class SortingAlg {

    static long countInversions(int[] arr) {
        // Complete this function

        int temp[] = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * Will sort the array with mergesort, an algorithm which yields the solution
     * in about Onlogn always, in all cases.
     *
     * @param arr   - our array, it's not null
     * @return
     */
    static long mergeSort(int[] arr, int[] temp, int left, int right) {
        // we will need to half the array in 2, recursively merge the 2 sides and then merge them back together.

        long countSwaps = 0;

        if (right > left)
        {
            // Find the middle point
            int m = (left + right)/2;

            // Sort first and second halves
            countSwaps = mergeSort(arr, temp,  left, m);
            countSwaps += mergeSort(arr , temp,m + 1, right);

            // Merge the sorted halves
            countSwaps += mergeHalves(arr, temp, left, m + 1, right);
        }

        return countSwaps;
    }

    /**
     * Merges two subarrays of arr[].
     * First subarray is arr[left .. middle]. Second subarray is arr[middle + 1 .. right]
     *
     * How to get number of inversions in merge()?
     * In merge process, let i is used for indexing left sub-array and j for right sub-array.
     * At any step in merge(), if a[i] is greater than a[j], then there are (mid – i) inversions.
     * because left and right subarrays are sorted, so all the remaining elements in
     * left-subarray (a[i+1], a[i+2] … a[mid]) will be greater than a[j]
     *
     * @param arr       - the array
     * @param left      - the left index
     * @param middle    - the middle index
     * @param right     - the right index
     */
    static long mergeHalves(int[] arr, int[] temp, int left, int middle, int right) {
        long countSwaps = 0;

        int newArrayIndex = left;       // we cannot start from 0, but from the left which is imposed by the current recursivity trend.
        int leftIndex = left;
        int rightIndex = middle;

        while(leftIndex <= (middle - 1) && rightIndex <= right) {

            if(arr[leftIndex] <= arr[rightIndex]) {                 // left index is smaller, we will just copy the elements to it one by one.
                temp[newArrayIndex++] = arr[leftIndex++];

            } else {                                                // left index is greater than right index
                temp[newArrayIndex++] = arr[rightIndex++];
                countSwaps += (middle - leftIndex);     // in case left > right, then we have to move (middle - left) elements to the right.
            }
        }

        // At this point, maybe one array finished faster than the others. We need to copy the remaining elements.
        for(int i = leftIndex; i <= middle - 1; i++) {
            temp[newArrayIndex++] = arr[i];
        }

        for(int j = rightIndex; j <= right; j++) {
            temp[newArrayIndex++] = arr[j];
        }

        for (int i = left; i <= right; i++)
            arr[i] = temp[i];

        return countSwaps;
    }

    /**
     * Simple algorithm that uses quick sort to sort an array.
     * It uses an internal pivot to choose the elements around him, to be sorted
     * @param arr
     */
    static void quickSort(int[] arr){

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            long result = countInversions(arr);
            System.out.println(result);
        }
        in.close();
    }
}
