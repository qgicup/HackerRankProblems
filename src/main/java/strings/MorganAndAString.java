package strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with ♥ by georgeplaton on 22.04.18.
 */
public class MorganAndAString {

    /**
     * We need to construct a string using the given parameters.
     * The way to construct this string, is by taking the letters one by one, compare them, and then construct the final string
     * @param a     - must not be null
     * @param b     - must not be null
     * @return
     */
    static String morganAndString(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int aIndex = 0, bIndex = 0;
        String result = "";

        while(aIndex < aLength && bIndex < bLength) {
            char charA = a.charAt(aIndex);
            char charB = b.charAt(bIndex);

            if(charA < charB) {
                result = result + charA;
                aIndex++;
            } else if(charA > charB) {
                result = result + charB;
                bIndex++;
            } else if(charA == charB) {
                //result = result + charA;
                result = result + charB;

                //aIndex++;
                bIndex++;
            }
        }

        // copy the remaining elements
        if(aIndex < aLength) {
            char[] charArray = a.substring(aIndex, aLength).toCharArray();
            Arrays.sort(charArray);
            for(int i = 0; i < charArray.length; i++) {
                // Here take them in lexicographic order.
                result = result + charArray[i];
            }
        }


        if(bIndex < bLength) {
            char[] charArray = b.substring(bIndex, bLength).toCharArray();
            Arrays.sort(charArray);
            for(int i = 0; i < charArray.length; i++) {
                // Here take them in lexicographic order.
                result = result + charArray[i];
            }
        }

        return result; // BABABC
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
