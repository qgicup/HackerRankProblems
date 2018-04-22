package strings;

import java.util.Scanner;

/**
 * A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string. Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?
 *
 * For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be formed by eliminating either the D or C from both strings. Note that we will not consider ABCD as a common child because we can't rearrange characters and ABCD  ABDC.
 *
 * Input Format
 *
 * There is one line with two space-separated strings,  and .
 *
 * Constraints
 *
 * All characters are upper case in the range ascii[A-Z].
 * Output Format
 *
 * Print the length of the longest string , such that  is a child of both  and .
 *
 * Sample Input
 *
 * HARRY
 * SALLY
 * Sample Output
 *
 *  2
 * Explanation
 *
 * The longest string that can be formed by deleting zero or more characters from  and  is , whose length is 2.
 *
 * Sample Input 1
 *
 * AA
 * BB
 * Sample Output 1
 *
 * 0
 * Explanation 1
 *
 *  and  have no characters in common and hence the output is 0.
 *
 * Sample Input 2
 *
 * SHINCHAN
 * NOHARAAA
 * Sample Output 2
 *
 * 3
 * Explanation 2
 *
 * The longest string that can be formed between  and  while maintaining the order is .
 *
 * Sample Input 3
 *
 * ABCDEF
 * FBDAMN
 * Sample Output 3
 *
 * 2
 * Explanation 3
 *  is the longest child of the given strings.
 *
 *  -----------------
 *
 *  Wikipedia explanation.
 *
 *  The longest common subsequence (LCS) problem is the problem of finding the longest subsequence common to all sequences in a set of sequences (often just two sequences). It differs from the longest common substring problem: unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences. The longest common subsequence problem is a classic computer science problem, the basis of data comparison programs such as the diff utility, and has applications in bioinformatics.
 *  It is also widely used by revision control systems such as Git for reconciling multiple changes made to a revision-controlled collection of files.
 *
 * The LCS problem has an optimal substructure: the problem can be broken down into smaller, simple "subproblems", which can be broken down into yet simpler subproblems, and so on, until, finally, the solution becomes trivial. The LCS problem also has overlapping subproblems: the solution to high-level subproblems often reuse lower level subproblems. Problems with these two properties—optimal substructure and overlapping subproblems—can be approached by a problem-solving technique called dynamic programming, in which subproblem solutions are memoized rather than computed over and over. The procedure requires memoization—saving the solutions to one level of subproblem in a table (analogous to writing them to a memo, hence the name) so that the solutions are available to the next level of subproblems. This method is illustrated here.
 *
 * Prefixes
 * The subproblems become simpler as the sequences become shorter. Shorter sequences are conveniently described using the term prefix. A prefix of a sequence is the sequence with the end cut off. Let S be the sequence (AGCA). Then, the sequence (AG) is one of the prefixes of S. Prefixes are denoted with the name of the sequence, followed by a subscript to indicate how many characters the prefix contains.[4] The prefix (AG) is denoted S2, since it contains the first 2 elements of S. The possible prefixes of S are
 *
 * S1 = (A)
 * S2 = (AG)
 * S3 = (AGC)
 * S4 = (AGCA).
 * The solution to the LCS problem for two arbitrary sequences, X and Y, amounts to constructing some function, LCS(X, Y), that gives the longest subsequences common to X and Y. That function relies on the following two properties.
 *
 * First property
 * Suppose that two sequences both end in the same element. To find their LCS, shorten each sequence by removing the last element, find the LCS of the shortened sequences, and to that LCS append the removed element.
 *
 * For example, here are two sequences having the same last element: (BANANA) and (ATANA).
 * Remove the same last element. Repeat the procedure until you find no common last element. The removed sequence will be (ANA).
 * The sequences now under consideration: (BAN) and (AT)
 * The LCS of these last two sequences is, by inspection, (A).
 * Append the removed element, (ANA), giving (AANA), which, by inspection, is the LCS of the original sequences.
 * In general, for any sequences X and Y of length n and m, if we denote their elements x1 to xn and y1 to ym and their prefixes X1 to Xn-1 and Y1 to Ym-1, then we can say this:
 *
 * If: xn=ym
 * then: LCS(Xn, Ym) = LCS( Xn-1, Ym-1) ^ xn
 * where the caret ^ indicates that the following element, xn, is appended to the sequence. Note that the LCS for Xn and Ym involves determining the LCS of the shorter sequences, Xn-1 and Ym-1.
 *
 * Second property
 * Suppose that the two sequences X and Y do not end in the same symbol. Then the LCS of X and Y is the longer of the two sequences LCS(Xn,Ym-1) and LCS(Xn-1,Ym).
 *
 * To understand this property, consider the two following sequences :
 *
 * sequence X: ABCDEFG (n elements)
 * sequence Y: BCDGK (m elements)
 *
 * The LCS of these two sequences either ends with a G (the last element of sequence X) or does not.
 *
 * Case 1: the LCS ends with a G
 * Then it cannot end with a K. Thus it does not hurt to remove the K from sequence Y: if K were in the LCS, it would be its last character; as a consequence K is not in the LCS. We can then write: LCS(Xn,Ym) = LCS(Xn, Ym-1).
 *
 * Case 2: the LCS does not end with a G
 * Then it does not hurt to remove the G from the sequence X (for the same reason as above). And then we can write: LCS(Xn,Ym) = LCS(Xn-1, Ym).
 *
 * In any case, the LCS we are looking for is one of LCS(Xn, Ym-1) or LCS(Xn-1, Ym). Those two last LCS are both common subsequences to X and Y. LCS(X,Y) is the longest. Thus its value is the longest sequence of LCS(Xn, Ym-1) and LCS(Xn-1, Ym).
 *
 * LCS function defined
 * Let two sequences be defined as follows: X = (x1, x2...xm) and Y = (y1, y2...yn). The prefixes of X are X1, 2,...m; the prefixes of Y are Y1, 2,...n. Let LCS(Xi, Yj) represent the set of longest common subsequence of prefixes Xi and Yj. This set of sequences is given by the following.
 *
 * {\displaystyle LCS\left(X_{i},Y_{j}\right)={\begin{cases}\emptyset &{\mbox{ if }}\ i=0{\mbox{ or }}j=0\\{\textrm {}}LCS\left(X_{i-1},Y_{j-1}\right)\frown x_{i}&{\mbox{ if }}x_{i}=y_{j}\\{\mbox{longest}}\left(LCS\left(X_{i},Y_{j-1}\right),LCS\left(X_{i-1},Y_{j}\right)\right)&{\mbox{ if }}x_{i}\neq y_{j}\\\end{cases}}} LCS\left(X_{i},Y_{j}\right)={\begin{cases}\emptyset &{\mbox{ if }}\ i=0{\mbox{ or }}j=0\\{\textrm {}}LCS\left(X_{i-1},Y_{j-1}\right)\frown x_{i}&{\mbox{ if }}x_{i}=y_{j}\\{\mbox{longest}}\left(LCS\left(X_{i},Y_{j-1}\right),LCS\left(X_{i-1},Y_{j}\right)\right)&{\mbox{ if }}x_{i}\neq y_{j}\\\end{cases}}
 * To find the longest subsequences common to Xi and Yj, compare the elements xi and yj. If they are equal, then the sequence LCS(Xi-1, Yj-1) is extended by that element, xi. If they are not equal, then the longer of the two sequences, LCS(Xi, Yj-1), and LCS(Xi-1, Yj), is retained. (If they are both the same length, but not identical, then both are retained.) Notice that the subscripts are reduced by 1 in these formulas. That can result in a subscript of 0. Since the sequence elements are defined to start at 1, it was necessary to add the requirement that the LCS is empty when a subscript is zero.
 * Created with ♥ by georgeplaton on 22.04.18.
 */
