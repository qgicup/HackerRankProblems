import java.util.LinkedList;

/**
 *
 * A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
 *
 * Complete the function provided in the editor below. It has one parameter: a pointer to a Node object named  that points to the head of a linked list. Your function must return a boolean denoting whether or not there is a cycle in the list. If there is a cycle, return true; otherwise, return false.
 *
 * Note: If the list is empty,  will be null.
 *
 * Input Format
 *
 * Our hidden code checker passes the appropriate argument to your function. You are not responsible for reading any input from stdin.
 *
 * Constraints
 *
 * Output Format
 *
 * If the list contains a cycle, your function must return true. If the list does not contain a cycle, it must return false. The binary integer corresponding to the boolean value returned by your function is printed to stdout by our hidden code checker.
 *
 * Sample Input
 *
 * The following linked lists are passed as arguments to your function:
 *
 * Sample Inputs
 * Sample Output
 *
 * 0
 * 1
 * Explanation
 *
 * The first list has no cycle, so we return false and the hidden code checker prints  to stdout.
 * The second list has a cycle, so we return true and the hidden code checker prints  to stdout.
 *
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class LinkedListCycleDetection
{
    Node head;  // head of list

    /* Linked list Node*/
    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }

    /* Inserts a new Node at front of the list. */
    public void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    int detectLoop()
    {
        Node slow_p = head, fast_p = head;
        while (slow_p != null && fast_p != null && fast_p.next != null) {
            slow_p = slow_p.next;
            fast_p = fast_p.next.next;
            if (slow_p == fast_p) {
                System.out.println("Found loop");
                return 1;
            }
        }
        return 0;
    }

    /* Drier program to test above functions */
    public static void main(String args[])
    {
        LinkedListCycleDetection llist = new LinkedListCycleDetection();

        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(10);

        /*Create loop for testing */
        llist.head.next.next.next.next = llist.head;

        llist.detectLoop();
    }
}
