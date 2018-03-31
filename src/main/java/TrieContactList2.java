/**
 * Created with ♥ by georgeplaton on 31.03.18.
 */

import java.util.Scanner;
        import java.util.HashMap;

public class TrieContactList2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Trie2 trie = new Trie2();
        for (int i = 0; i < n; i++) {
            String operation = scan.next();
            String contact   = scan.next();
            if (operation.equals("add")) {
                trie.add(contact);
            } else if (operation.equals("find")) {
                System.out.println(trie.find(contact));
            }
        }
        scan.close();
    }
}

/* Based loosely on tutorial video in this problem */
class TrieNode2 {
    private HashMap<Character, TrieNode2> children = new HashMap<>();
    public int size;

    public void putChildIfAbsent(char ch) {
        children.putIfAbsent(ch, new TrieNode2());
    }

    public TrieNode2 getChild(char ch) {
        return children.get(ch);
    }
}

class Trie2 {
    TrieNode2 root = new TrieNode2();

    Trie2(){} // default constructor

    Trie2(String[] words) {
        for (String word : words) {
            add(word);
        }
    }

    public void add(String str) {
        TrieNode2 curr = root;
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            curr.putChildIfAbsent(ch);
            curr = curr.getChild(ch);
            curr.size++;
        }
    }

    public int find(String prefix) {
        TrieNode2 curr = root;

        /* Traverse down tree to end of our prefix */
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            curr = curr.getChild(ch);
            if (curr == null) {
                return 0;
            }
        }
        return curr.size;
    }
}
