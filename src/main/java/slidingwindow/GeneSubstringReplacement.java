package slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * A gene is represented as a string of length  (where  is divisible by ), composed of the letters , , , and . It is considered to be steady if each of the four letters occurs exactly  times. For example,  and  are both steady genes.
 *
 * Bear Limak is a famous biotechnology scientist who specializes in modifying bear DNA to make it steady. Right now, he is examining a gene represented as a string . It is not necessarily steady. Fortunately, Limak can choose one (maybe empty) substring of  and replace it with any string of the same length.
 *
 * Modifying a large substring of bear genes can be dangerous. Given a string , can you help Limak find the length of the smallest possible substring that he can replace to make  a steady gene?
 *
 * Note: A substring of a string  is a subsequence made up of zero or more contiguous characters of .
 *
 * Input Format
 *
 * The first line contains an interger  divisible by , denoting the length of a string .
 * The second line contains a string  of length .
 *
 * Constraints
 *
 *  is divisible by
 * Subtask
 *
 *  in tests worth  points.
 * Output Format
 *
 * Print the length of the minimum length substring that can be replaced to make  stable.
 *
 * Sample Input
 *
 * 8
 * GAAATAAA
 * Sample Output
 *
 * 5
 * Explanation
 *
 * One optimal solution is to replace  with  resulting in .
 * The replaced substring has length .
 *
 * Created with ♥ by georgeplaton on 22.04.18.
 */
public class GeneSubstringReplacement {

    /**
     * This function will try to make a gene stable. In order for a gene to be stable, it needs that each 4 characters of it,
     * to occur at maximum n/4 times.
     *
     * A gene is represented as a string of length n (where n is divisible by 4), composed of the letters A, C, T, and G.
     * It is considered to be steady if each of the four letters occurs exactly n/4 times. For example, GACT and GACTGACT are both steady genes.
     *
     * E.g GAAATAAA -> 5 minimal changes. If we replace AAATA with TTCCG -> GTTCCGAA
     *
     * Facts :
     *  - we need to find n. We are supposed to replace strings, not to add or remove them, hence n is stable.
     *  - as soon as we know n, we can compute the frequency of the letters.
     *  - n/4 will tell us how many characters are missing from the gene in order to be steady.
     *
     *  We need to find the smallest substring that has to be replaced, in order to make the gene steady. Which means the characters have to be continous.
     *
     *  Possible solution :
     *
     *  1) calculate n
     *  E.g for GAAATAAA -> n = 8, -> noCharacters = 8/4 = 2;
     *
     *  2) calculate frequency
     *  freq[G = 1, A = 6, T =1, C = 0]
     *
     *  3) analyze frequency needs -> freqComplement [G = +1, A = -4, T = +1, C = +2]
     *
     *  4) Do dynamic programming from the end of the array.
     *
     *  Let s be a string, and i be it's index.
     *
     *  SCS(s) = { 0,                           if i == 0 }
     *           { SCS(s[i-1]) + 1,             if s[i-1] belongs to freqComplement  }
     *           { min(result, SCS(s[i]),       if freqComplement is empty }
     *           { 0,                           if s[i-1] does not belong to freqComplement }
     *
     *
     *  E.g GAAATAAA
     *  i = n = 7, freqComplement [G = +1, A = -4, T = +1, C = +2]  -> GTCC -> 4 max sum.
     *  Now we need to find a subsequence of 4 characters that needs to be replaced with this one (GTCC)
     *  We need to find a subsequence of length 4, with the following characters [AAAA]
     *
     *  GAAATAAA -> GAAATAAA
     *
     *
     *  This is genius, so short, so efficient code. Two points technique.
     *  The right point is sliding from 0 to n-1 of s chars,
     *  until there is steady rest of the string by the right side of the right point.
     *
     *  Then left point slides from 0 until it's steady all the string which is
     *  by the left side of the left point. On each step of the left point the
     *  length between points counted and stored the min one. Loop continues until
     *  right point reaches n-1 of s chars. Steadyness controlled using counting
     *  sort technique (https://www.hackerrank.com/challenges/countingsort1) which
     *  is implemented not with the array, but with the HashMap.
     *
     * @param gene
     * @return
     */
    static int steadyGene(String gene, int n) {

        // 1. Find the frequency of the elements
        // This map represents the current state of the string,
        // with their own frequency, if they're steady or not.
        Map<Character, Integer> freqMap = new HashMap<>();
        freqMap.put('A', 0);
        freqMap.put('C', 0);
        freqMap.put('G', 0);
        freqMap.put('T', 0);
        for (Character c : gene.toCharArray())
            freqMap.put(c, freqMap.get(c) + 1);

        // 2. Choose left and right pointers, which will denote the substring which do not have to be replaced
        int left = 0, right = 0, min = Integer.MAX_VALUE;

        // 3. Move the right pointer up until the end of the string.
        while(right < n - 1){
            char rightChar = gene.charAt(right++); // get current character


            freqMap.put(rightChar, freqMap.get(rightChar) - 1);   // substract the frequency of the current character, as we're advancing through the string

            while(isSteady(freqMap, n)) {                     // verify that after this new element, if our map is still steady
                min = Math.min(min, right - left);            // verifies the minimum string distance, for which our array is still steady

                char leftChar = gene.charAt(left++);
                freqMap.put(leftChar, freqMap.get(leftChar) + 1);   // adds the frequency of the current character from left, while the map is still steady.
            }
        }
        return min;
    }

    /**
     * Will check if the map of characters are steady, meaning all the frequencies are dividable by the given number n / 4
     * @param map   - Frequency map of elements
     * @return
     */
    private static boolean isSteady(Map<Character, Integer> map, int n) {
        for (Integer i : map.values())
            if (i > n / 4) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String gene = in.next();
        int result = steadyGene(gene, n);
        System.out.println(result);
        in.close();
    }
}
