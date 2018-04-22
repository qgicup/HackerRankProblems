package strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with ♥ by georgeplaton on 22.04.18.
 */
public class MorganAndAString {

    /**
     * Will compare A and B, and try to compare a lexigographically correct String as result.
     * @param s1     - First string
     * @param s2     - Second string
     * @return
     */
    static String morganAndString(String s1, String s2) {
        // Complete this function
        int lenS1 = s1.length(), lenS2 = s2.length();

        StringBuilder sb = new StringBuilder();
        int indexS1 = 0, indexS2 = 0;

        while (indexS1 < lenS1 && indexS2 < lenS2) {
            if (s1.charAt(indexS1) < s2.charAt(indexS2)) {
                sb.append(s1.charAt(indexS1++));
            } else if (s1.charAt(indexS1) > s2.charAt(indexS2)) {
                sb.append(s2.charAt(indexS2++));
            } else {
                if (compare(s1, indexS1 + 1, s2, indexS2 + 1)) {
                    sb.append(s1.charAt(indexS1++));
                    while (indexS1 < s1.length() && s1.charAt(indexS1) == s1.charAt(indexS1 - 1)) {
                        sb.append(s1.charAt(indexS1++));
                    }
                } else {
                    sb.append(s2.charAt(indexS2++));
                    while (indexS2 < s2.length() && s2.charAt(indexS2) == s2.charAt(indexS2 - 1)) {
                        sb.append(s2.charAt(indexS2++));
                    }
                }
            }
        }

        if (indexS1 < lenS1) {
            sb.append(s1.substring(indexS1));
        }

        if (indexS2 < lenS2) {
            sb.append(s2.substring(indexS2));
        }

        return sb.toString();
    }

    /**
     * Will compare the 2 strings, by looking in the whole array, until it finds the first elements which are smaller.
     * For example, if you have s1 = "AAADC" and s2 = "AAABC", then s1 > s2, because after you parse "AAA" sequence, you get "D" > "B".
     *
     * @param s1     - first string
     * @param s1Index     - index of first string
     * @param s2     - second string
     * @param s2Index     - index of second string
     * @return
     */
    private static boolean compare(String s1, int s1Index, String s2, int s2Index) {
        while (s1Index < s1.length() && s2Index < s2.length()) {
            if (s1.charAt(s1Index) < s2.charAt(s2Index)) return true;
            else if (s1.charAt(s1Index) > s2.charAt(s2Index)) return false;
            s1Index++;
            s2Index++;
        }

        return s1Index == s1.length() ? false : true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            String a = in.next();
            String b = in.next();
            String result = morganAndString(a, b);
            System.out.println(result);
        }
        in.close();
    }

    /**
     *
     * Correct:
     * DADADD
     *
     * ABBCBACBA
     *
     * BABABC
     *
     * DABCDAD
     *
     * YZYYZYYZYZYYZYZYY
     *
     *
     * Georges' :
     * DADDAD
     * ABBCBACBA
     * BABBAC
     * DABCDAD
     *
     * YZYYZYZYYZYYZYZYY
     *
     * BAC
     *
     * BAB
     */
}
