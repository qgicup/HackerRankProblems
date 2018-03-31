import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 *
 * We're going to make our own Contacts application! The application must perform two types of operations:

 add name, where  is a string denoting a contact name. This must store  as a new contact in the application.
 find partial, where  is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.
 Given  sequential add and find operations, perform each operation in order.

 Input Format

 The first line contains a single integer, , denoting the number of operations to perform.
 Each line  of the  subsequent lines contains an operation in one of the two forms defined above.

 Constraints

 It is guaranteed that  and  contain lowercase English letters only.
 The input doesn't have any duplicate  for the  operation.
 Output Format

 For each find partial operation, print the number of contact names starting with  on a new line.

 Sample Input

 4
 add hack
 add hackerrank
 find hac
 find hak
 Sample Output

 2
 0
 Explanation

 We perform the following sequence of operations:

 Add a contact named hack.
 Add a contact named hackerrank.
 Find and print the number of contact names beginning with hac. There are currently two contact names in the application and both of them start with hac, so we print  on a new line.
 Find and print the number of contact names beginning with hak. There are currently two contact names in the application but neither of them start with hak, so we print  on a new line.
 *
 *
 * Solution:
 *
 * Phone Directory can be efficiently implemented using Trie Data Structure. We insert all the contacts into Trie.

 Generally search query on a Trie is to determine whether the string is present or not in the trie, but in this case we are asked to find all the strings with each prefix of ‘str’. This is equivalent to doing a DFS traversal on a graph. From a Trie node, visit adjacent Trie nodes and do this recursively until there are no more adjacent. This recursive function will take 2 arguments one as Trie Node which points to the current Trie Node being visited and other as the string which stores the string found so far with prefix as ‘str’.
 Each Trie Node stores a boolean variable ‘isLast’ which is true if the node represents end of a contact(word).
 *
 * Created with ♥ by georgeplaton on 31.03.18.
 */
public class TrieContactList {


    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String operation = scan.next();
            String contact   = scan.next();
            // 2.3 Perform the operation
            if(operation.equals("add")) {
                trie.insert(contact);
            } else if(operation.equals("find")) {
                trie.findPartial(contact);
            }
        }
        scan.close();
    }
}

/**
 * One simple node in a Trie data structure.
 * Each TrieNode contains a list of childs, which each represent one character in the whole data structure.
 * The number of childs is the number of alphabet letters (in case we're using chinese letters, that is completely different). We'll need to assimilate again, if Trie is an efficient
 * data structure for chinese alphabet as well. We believe yes, since the search performed by characters is quite fast.
 */
class TrieNode
{
    // Each Trie Node contains a Map 'child'
    // where each alphabet points to a Trie
    // Node.
    HashMap<Character, TrieNode> child;

    // 'isLast' is true if the node represents
    // end of a contact
    boolean isLast;
    int childSize;  // we will store the child size, so we don't have to look it up all the time! Quite important optimization, helps with huge datasets

    // Default Constructor
    public TrieNode()
    {
        child = new HashMap<Character,TrieNode>();

        // Initialize all the Trie nodes with NULL
        for (char i = 'a'; i <= 'z'; i++)           // nice way of going through all the letters of alphabet, since the letters are ordered ASCII characters
            child.put(i, null);

        isLast = false;
        childSize = 0;
    }
}

class Trie
{
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    private void print(String s) {
        System.out.println(s);
    }

