package DynamicMultithreading.Mergesort;

import java.util.Arrays;

/**
 * Created by Ethan on 15/10/24.
 */
public class MergeSort {
    public static void merge(int[] a, int p, int q, int r) {
        int num1 = q - p + 1;
        int num2 = r - q;
        int[] leftArr = new int[num1 + 1];
        int[] rightArr = new int[num2 + 1];

        for (int i = 0; i < num1; i++) {
            leftArr[i] = a[p + i];
        }
        for (int i = 0; i < num2; i++) {
            rightArr[i] = a[q + i + 1];
        }

        // Sentinel
        leftArr[num1] = Integer.MAX_VALUE;
        rightArr[num2] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (leftArr[i] <= rightArr[j]) {
                a[k] = leftArr[i];
                i++;
            } else {
                a[k] = rightArr[j];
                j++;
            }
        }
    }

    private static void mergeSort(int[] a, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
