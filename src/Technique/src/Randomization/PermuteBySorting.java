package Randomization;

import java.util.Arrays;

/**
 * Created by Ethan on 15/10/27.
 */
public class PermuteBySorting {

    static int[] pArr; // random priority

    /**
     * Sort
     */
    private static int partition(int[] arr, int p, int r) {
        int x = pArr[r]; // pivot element
        int y = arr[r];

        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (pArr[j] <= x) {
                i++;
                int tmp = pArr[i];
                pArr[i] = pArr[j];
                pArr[j] = tmp;

                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        pArr[r] = pArr[i + 1];
        pArr[i + 1] = x;

        arr[r] = arr[i + 1];
        arr[i + 1] = y;

        return i + 1;
    }

    private static void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Permute
     */
    public static void permute(int[] arr){
        int n = arr.length;
        pArr = new int[n];
        for (int i = 0; i < n; i++) {
            pArr[i] = (int)(Math.random()*n*n*n);
        }
//        System.out.println(Arrays.toString(pArr));
        quickSort(arr);
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        permute(array);
        System.out.println(Arrays.toString(array));
    }
}
