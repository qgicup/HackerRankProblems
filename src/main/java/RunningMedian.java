import java.util.*;

/**
 * The median of a dataset of integers is the midpoint value of the dataset for which an equal number of integers are less than and greater than the value. To find the median, you must first sort your dataset of integers in non-decreasing order, then:
 * <p>
 * If your dataset contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted dataset ,  is the median.
 * If your dataset contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted dataset ,  is the median.
 * Given an input stream of  integers, you must perform the following task for each  integer:
 * <p>
 * Add the  integer to a running list of integers.
 * Find the median of the updated list (i.e., for the first element through the  element).
 * Print the list's updated median on a new line. The printed value must be a double-precision number scaled to  decimal place (i.e.,  format).
 * Input Format
 * <p>
 * The first line contains a single integer, , denoting the number of integers in the data stream.
 * Each line  of the  subsequent lines contains an integer, , to be added to your list.
 * <p>
 * Constraints
 * <p>
 * Output Format
 * <p>
 * After each new integer is added to the list, print the list's updated median on a new line as a single double-precision number scaled to  decimal place (i.e.,  format).
 * <p>
 * Sample Input
 * <p>
 * 6
 * 12
 * 4
 * 5
 * 3
 * 8
 * 7
 * Sample Output
 * <p>
 * 12.0
 * 8.0
 * 5.0
 * 4.5
 * 5.0
 * 6.0
 * <p>
 * Created with ♥ by georgeplaton on 31.03.18.
 */
public class RunningMedian {

    /**
     * Will compare the given 2 numbers.
     * @param a     - first number
     * @param b     - second number
     * @return      0 if they are the same,
     *              1 if a > b
     *              -1 if b > a
     */
    static int compare(int a, int b)
    {
        if( a == b )
            return 0;

        return a < b ? -1 : 1;
    }

    /**
     * Will make the average of the given elements
     * @param a    - Must not be null, > 0
     * @param b    - Must not be null, > 0
     * @return
     */
    static double average(int a, int b)
    {
        if(a < 0 && b < 0)
            return -1;

        double aa = a;
        double bb = b;

        //System.out.println("Doing average of [" + aa + "] and [" + bb + "] is = " + ((aa + bb) / 2) );
        return (aa + bb) / 2;
    }

    /**
     * Will get the median of the array, based on the left and right heaps.
     * In case array it's even : take the median of the smallest/biggest number
     * In case array it's odd : take the element on the middle of the array
     *
     * Solution :
     *  1) use insertion sort to sort the array, and take the elements in the middle always -> O(n^2)
     *  2) Use BST Balanced, and take the root element, which should be the middle usually.
     *     If it's evenly balanced (left == right), take left root and right root and make the median
     *     It can work, but it's not so efficient, due frequent rebalancing
     *  3) Use 1 MinHeap and 1 MaxHeap to keep the minimums and maximum.
     *     it will also serve to keep the elements in the middle of the array as the roots of these 2 heaps.
     *     For that its important to insert an evne number of elements in both heaps.
     *     If one becomes unbalanced, take items from there and move it in the second heap.
     *     At most, there should be only 1 in difference between the heaps.
     *     This way, we can consider that the root of the 2 heaps, are the elements in the middle
     *     of the array actually.
     *
     * We can use a max heap on left side to represent elements that are less than effective median,
     * and a min heap on right side to represent elements that are greater than effective median.
     * After processing an incoming element, the number of elements in heaps
     * differ utmost by 1 element. When both heaps contain same number of elements,
     * we pick average of heaps root data as effective median.
     * When the heaps are not balanced, we select effective median from the root
     * of heap containing more elements.
     *
     * ! IMPORTANT ! We always want the median, and the best way to accomplish that, is to always
     * store the minimum and the maximum, and then do the median compared on them.
     * We can easily accomplish storing minimum/maximum by having 2 heaps.
     *
     * Those heaps is important to be balanced, aka contain the same number of elements,
     * so then we have a good basis where to insert the new element.
     *
     * Insert procedure :
     *
     *      check if trees are balanced :
     *      leftTree > rightTree :
     *          compare newElem with oldMedian and
     *              - insert in leftTree if less  (also make sure elements are shifted to the other tree, so they will be balanced)
     *              - insert in rightTree if bigger
     *              - take the average of the left & right peek
     *      leftTree == rightTree :
     *          compare newElem with oldMedian and
     *              - insert in leftTree if less, take leftTree peak
     *              - insert in rightTree if bigger, take rightTree peak
     *
     *      leftTree < rightTree :
     *          compare newElem with oldMedian and
     *              - insert in leftTree if less  (also make sure elements are shifted to the other tree, so they will be balanced)
     *              - insert in rightTree if bigger
     *              - take the average of the left & right peek
     *

     * It will try to insert the new element and obtain the median as well.
     *
     * @param crtElem   - the new element that is inserted into the heaps
     * @param median    - the previous median of the tree, important for comparisions.
     * @param leftHeap  - the left heap, it is a MaxHeap
     * @param rightHeap - the right heap, it is a MinHeap
     * @return          Will return the median
     */
    static double getMedian(int crtElem, double median, PriorityQueue<Integer> leftHeap, PriorityQueue<Integer> rightHeap)
    {
        // Are heaps balanced? If yes, sig will be 0
        int sig = compare(leftHeap.size(), rightHeap.size());
        switch(sig)
        {
        case 1: // There are more elements in left (max) heap

            // Median is the old median of the tree, we will insert the new element
            // based on it's position.
            if(crtElem < median) { // current element fits in left (max) heap
                // Because there are more elements in the left heap,
                // we will have to Remove top element from left heap and
                // insert into right heap (make room for the new element insert)
                rightHeap.add(leftHeap.poll());

                // current element fits in left (max) heap
                leftHeap.add(crtElem);
            } else {
                // current element fits in right (min) heap
                // we don't remove new elements from right, since already
                // left size > right size.
                rightHeap.add(crtElem);
            }

            // Both heaps are balanced, they should have the same size now.
            // take the roots and do the average.
            median = average(leftHeap.peek(), rightHeap.peek());
            break;

        case 0: // The left and right heaps contain same number of elements

            if( crtElem < median ) { // current element fits in left (max) heap
                // left heap has now more elements, hence it makes sense to take the median from there
                leftHeap.add(crtElem);
                // after insert, the heap will be heapified, hence,
                // we dont know for sure if we gonna peek the same inserted element,
                // due to the reheapify
                median = leftHeap.peek();
            } else {
                // current element fits in right (min) heap
                // right heap now has more elements, hence it makes sense to take the median from there
                rightHeap.add(crtElem);
                // after insert, the heap will be heapified, hence,
                // we dont know for sure if we gonna peek the same inserted element,
                // due to the reheapify
                median = rightHeap.peek();
            }
            break;

        case -1: // There are more elements in right (min) heap
                 // it also means that the previous median, was in the right heap
                 // hence, it can be removed and inserted in the left heap
                 // in order to make room for the current element.

            if( crtElem < median ) { // current element fits in left (max) heap
                leftHeap.add(crtElem);
            } else {
                // Remove top element from right heap and
                // insert into left heap
                leftHeap.add(rightHeap.poll());
                // current element fits in right (min) heap
                rightHeap.add(crtElem);
            }

            // Both heaps are balanced
            median = average(leftHeap.peek(), rightHeap.peek());
            break;
        }

        return median;
    }

