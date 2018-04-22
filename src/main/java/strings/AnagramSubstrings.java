package strings;

import java.util.Scanner;

/**
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class AnagramSubstrings {

    static final int NO_OF_CHARS = 256;

    /**
     * Function to check whether two strings are anagram of each other.
     * Will run in O(n) time, where N is the string1, string2 length.
     * @param str1      - Must not be null
     * @param str2      - Must not be null
     * @return
     */
    static boolean areAnagram(String str1, String str2)
    {
        if(str1.length() != str2.length())
            return false;

        // Create two count arrays and initialize
        // all values as 0
        int[] count = new int[NO_OF_CHARS];
        int i;

        // For each character in input strings,
        // increment count in the corresponding
        // count array
        for (i = 0; i < str1.length() && i < str2.length();
             i++)
        {
            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }

        // If both strings are of different length.
        // Removing this condition will make the program
        // fail for strings like "aaca" and "aca"
        if (str1.length() != str2.length())
            return false;

        // See if there is any non-zero value in
        // count array
        for (i = 0; i < NO_OF_CHARS; i++)
            if (count[i] != 0)
                return false;
        return true;
    }

    /**
     * Will check all the substrings of S, which are anagrams of each other.
     * E.g For s = abba, anagrammatic pairs are [a,a],[ab,ba],[b,b] and [abb,bba]
     * For s = ifailuhkqq, we have anagram pairs [i,i], [q,q] and [ifa, fai]
     *
     * Few things to consider:
     *  1) Same substring length : when we're generating substrings, we always have to generate substrings of the same size
     *     otherwise, it's clear that those substrings won't be anagram's.
     *  2) Substring continous block : substrings needs to be continous block of characters.
     *
     * @param s - Must not be null
     * @return
     */
    static int sherlockAndAnagrams(String s){
        // Complete this function
        if(s == null || s.isEmpty())
            return 0;
        if(s.length() == 1)
            return 1;

        int countAnagrams = 0;

        /**
         * Few things to consider:
         *  1) Same substring length : when we're generating substrings, we always have to generate substrings of the same size
         *     otherwise, it's clear that those substrings won't be anagram's.
         *  2) Substring continous block : substrings needs to be continous block of characters.
         *
         *  Possible solution :
         *   - use recursivity
         */
        for(int subLength = 1; subLength < s.length(); subLength++) {
            // We start with substrings of length 1 until (length / 2 + 1)
            int subIndexStart = 0;

            for(int k = 0; k < s.length(); k++) {
                if(s.length() < subIndexStart + subLength) {
                    continue;
                } else {
                    String subCrt = s.substring(subIndexStart, subIndexStart + subLength);

                    // for the current substring, find all other substrings which are anagrams
                    // starting with one position ahead than the current position
                    for(int j = subIndexStart + 1; j < s.length(); j++) {
                        if(s.length()  < (j + subLength))       // making sure there are still substrings for this string length.
                            continue;
                        else {
                            String subPair = s.substring(j, j + subLength);
                            if(areAnagram(subCrt, subPair)) {
                                countAnagrams++;
                                //System.out.println("(" + subCrt + ", " + subPair + ")");
                            }
                        }
                    }

                    subIndexStart++;
                }
            }
        }

        return countAnagrams;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = sherlockAndAnagrams(s);
            System.out.println(result);
        }
    }
}
