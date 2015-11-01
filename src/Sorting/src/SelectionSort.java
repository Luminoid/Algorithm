import java.util.Arrays;

/**
 * Created by Ethan on 15/10/24.
 */
public class SelectionSort {

    private static void selectionSort(int[] arr) {
        int smallest;
        // element arr[arr.length - 1] must be the largest element
        for (int i = 0; i < arr.length - 1; i++) {
            smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
