package strings;

import java.util.Scanner;

/**
 *
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 *
 * Examples :
 *
 * Input : X = "GeeksforGeeks", y = "GeeksQuiz"
 * Output : 5
 * The longest common substring is "Geeks" and is of
 * length 5.
 *
 * Input : X = "abcdxyz", y = "xyzabcd"
 * Output : 4
 * The longest common substring is "abcd" and is of
 * length 4.
 *
 * Input : X = "zxabcdezy", y = "yzabcdezx"
 * Output : 6
 * The longest common substring is "abcdez" and is of
 * length 6.
 *
 * Created with ♥ by georgeplaton on 22.04.18.
 */
public class LongestCommonSubstring {

    /**
     * We will have to find the longest common substring.
     * In this case a substring means, it needs to have consecutive characters.
     *
     * @param s1    - Must not be null
     * @param s2    - Must not be null
     * @return
     */
    static int commonSubstring(String s1, String s2) {

        int s1Length = s1.length();
        int s2Length = s2.length();
        int result = 0;

        int[][] k = new int[s1Length + 1][s2Length + 1];    // a mapping from characters of s1 to characters of s2, containing the longest common subsequence up to that characters intersection.

        for(int i = 0; i <= s1Length; i++) {
            for(int j = 0; j <= s2Length; j++) {

                if(i == 0 || j == 0)    // It's important to consider here the "OR" operator, not "AND", in all dynamic programming problems! <3
                    k[i][j] = 0;

                else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {     // here we have to calculate the previous elements going backwards! remember the formula above, it's not about the current elements.
                    k[i][j] = k[i - 1][j - 1] + 1;
                    result = Math.max(result, k[i][j]);
                } else {
                    k[i][j] = 0; // if they are not equal
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int result = commonSubstring(s1, s2);
        System.out.println(result);
    }
}
