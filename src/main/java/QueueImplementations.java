import datastructures.QueueUsing1Stack;
import datastructures.QueueUsing2Stacks;
import datastructures.QueueUsingArray;

import java.util.Scanner;

/**
 * A enqueue is an abstract data type that maintains the order in which elements were added to it, allowing the oldest elements to be removed from the front and new elements to be added to the rear. This is called a First-In-First-Out (FIFO) data structure because the first element added to the enqueue (i.e., the one that has been waiting the longest) is always the first one to be removed.
 *
 * A basic enqueue has the following operations:
 *
 * Enqueue: add a new element to the end of the enqueue.
 * Dequeue: remove the element from the front of the enqueue and return it.
 * In this challenge, you must first implement a enqueue using two stacks. Then process  queries, where each query is one of the following  types:
 *
 * 1 x: Enqueue element  into the end of the enqueue.
 * 2: Dequeue the element at the front of the enqueue.
 * 3: Print the element at the front of the enqueue.
 * Input Format
 *
 * The first line contains a single integer, , denoting the number of queries.
 * Each line  of the  subsequent lines contains a single query in the form described in the problem statement above. All three queries start with an integer denoting the query , but only query  is followed by an additional space-separated value, , denoting the value to be enqueued.
 *
 * Constraints
 *
 * It is guaranteed that a valid answer always exists for each query of type .
 * Output Format
 *
 * For each query of type , print the value of the element at the front of the enqueue on a new line.
 *
 * Sample Input
 *
 * 10
 * 1 42
 * 2
 * 1 14
 * 3
 * 1 28
 * 3
 * 1 60
 * 1 78
 * 2
 * 2
 * Sample Output
 *
 * 14
 * 14
 * Explanation
 *
 * We perform the following sequence of actions:
 *
 * Enqueue ; .
 * Dequeue the value at the head of the enqueue, ; .
 * Enqueue ; .
 * Print the value at the head of the enqueue, ; .
 * Enqueue ; .
 * Print the value at the head of the enqueue, ; .
 * Enqueue ; .
 * Enqueue ; .
 * Dequeue the value at the head of the enqueue, ; .
 * Dequeue the value at the head of the enqueue, ; .
 *
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class QueueImplementations  {

    public static void main(String[] args) {
        QueueUsingArray<Integer> queue = new QueueUsingArray<Integer>(Integer.class);

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }

}
