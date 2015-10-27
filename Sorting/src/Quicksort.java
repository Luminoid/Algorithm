import java.util.Arrays;

/**
 * Created by Ethan on 15/10/2.
 */
public class Quicksort {
    private static int partition(int[] arr, int p, int r) {
        int x = arr[r]; // pivot element
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (arr[j] <= x) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        arr[r] = arr[i + 1];
        arr[i + 1] = x;
        return i + 1;
    }

    private static int randomizedPartition(int arr[], int p, int r) {
        int i = p + (int) (Math.random() * (r - p));
        int tmp = arr[r];
        arr[r] = arr[i];
        arr[i] = tmp;
        return partition(arr, p, r);
    }

    private static void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }

}
