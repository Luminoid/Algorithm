import java.util.Arrays;

/**
 * Created by Ethan on 15/10/1.
 */

public class Heapsort {

    /**
     * Internal method for heapsort.
     *
     * @param i the index of an item in the heap(starts from 0).
     * @return the index of the left child.
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort that is used in deleteMax and buildHeap.
     *
     * @param a an array of Comparable items.
     * @int i the position from which to percolate down.
     * @int n the logical size of the binary heap
     */
    private static void percDown(int[] a, int i, int n) {
        int child;
        int tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child] < a[child + 1]) {
                child++; // child = rightChild
            }
            if (tmp < a[child]) {
                a[i] = a[child]; // exchange a[i] with a[largest]
            } else
                break;
        }
        a[i] = tmp;
    }

    /**
     * Standard heapsort.
     *
     * @param a an array of Comparable items.
     */
    public static void heapsort(int[] a) {
        int tmp;
        for (int i = a.length / 2; i >= 0; i--) { /* buildHeap */
            percDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) { /* deleteMax */
            tmp = a[i];
            a[i] = a[0];
            a[0] = tmp;
            percDown(a, 0, i); // heapSize --
        }
    }

    /**
     * Test heapsort
     */
    public static void main(String[] args) {
           int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
           heapsort(array);
           System.out.println(Arrays.toString(array));
    }

}