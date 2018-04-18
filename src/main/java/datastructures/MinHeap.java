package datastructures;

import java.util.Arrays;

/**
 * A standard MinHeap, which will keep on the root of the Tree,
 * the minimum element inside of the whole data structure.
 *
 */
public class MinHeap extends Heap {

    public MinHeap(int size) {
        super(HeapType.HEAP_MIN, size);
    }

    public MinHeap(int[] heap) {
        super(HeapType.HEAP_MIN, heap.length);
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }
}