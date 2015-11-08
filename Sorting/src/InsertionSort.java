import java.util.Arrays;

/**
 * Created by Ethan on 15/9/30.
 */
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int key;
        int j;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            for (j = i; j > 0 && key < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
