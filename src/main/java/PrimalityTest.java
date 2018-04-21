import java.util.Scanner;

/**
 * A prime is a natural number greater than  that has no positive divisors other than  and itself. Given  integers, determine the primality of each integer and print whether it is Prime or Not prime on a new line.
 *
 * Note: If possible, try to come up with an  primality algorithm, or see what sort of optimizations you can come up with for an  algorithm. Be sure to check out the Editorial after submitting your code!
 *
 * Input Format
 *
 * The first line contains an integer, , denoting the number of integers to check for primality.
 * Each of the  subsequent lines contains an integer, , you must test for primality.
 *
 * Constraints
 *
 * Output Format
 *
 * For each integer, print whether  is Prime or Not prime on a new line.
 *
 * Sample Input
 *
 * 3
 * 12
 * 5
 * 7
 * Sample Output
 *
 * Not prime
 * Prime
 * Prime
 * Explanation
 *
 * We check the following  integers for primality:
 *
 *  is divisible by numbers other than  and itself (i.e.: , , ), so we print Not prime on a new line.
 *  is only divisible  and itself, so we print Prime on a new line.
 *  is only divisible  and itself, so we print Prime on a new line.
 *
 *  Please check https://en.wikipedia.org/wiki/Primality_test as well
 *
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class PrimalityTest {

    /* Iterative Function to calculate
    // (a^n)%p in O(logy) */

    /**
     * Iterative function to calculate (a^n)%p in O(logy)
     * @param a     - must not be null
     * @param n     - must not be null
     * @param p     - must not be null
     * @return
     */
    static int power(int a, int n, int p)
    {
        // Initialize result
        int res = 1;

        // Update 'a' if 'a' >= p
        a = a % p;

        while (n > 0)
        {
            // If n is odd, multiply 'a' with result
            if ((n & 1) == 1)
                res = (res * a) % p;

            // n must be even now
            n = n >> 1; // n = n/2
            a = (a * a) % p;
        }
        return res;
    }

    /**
     * If n is prime, then always returns true,
     * If n is composite than returns false with high probability Higher value of k increases
     * probability of correct result.
     * Note that the above method may fail even if we increase number of iterations (higher k).
     * There exist sum composite numbers with the property that for every a < n, an-1 ≡ 1 (mod n).
     * Such numbers are called Carmichael numbers. Fermat’s primality test is often used if a rapid
     * method is needed for filtering, for example in key generation phase of the RSA public key cryptographic algorithm.
     *
     * Time :  O(k Log n)
     * Space : O(1)
     *
     * @param n     - must not be null
     * @param k     - the probability factor, for large numbers it has to increase as well
     * @return
     */
    static boolean isPrimeByFermatProbabilisticMethod(int n, int k)
    {
        // Corner cases
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;

        // Try k times
        while (k > 0)
        {
            // Pick a random number in [2..n-2]
            // Above corner cases make sure that n > 4
            int a = 2 + (int)(Math.random() % (n - 4));

            // Fermat's little theorem
            if (power(a, n - 1, n) != 1)
                return false;

            k--;
        }

        return true;
    }

    /**
     * We can do following optimizations:
     *
     * Instead of checking till n, we can check till √n because a larger factor of n must be a multiple of smaller factor that has been already checked.
     * The algorithm can be improved further by observing that all primes are of the form 6k ± 1, with the exception of 2 and 3. This is because all integers can be expressed as (6k + i) for some integer k and for i = ?1, 0, 1, 2, 3, or 4; 2 divides (6k + 0), (6k + 2), (6k + 4); and 3 divides (6k + 3). So a more efficient method is to test if n is divisible by 2 or 3, then to check through all the numbers of form 6k ± 1. (Source: wikipedia)
     * Below is the implementation of this solution.
     *
     *  Time :  O(nlogn)
     *  Space : O(1)
     *
     * @param n     - must not be negative
     * @return
     */
    static boolean isPrimeBy6kMethod(int n) {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;


        // we will go through all the numbers up until square of n
        // lets start at 5, since the numbers below 5 have already been tested above.
        for(int i = 5; i * i <= n; i = i + 6) {
            if(n % i == 0 || n % (i + 2) == 0)
                return false;
        }

        return true;
    }

    /**
     * The naive method will go through all the numbers from 2 to n-1, and check if it divides by n.
     * If we find any number that divides, we return false.
     *
     * Time :  O(n)
     * Space : O(1)
     * @param n - must not be negative.
     * @return
     */
    static boolean isPrimeNaiveMethod(int n)
    {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // Driver Program
    public static void main(String args[])
    {

        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            if(isPrimeBy6kMethod(n))
                System.out.println("Prime");
            else
                System.out.println("Not prime");
        }
    }
}