public class LongestCommonSubsequenceProblem {

    /**
     * We need to find the longest child that can be constructed by eliminating characters from one string or the other.
     *
     * E.g HARRY, SALLY  -> AY
     *
     * Important constraints :
     *  - you need to maintain the order of elements, they need to be in the right order as substrings
     *
     * Possible solution :
     *  - One possible solution would be to use dynamic programming, in order to find the pairs.
     *
     * @param s1
     * @param s2
     * @return
     */
    static int commonChild(String s1, String s2){
        /**
         * We will use dynamic programming.
         * A common subsequence means, that characters do not have to be consecutive. A common substring means that characters have to be consecutive.
         * The function which we have to find is the following :
         *
         * Let it be s1 and s2 two strings, and i the index of s1 and j the index of s2.
         *
         * LCS(s1, s2) = { 0,                                                   in case i OR j == 0 } // IMPORTANT to consider here "OR" operator, not AND.
         *               { LCS(s1[i-1], s2[j-1]) + s[i]                         in case i == j     }
         *               { max(LCS(s1[i-1], s2[j]), LCS(s1[i], s2[j-1]))        in case i != j     }  // one element gets removed either from s1 or s2
         */

        int s1Length = s1.length();
        int s2Length = s2.length();

        int[][] k = new int[s1Length + 1][s2Length + 1];    // a mapping from characters of s1 to characters of s2, containing the longest common subsequence up to that characters intersection.

        for(int i = 0; i <= s1Length; i++) {
            for(int j = 0; j <= s2Length; j++) {

                if(i == 0 || j == 0)    // It's important to consider here the "OR" operator, not "AND", in all dynamic programming problems! <3
                    k[i][j] = 0;

                else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {     // here we have to calculate the previous elements going backwards! remember the formula above, it's not about the current elements.
                    k[i][j] = k[i - 1][j - 1] + 1;
                } else {
                    k[i][j] = Math.max(k[i][j - 1], k[i - 1][j]);
                }
            }
        }

        return k[s1Length][s2Length];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int result = commonChild(s1, s2);
        System.out.println(result);
    }
}
