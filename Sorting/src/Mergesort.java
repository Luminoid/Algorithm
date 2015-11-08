import java.util.Arrays;

/**
 * Created by Ethan on 15/10/2.
 */
// Mergesort uses the lowest number of comparisons of all the popular sorting algorithms
public class Mergesort {

    /**
     * Internal method that merges two sorted halves of a subarray.
     *
     * @param a        an array of Comparable items.
     * @param leftPos  the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
    private static void merge(int[] a, int leftPos, int rightPos, int rightEnd) {
        int[] tmpArr = new int[a.length];
        int tmpPos = leftPos;
        int leftEnd = rightPos - 1;
        int num = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos] <= a[rightPos])
                tmpArr[tmpPos++] = a[leftPos++];
            else
                tmpArr[tmpPos++] = a[rightPos++];
        }

        // Copy the rest of two halves
        while (leftPos <= leftEnd)
            tmpArr[tmpPos++] = a[leftPos++];
        while (rightPos <= rightEnd)
            tmpArr[tmpPos++] = a[rightPos++];

        // Copy tmpArray back
        for (int i = 0; i < num; i++, rightEnd--) {
            a[rightEnd] = tmpArr[rightEnd];
        }


    }

    /**
     * Internal method that makes recursive calls.
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, left, center);
            mergeSort(a, center + 1, right);
            merge(a, left, center + 1, right);
        }
    }

    /**
     * Mergesort algorithm
     *
     * @param a an array of Comparable items.
     */
    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
