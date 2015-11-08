import java.util.Arrays;

/**
 * Created by Ethan on 15/11/8.
 */
public class CountingSort {
    private static int[] countingSort(int[] input, int[] output, int k) {
        int n = input.length;
        int[] position = new int[k + 1]; // 0~k
        for (int i = 0; i <= k; i++) {
            position[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            position[input[i]] += 1;
        }
        // position[i] now contains the number of elements equal to i.
        for (int i = 1; i <= k; i++) {
            position[i] += position[i - 1];
        }
        // position[i] now contains the number of elements less than or equal to i.
        for (int i = n - 1; i >= 0; i--) {
            output[position[input[i]] - 1] = input[i]; // output starts from 0
            position[input[i]] -= 1;
        }
        return output;
    }

    public static void countingSort(int[] input, int k) {
        int n = input.length;
        int[] output = new int[n];
        output = countingSort(input, output, k);
        System.arraycopy(output, 0, input, 0, n); // Deep copy
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12, 0};
        countingSort(array, 12);
        System.out.println(Arrays.toString(array));
    }
}
