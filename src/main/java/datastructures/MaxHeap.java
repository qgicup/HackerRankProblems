package datastructures;

import java.util.Arrays;

/**
 * This is a standard MaxHeap data structure that will keep on the root of the tree
 * the biggest element among the whole data structure.
 * Created with ♥ by georgeplaton on 18.04.18.
 */
public class MaxHeap extends Heap {

    public MaxHeap(int size) {
        super(HeapType.HEAP_MAX, size);
    }

    public MaxHeap(int[] heap) {
        super(HeapType.HEAP_MAX, heap.length);
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }
}
