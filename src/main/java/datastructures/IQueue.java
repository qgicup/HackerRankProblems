package datastructures;

/**
 * Created with ♥ by georgeplaton on 20.04.18.
 */
public interface IQueue<T> {

    public T dequeue();
    public T peek();
    public void enqueue(T element);
}
