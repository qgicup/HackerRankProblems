package datastructures;

/**
 * Created with ♥ by georgeplaton on 18.04.18.
 */

import java.util.Arrays;

/**
 * A generic data structure, that will be used in MinHeap and MaxHeap data structures.
 */
public abstract class Heap {
    public enum HeapType {
        HEAP_MIN,
        HEAP_MAX
    }

    public int[] heap;
    public int size;
    public HeapType heapType;

    public Heap(HeapType heapType, int size) {
        this.heapType = heapType;
        this.heap = new int[size];
        this.size = 0;
    }

    // Getters/Setters

    public int getSize() {
        return size;
    }

    public int[] getHeap() {
        return heap;
    }

    public boolean isMaxHeap() {
        return heapType == HeapType.HEAP_MAX;
    }

    public boolean isMinHeap() {
        return heapType == HeapType.HEAP_MIN;
    }

    /**
     * Insert a new element into the heap satisfying
     * the heap property (wethever it is MaxHeap or MinHeap.
     * <p>
     * Time complexity: O(log n) where 'n' is total no. of
     * elements in heap or O(h) where 'h' is the height of
     * heap.
     *
     * @param elem
     */
    public void insert(int elem) {
        // increase heap size
        heap = Arrays.copyOf(heap, size + 1);
        int i = size;
        int parentIndex = (int) Math.floor((i - 1) / 2);

        // move up through the heap till you find the right position
        while (i > 0 &&
                (isMaxHeap() ? (elem > heap[parentIndex]) : (elem < heap[parentIndex]))) {
            heap[i] = heap[parentIndex];
            i = parentIndex;
            parentIndex = (int) Math.floor((i - 1) / 2);
        }
        heap[i] = elem;
        size++;
    }

    /**
     * Will peek the element from the top of the tree without removing it.
     * @return the element
     */
    public int peek() {
        if (size == 0) {
            return -1;
        } else {
            return heap[0];
        }
    }

    /**
     * Will take the element from the top of the tree and remove it.
     * @return the element
     */
    public int pop() {
        if (size == 0)
            return -1;

        int elem = heap[0];
        // last element gets on the root, and from that, we need to heapify,
        // rearrange the data structure again.
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return elem;
    }

    /**
     * Converts array {@param a} in to a max heap.
     * <p/>
     * Time complexity: O(n) and is not O(n log n).
     */
    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Makes the array {@param a} satisfy the max/min heap property starting from
     * {@param index} till the end of array.
     * Time complexity: O(log n).
     * Will rearrange the elements of the tree.
     *
     * @param index - must not be null
     */
    public void heapify(int index) {

        int crtElem = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex < size &&
                (isMaxHeap() ? (heap[index] < heap[leftIndex]) :
                        (heap[index] > heap[leftIndex]))) {
            crtElem = leftIndex;
        }
        if (rightIndex < size &&
                (isMaxHeap() ? (heap[crtElem] < heap[rightIndex]) :
                        (heap[crtElem] > heap[rightIndex]))) {
            crtElem = rightIndex;
        }

        if (crtElem != index) {
            swap(index, crtElem);
            heapify(crtElem);
        }
    }

    /**
     * Will print all the heap
     */
    public String printHeap() {
        if (heap == null)
            return "null";

        int iMax = size - 1, i;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (i = 0; i < iMax; i++) {
            b.append(heap[i]);
            b.append(", ");
        }
        return b.append(heap[i]).append(']').toString();
    }

    /**
     * Will swap 2 elements that should exist in heap
     * @param firstIndex        - Must not be null
     * @param secondIndex       - Must not be null
     */
    protected void swap(int firstIndex, int secondIndex) {
        int temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }
}