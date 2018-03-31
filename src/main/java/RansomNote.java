/**
 * Created with ♥ by georgeplaton on 31.03.18.
 */

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RansomNote {

    public static void main(String[] args) {
        // 1. Read arguments from command line
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();  //  m  (the number of words in the magazine)
        int n = in.nextInt();  // n  (the number of words in the ransom note).
        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
        }
        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }

        // 2. Validate the input
        if(n > m || magazine.length != m || ransom.length != n) {
            System.out.println("NO");
            return; // invalid input
        }

        // 3. Find the frequency of the words in the magazine
        HashMap<String, Integer> freqsMap = new HashMap<String, Integer>();
        for (int i = 0; i < m; i++){
            int oldFreq = freqsMap.getOrDefault(magazine[i], 0 );
            freqsMap.put(magazine[i], oldFreq + 1);
        }

        // 4. The problem reduces to find if the frequency of words in the ransom is equal or smaller than the frequency of words in magazine
        String result = "Yes";

        for (int j = 0; j < n; j++) {
            String ransomItem = ransom[j];
            int ransomItemFreq = freqsMap.getOrDefault(ransomItem, -1);

            if (ransomItemFreq != -1) {
                freqsMap.put(ransomItem, freqsMap.get(ransomItem) - 1);
            } else {
                result = "No";
                break;
            }
        }

        System.out.println(result);
    }
}