    /**
     * Will insert a Contact into the Trie
     *
     * So, I have a Trie formed of words hack, hacker, hacking
     //     h                -> root, with 1 children [h]
     //     a                -> second level, with 1 children [a]
     //     c                -> third level, with 1 children [c]
     //     k                -> fourth level, with 1 children [k]
     //    e i               -> fifth level, with 2 children's [e] and [i]
     //    r n               -> sixth level, with 2 children's [r] and [n]
     //      g               -> seventh level, with 1 children [g]

     // Insert of a new word - hackuit
     // 1. Go through all the letters of the crt word
     // 2. See if there is a child with this character
     //      2.1 If there is such a child, advance to the next node in the depth
     //      2.2 If there is no such child, create it and advance then to the next node in the depth

     * @param s         - Must not be null, the string to be inserted
     */
    public void insert(String s)
    {
        int len = s.length();

        // 'itr' is used to iterate the Trie Nodes
        TrieNode itr = root;

        // 1. Go through all the characters of the current word
        for (int i = 0; i < len; i++)
        {
            // 1.2 Check if the character s[i] is already present in Trie
            TrieNode nextNodeInDepth = itr.child.get(s.charAt(i));
            if (nextNodeInDepth == null)
            {
                // 1.2.1 If not found then create a new TrieNode
                nextNodeInDepth = new TrieNode();

                // 1.2.2 Insert into the HashMap
                itr.child.put(s.charAt(i), nextNodeInDepth);
            }

            // 1.3 Move the iterator('itr'), to point to next Trie Node
            itr = nextNodeInDepth;
            itr.childSize++;

            // If its the last character of the string 's'
            // then mark 'isLast' as true
            if (i == len - 1)
                itr.isLast = true;
        }
    }

    /**
     * This function will count the number of childrens starting from this node.
     * In order to do that, we will traverse the tree, and count all nodes,
     * which are leafs.
     *
     * ! RECURSION IMPORTANT ! Each time we need to do recursion, which has to count or sum up, values accumulated during the recursion,
     * then we can use a local variable, which will sum up those values. In this way,
     * when the recursion is finished, we will just return the local variable, which has accumulated
     * all the previous variables.
     *
     * @param crtNode       - Must not be null or be last
     * @return                The number of childrens of this node, recursively to the last one.
     */
    private int countChildrens(TrieNode crtNode) {

        // this local variable will accumulate the results of the recursion.
        // we need such a variable, otherwise, we have no variable/point to cling the other results to
        // quite important to make sure we can accumulate them in this manner.
        int result = 0;

        if(crtNode == null || crtNode.isLast)
            result++;

        // Find all the adjacent Nodes to the current
        // Node and then call the function recursively
        // This is similar to performing DFS on a graph
        for (char i = 'a'; i <= 'z'; i++)
        {
            TrieNode nextNode = crtNode.child.get(i);
            if (nextNode != null)
            {
                result += countChildrens(nextNode);
            }
        }

        return result;
    }

    /**
     * This function will print on a each new line, the count of contact names starting with "@param partial" on a new line.
     * In order to search for the prefix, we need to do the following :
     *      1. Search for the prefix, if it's fully contained in the Trie
     *      2. Starting from that node, find the number of all next nodes
     *      3.
     *
     * @param prefix       - the prefix to search contacts for. . Must not be null
     * @return nothing
     */
    public void findPartial(final String prefix) {

        // 1. Validate the input
        if(prefix == null)
            return;

        // 2. We maintain a prevNode to the last node of the prefix, before the current newly introduced
        // character. Based on this, we will only check new incoming characters as childs of the
        // crt prefix, instead of checking the whole tree again and again.

        TrieNode prevNode = root;
        String prefixSoFar = "";
        int length = prefix.length();

        // 3. Count the contact list for string formed after entering every character
        // We will start from the first character to the last
        // e.g hack -> h, a, c, k
        // Based on each character, we will advance on the depth, if those nodes exists.
        int i;
        for(i = 0; i < length; i++) {
            prefixSoFar += prefix.charAt(i);
            char lastChar = prefix.charAt(i);

            TrieNode curNode = prevNode.child.get(lastChar);
            if(curNode == null) {
                print("0");
                return;
            }

            // Advance prevNode to the current node
            prevNode = curNode;
        }

        // 4. At this point, we've advanced to the node that has our complete prefix
        // we need to count all kids from here.

        print(prevNode.childSize + "");
        //print(countChildrens(prevNode) + "");
    }

}