    /**
     * Will find and print the median of the given array
     * @param arr    - the current array of elements
     * @param size   - Must not be null
     */
    static void printMedian(int arr[], int size)
    {
        double median = 0.0; // effective median

        // TODO use Heaps with dynamic size, so then we can use it as an
        // "online" algorithm
        PriorityQueue<Integer> leftQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2)
                    return 0;
                if(o1 < o2)
                    return 1;
                if(o1 > o2)
                    return -1;

                return 0;
            }
        });

        PriorityQueue<Integer> rightQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2)
                    return 0;
                if(o1 < o2)
                    return -1;
                if(o1 > o2)
                    return 1;

                return 0;
            }
        });


        Heap leftHeap  = new MaxHeap(size);
        Heap rightHeap = new MinHeap(size);

        for(int i = 0; i < size; i++)
        {
            //System.out.println("\n Adding elem : [" + arr[i] + "] ...");
            //System.out.println("Status : LeftHeap : " + leftHeap.printHeap() + " RightHeap : " + rightHeap.printHeap());
            median = getMedian(arr[i], median, leftQueue, rightQueue);
            //System.out.println("After adding [" + arr[i] + "] median is now [" + median + "]. LeftHeap : " + leftHeap.printHeap() + " RightHeap : " + rightHeap.printHeap());
            System.out.println(median);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];

        // 1. Read the whole array
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        // 2. Find medians for each element as it was added.
        printMedian(a, n);
    }
}

/**
 * A generic data structure, that will be used in MinHeap and MaxHeap data structures.
 */
abstract class Heap {
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

/**
 * This is a standard MaxHeap data structure that will keep on the root of the tree
 * the biggest element among the whole data structure.
 */
class MaxHeap extends Heap {

    public MaxHeap(int size) {
        super(HeapType.HEAP_MAX, size);
    }

    public MaxHeap(int[] heap) {
        super(HeapType.HEAP_MAX, heap.length);
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }
}

/**
 * A standard MinHeap, which will keep on the root of the Tree,
 * the minimum element inside of the whole data structure.
 *
 */
class MinHeap extends Heap {

    public MinHeap(int size) {
        super(HeapType.HEAP_MIN, size);
    }

    public MinHeap(int[] heap) {
        super(HeapType.HEAP_MIN, heap.length);
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }
}
