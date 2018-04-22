package dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given a number of dollars, , and a list of dollar values for  distinct coins, , find and print the number of different ways you can make change for  dollars if each coin is available in an infinite quantity.
 *
 * Hints:
 *
 * You can solve this problem recursively, but you must optimize your solution to eliminate overlapping subproblems using Dynamic Programming if you wish to pass all test cases. More specifically, think of ways to store the checked solutions and use the stored values to avoid repeatedly calculating the same values.
 * Think about the degenerate cases:
 * How many ways can you make change for  dollars?
 * How many ways can you make change for less than  dollars if you have no coins?
 * If you are having trouble defining the storage for your precomputed values, then think about it in terms of the base case .
 * Input Format
 *
 * The first line contain two space-separated integers describing the respective values of  and .
 * The second line contains  space-separated integers describing the respective values of , where each integer denotes the dollar value of a distinct coin available in an infinite quantity.
 *
 * Constraints
 *
 * The list of coins contains  distinct integers where each integer denotes the dollar value of a coin available in an infinite quantity.
 * Output Format
 *
 * Print a single integer denoting the number of ways we can make change for  dollars using an infinite supply of our  types of coins.
 *
 * Sample Input 0
 *
 * 4 3
 * 1 2 3
 * Sample Output 0
 *
 * 4
 * Explanation 0
 * For  and  there are four solutions:
 *
 * Thus, we print  on a new line.
 *
 * Sample Input 1
 *
 * 10 4
 * 2 5 3 6
 * Sample Output 1
 *
 * 5
 * Explanation 1
 * For  and  there are five solutions:
 *
 * Thus, we print  on a new line.
 *
 * Created with ♥ by georgeplaton on 03.04.18.
 */
public class CoinChangeDP {

    /**
     * Will compute the number of ways the coins can be selected in a bottom-up manner.
     * Time complexity of this function: O(noCoins * totalCoinsValue)
     * Space Complexity of this function: O(n)
     * 
     * @param coinArray          - the array which holds the coins
     * @param noCoins            - the number of coins
     * @param totalCoinsValue    - the total value of the coins
     * @return
     */
    static long countWays(int coinArray[], int noCoins, int totalCoinsValue)
    {
        /**
         * Store the number of solutions for the value i. We need n+1 rows (totalCoinsValue+1),
         * as the table is constructed in bottom up manner, using the base case as starting point (totalCoinsValue = 0).
         * That base case adds the +1 to the array.
         */
        long[] table = new long[totalCoinsValue + 1];

        Arrays.fill(table, 0);   // O(n)

        // Base case (If given value is 0)
        table[0] = 1;

        /**
         * Pick all coins one by one and update the table[] values
         * after the index greater or equal to the value of the picked coin.
         */
        for (int i=0; i<noCoins; i++)
            for (int j=coinArray[i]; j<=totalCoinsValue; j++)
                table[j] += table[j-coinArray[i]];

        return table[totalCoinsValue];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalCoinsValue = in.nextInt();
        int noCoins = in.nextInt();
        int coins[] = new int[noCoins];
        for(int coins_i=0; coins_i < noCoins; coins_i++){
            coins[coins_i] = in.nextInt();
        }

        System.out.println(countWays(coins, noCoins, totalCoinsValue));
    }
}
