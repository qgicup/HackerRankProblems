package datastructures;

import java.util.Stack;

/**
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class QueueUsing1Stack<T> implements IQueue<T>{

    Stack<T> stack1;

    public QueueUsing1Stack() {
        stack1 = new Stack<>();
    }

    /**
     * We will dequeue the elements one by one by using recursivity,
     * and then when we've found our element, we will take it away,
     * and then queue the elements back up.
     *
     * @return
     */
    @Override
    public T dequeue() {
        if(stack1.isEmpty())
            return null;
        if(stack1.size() == 1)
            return stack1.pop();

        return dequeueRecursively(stack1);
    }

    @Override
    public T peek() {
        if(stack1.isEmpty())
            return null;
        if(stack1.size() == 1)
            return stack1.peek();

        return peekRecursively(stack1);
    }

    @Override
    public void enqueue(T element) {
        stack1.push(element);
    }

    // PRIVATE FUNCTIONS
    private T dequeueRecursively(Stack<T> stack) {
        if(stack.size() == 1) {
            T elem = stack.pop();
            return elem;
        } else {

            /* pop an item from the stack1 */
            T elem = stack.pop();

            /* store the last dequeued item */
            T res = dequeueRecursively(stack);

            /* push everything back to stack1 */
            stack.push(elem);
            return res;
        }
    }

    private T peekRecursively(Stack<T> stack) {
        if(stack.size() == 1) {
            T elem = stack.peek();
            return elem;

        } else {

            /* pop an item from the stack1 */
            T elem = stack.pop();

            /* store the last dequeued item */
            T res = dequeueRecursively(stack);

            /* push everything back to stack1 */
            stack.push(elem);
            return res;
        }
    }

}
