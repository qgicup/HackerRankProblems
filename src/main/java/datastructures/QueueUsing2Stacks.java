package datastructures;

import java.util.Stack;

/**
 *
 * Method 1 (By making enQueue operation costly) This method makes sure that oldest entered element is always at the top of stack 1, so that deQueue operation just pops from stack1. To put the element at top of stack1, stack2 is used.
 *
 * enQueue(q, x)
 *   1) While stack1 is not empty, push everything from satck1 to stack2.
 *   2) Push x to stack1 (assuming size of stacks is unlimited).
 *   3) Push everything back to stack1.
 *
 * dnQueue(q)
 *   1) If stack1 is empty then error
 *   2) Pop an item from stack1 and return it
 * Method 2 (By making deQueue operation costly)In this method, in en-enqueue operation, the new element is entered at the top of stack1. In de-enqueue operation, if stack2 is empty then all the elements are moved to stack2 and finally top of stack2 is returned.
 *
 * enQueue(q,  x)
 *   1) Push x to stack1 (assuming size of stacks is unlimited).
 *
 * deQueue(q)
 *   1) If both stacks are empty then error.
 *   2) If stack2 is empty
 *        While stack1 is not empty, push everything from stack1 to stack2.
 *   3) Pop the element from stack2 and return it.
 * Method 2 is definitely better than method 1.
 * Method 1 moves all the elements twice in enQueue operation, while method 2 (in deQueue operation) moves the elements once and moves elements only if stack2 empty.
 *
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class QueueUsing2Stacks<T> implements IQueue<T> {

    Stack<T> stack1;
    Stack<T> stack2;

    public QueueUsing2Stacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * We will make the dequeue operation a bit more slow, by trying
     * @return
     */
    @Override
    public T dequeue() {
        if(stack1.isEmpty() && stack2.isEmpty())
            return null;
        balanceStacks();

        return stack2.pop();
    }

    @Override
    public T peek() {
        if(stack1.isEmpty() && stack2.isEmpty())
            return null;
        balanceStacks();

        return stack2.peek();
    }

    /**
     * Element is added on top of the first stack.
     * When we are dequeuing, we need to check if there is a element in stack 2.
     * When we are pushing the elements from stack1 to stack2, the elements will be reversed, which is good, since
     * it's gonna satisfy our enqueue policy - first in, first out.
     *
     * @param element       - Must not be null
     */
    @Override
    public void enqueue(T element) {
        stack1.push(element);
    }


    // PRIVATE FUNCTIONS

    private void balanceStacks() {
        if(stack2.isEmpty()) {          // this stack is supposed to keep our reversed elements, which are added from time to time.
            // let's put all elements from stack1 1. They will be introduced in reverse order, which is nice, since it's gonna help us poping them out.

            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }
}
