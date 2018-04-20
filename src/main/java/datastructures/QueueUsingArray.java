package datastructures;

import java.lang.reflect.Array;

/**
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public class QueueUsingArray<T> implements IQueue<T> {

    private static final int CAPACITY = 100;

    T[] array;
    int startPos, endPos, size;

    public QueueUsingArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(clazz, CAPACITY);
        this.array = a;

        startPos = 0;
        endPos = -1;
        size = 0;
    }

    /**
     * Will take the element from the head of the array, and advance the startPos index.
     *
     * [1, 2, 3, _]
     *
     * -> dequeue
     *
     * [_, 2, 3, _] -> startIndex = 1, endIndex = 2;
     *
     * -> enqueue 5
     *  [_, 2, 3, 5] -> startIndex = 1, endIndex = 3;
     *
     * -> enqueue 7
     *  [7, 2, 3, 5] -> startIndex = 1, endIndex = 4 % 4 = 0;
     *
     * -> dequeue
     *  [7, _, 3, 5] -> startIndex = 2, endIndex = 4 % 4 = 0;
     *
     * @return
     */
    @Override
    public T dequeue() {
        T elem = array[startPos % CAPACITY];
        startPos++; // we've poped the element from the beginning of the array, hence the index is not the same anymore.
        size--;

        return elem;
    }

    @Override
    public T peek() {
        T elem = array[startPos];

        return elem;
    }

    /**
     * We gonna add the element at the end of the array,
     * and we gonna peek/pop from the beginning of the array
     * @param element       - Must not be null
     */
    @Override
    public void enqueue(T element) {
        if(element == null || size == CAPACITY)
            return;

        endPos++;
        array[endPos % CAPACITY] = element;
        size++;      //eventually we gonna have to wrap around.
    }

    // Private functions

    public int getStartPos() {
        return startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public int getSize() {
        return size;
    }
}
