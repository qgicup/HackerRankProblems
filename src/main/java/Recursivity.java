import java.util.Arrays;
import java.util.Scanner;

/**
 * Davis has  staircases in his house and he likes to climb each staircase , , or  steps at a time. Being a very precocious child, he wonders how many ways there are to reach the top of the staircase.
 *
 * Given the respective heights for each of the  staircases in his house, find and print the number of ways he can climb each staircase on a new line.
 *
 * Input Format
 *
 * The first line contains a single integer, , denoting the number of staircases in his house.
 * Each line  of the  subsequent lines contains a single integer, , denoting the height of staircase .
 *
 * Constraints
 *
 * Subtasks
 *
 *  for  of the maximum score.
 * Output Format
 *
 * For each staircase, print the number of ways Davis can climb it in a new line.
 *
 * Sample Input
 *
 * 3
 * 1
 * 3
 * 7
 * Sample Output
 *
 * 1
 * 4
 * 44
 * Explanation
 *
 * Let's calculate the number of ways of climbing the first two of the Davis'  staircases:
 *
 * The first staircase only has  step, so there is only one way for him to climb it (i.e., by jumping  step). Thus, we print  on a new line.
 * The second staircase has  steps and he can climb it in any of the four following ways:
 * Thus, we print  on a new line.
 *
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class Recursivity {

    private static final int[] STEPS = {1, 2, 3};

    /**
     * This function is supposed to find all the ways we can reach n, by using steps of 1, 2, 3.
     * In order to do this, we can use :
     *  1) Recursive back-tracking :  we will start with one, and backtrack for all other options 1, 2, 3, until we've found all the combinations
     *  2) Dynamic programming : calculating n(n-1)(n-2)(n-3)
     *
     *
     *  1) Optimal Substructure:
     * To consider all subsets of items, there can be two cases for every item: (1) the item is included in the optimal subset, (2) not included in the optimal set.
     * Therefore, the maximum value that can be obtained from n items is max of following two values.
     * 1) Maximum value obtained by n-1 items and W weight (excluding nth item).
     * 2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).
     *
     * If weight of nth item is greater than W, then the nth item cannot be included and case 1 is the only possibility.
     *
     * 2) Overlapping Subproblems
     * Following is recursive implementation that simply follows the recursive structure mentioned above.
     *
     * @param n
     */
    static int findWaysToClimb(int n) {
        // we gonna try to take as many one's and two's and 3's as possible
        // basically, the kid can jump either 1, 2 or 3 steps
        // we want to calculate all the possible ways for the kid to jump
        // from that point of view, we can go recursively, by summing up if he jumps 1, 2 or 3 steps at a time.
        // we need to make the tree of all possible options to climb the stairs.
        // we have a limited number of steps, due to the stair lenght.
        // we have an many number of steps, we can take either 1, 2, 3 as many times as we want.
        // the tree will look like the following :

        /**
         *              AS WITH ALL THE DP PROBLEMS, WE WILL GO DECREASING, AS IT'S EASIER TO REACH THE BASE CASES THIS WAY.
         *              steps(n - 1) + steps (n - 2) + steps (n - 3)   E.g steps = 5.
         *     steps(4) -> recursively go to the others (steps n-2) = steps (2) = ..., steps (n - 3) = steps (1) = 1
         *  steps(3)
         * steps(2) -> recursively go to the others (steps n-2) = steps (0) = 0, steps (n - 3) = steps (-1) = 0
         * steps(1) = 1
         * steps(0) = 0 -> recursively go to the others (steps n-2) = steps (0) = 0, steps (n - 3) = steps (-1) = 0
         */

        if(n <= 0) {
            return 0;
        } else if(n <= 1) {
            return 1;
        } else {
            return findWaysToClimb(n - 1) + findWaysToClimb(n - 2) + findWaysToClimb(n - 3);
        }
    }

    static int findWaysToClimbMemo(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, 0);
        return findWaysToClimbMemo(n, memo); // we always reserve n + 1 elements, so we can return the final sum as memo[n];
    }

    static int findWaysToClimbMemo(int n, int[] memo) {
        if(n <= 0) {
            return 0;
        } else if(n <= 1) {
            return 1;
        } else if(memo[n] == 0) {
            memo[n] = findWaysToClimb(n - 1) + findWaysToClimb(n - 2) + findWaysToClimb(n - 3);
        }

        return memo[n];
    }

    static int findWaysToClimbDP(int n) {
        // in this case we don't have to map the elements to the sum, since we take the elements an infinite number of times, they are not bound!
        // like in other cases
        if(n < 0)
            return 0;
        else if(n <= 1)
            return 1;

        int[] k = new int[n + 1];
        k[0] = 1;
        k[1] = 1;
        k[2] = 2;

        for(int i = 3; i <= n ; i++) {
            k[i] = k[i - 1] + k[i - 2] + k[i - 3];
        }

        return k[n];
    }

    /**
     * Returns true if there is a subset of set[] with sum  equal to given sum. In this case, we only have to check if there is only one subset.
     * Let isSubSetSum(int set[], int n, int sum) be the function to find whether there is a subset of set[] with sum equal to sum. n is the number of elements in set[].
     *
     * The isSubsetSum problem can be divided into two subproblems
     * …a) Include the last element, recur for n = n-1, sum = sum – set[n-1] (consider last element as the crt element)
     * …b) Exclude the last element, recur for n = n-1.
     * If any of the above the above subproblems return true, then return true.
     *
     * Following is the recursive formula for isSubsetSum() problem.
     *
     * We are doing an OR, as we want to know if there is at least one case.
     * In case we would like to count the number of such sums, we will have to add the solutions.
     * In case we would like to find the max number of such subsets, we will do a max between those elements.
     * Question - how to differentiate unique subsets ?
     *
     * isSubsetSum(set, n, sum) = isSubsetSum(set, n-1, sum) ||
     *                            isSubsetSum(set, n-1, sum-set[n-1])   // In this case, we only have to guard over the total sum, not over the value of items.
     * Base Cases:
     * isSubsetSum(set, n, sum) = false, if sum > 0 and n == 0
     * isSubsetSum(set, n, sum) = true, if sum == 0
     * @param set
     * @param n
     * @param sum
     * @return
     */
    static boolean isThereOneSubsetToSumRecursive(int set[], int n, int sum)
    {
        // Base Cases
        if (sum == 0)               // empty sum is one solution
            return true;
        if (n == 0 && sum != 0)     // no elements
            return false;

        // If crt element is greater than sum, then ignore it, and recurse for other n - 1 elements.
        if (set[n - 1] > sum)
            return isThereOneSubsetToSumRecursive(set, n - 1, sum);

       /* else, check if sum can be obtained by any of the following
          (a) excluding the current element
          (b) including the current element */
        return isThereOneSubsetToSumRecursive(set, n-1, sum) ||                          // Skipping the crt element in the sum, have the same sum.
                isThereOneSubsetToSumRecursive(set, n-1, sum - set[n-1]);           // Include the crt element, by subtracting it from the sum
    }

    static boolean isThereOneSubsetToSumMemo(int set[], int n, int sum) {
        return isThereOneSubsetToSumMemo(set, n, sum, new Boolean[n + 1]); // for memoization, we always have to select one more item, so we can store the result.
    }


    /**
     * Solution which uses memoization.
     * @param set   - Could be empty
     * @param n     - the number of elements
     * @param sum   - the total sum, must not be null
     * @return
     */
    static boolean isThereOneSubsetToSumMemo(int set[], int n, int sum, Boolean[] memo)
    {
        // Base Cases
        if (sum == 0)               // empty sum is one solution
            return true;
        if (n == 0 && sum != 0)     // no elements
            return false;

        // If crt element is greater than sum, then ignore it, and recurse for other n - 1 elements.
        if (memo[n] == null) {
            if (set[n - 1] > sum)
                memo[n] = isThereOneSubsetToSumMemo(set, n - 1, sum, memo);
            else  {
                memo[n] = isThereOneSubsetToSumMemo(set, n-1, sum, memo) ||                          // Skipping the crt element in the sum, have the same sum.
                        isThereOneSubsetToSumMemo(set, n-1, sum - set[n-1], memo);           // Include the crt element, by subtracting it from the sum
            }
        }

        return memo[n];
    }

    /**
     * Returns true if there is a subset of set[] with sum  equal to given sum. In this case, we only have to check if there is only one subset.
     * Let isSubSetSum(int set[], int n, int sum) be the function to find whether there is a subset of set[] with sum equal to sum. n is the number of elements in set[].
     *
     * @param set   - Could be empty
     * @param n     - the number of elements
     * @param sum   - the total sum, must not be null
     * @return
     */
    static boolean isThereOneSubsetToSumBottomUpManner(int set[], int n, int sum)
    {
        if(sum == 0)            // empty sum is one solution
            return true;
        else if(n == 0 && sum != 0) // no elements
            return false;

        // creating an array which maps from the elements to the total possible sums.
        // we will construct it in bottom-up manner.
        boolean[][] k =  new boolean[n + 1][sum + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {

                if(i == 0 && j == 0) {
                    k[i][j] = true; // base case
                } else if(set[i - 1] > sum) {
                    k[i][j] = k[i - 1][j];
                } else {
                    k[i][j] = k[i - 1][j] || k[i - 1][j - set[i - 1]]; // max of not including and including the crt element.
                }
            }
        }

        return k[n][sum];
    }



    /**
     * This function will returns the maximum value that can be put in a knapsack of capacity W.
     * In our case, we need to find the steps that makes to the exact sum, that will be used.
     *
     * A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets.
     * Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.
     *
     * 1) Optimal Substructure:
     *      To consider all subsets of items, there can be two cases for every item (from the big subset!):
     *          - (1) the item is included in the optimal subset,
     *          - (2) not included in the optimal set.
     *
     * Therefore, the maximum value that can be obtained from n items is MAX of following two values.
     *      1) Maximum value obtained by n-1 items and W weight (excluding nth item, skipping over it, and continue to the n-1 item!).
     *      2) Include n'th item : Value of n'th item plus maximum value obtained by n - 1 items and (W minus weight of the nth item) (including nth item).
     *
     * If weight of nth item is greater than W, then the nth item cannot be included and case 1 is the only possibility.
     *
     * 2) Overlapping Subproblems
     * Following is recursive implementation that simply follows the recursive structure mentioned above.
     *
     * @param knapsackCapacity     - the knapsack weight capacity
     * @param weight               - the weight of items
     * @param value                - the value of the items
     * @param n                    - the number of elements added.
     * @return
     */
    static int knapSackRecursive(int knapsackCapacity, int weight[], int value[], int n)
    {
        // Base Case
        if (n == 0 || knapsackCapacity == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        // We always start in descending order, from n going towards 0.
        // When it's 0, we stop.
        if (weight[n - 1] > knapsackCapacity)
            return knapSackRecursive(knapsackCapacity, weight, value, n - 1);

        // Go to the next element in descending order (n - 1) and return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        else return max( value[n - 1] + knapSackRecursive(knapsackCapacity - weight[n-1], weight, value, n - 1),
                knapSackRecursive(knapsackCapacity, weight, value, n - 1)
        );
    }

    /**
     * Will calculate the knapsack maximum capacity, but by doing it in bottom-up manner.
     * @param knapsackCapacity
     * @param weight
     * @param value
     * @param n
     * @return
     */
    static int knapSackBottomUpManner(int knapsackCapacity, int weight[], int value[], int n)
    {
        // we will construct an array which maps the items to the capacity
        // this array will indicate for each item, if it's added, how much capacity will be left.
        // it will store the value, per a number of item and a specific capacity!
        // in the end we will be able to get all the values, by getting full capacity and full number of items (of course, not items might be included),
        // but that will be the final of the calculations.
        int[][] k = new int[n + 1][knapsackCapacity + 1];

        for(int i = 0; i <=  n; i++) {
            for(int j = 0; j <= knapsackCapacity; j++) {

                if(i == 0 && j == 0) {
                    k[i][j] = 0;
                }
                else if(weight[i - 1] > j) { // we will skip this item
                    k[i][j] = k[i - 1][j];
                } else {
                    k[i][j] = max(k[i - 1][j], value[i] + k[i - 1][j - weight[i]]); // max of not including and adding the value of this item, and subtracting it from the total weight (as it has been included).
                }
            }
        }

        return k[n][knapsackCapacity];
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();
        }
    }

    // PRIVATE
    // A utility function that returns maximum of two integers
    static int max(int a, int b) { return (a > b)? a : b; }
}
