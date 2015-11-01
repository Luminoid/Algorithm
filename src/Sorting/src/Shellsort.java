import java.util.Arrays;

/**
 * Created by Ethan on 15/9/30.
 */
public class Shellsort {
    public static void shellsort(int[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int tmp = a[i];
                for (j = i; j >= gap && tmp < a[j - gap]; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    // Shellsort using Hibbard's increments
    public static void shellsortHibbard(int[] a) {
        int k = 1;
        int j;
        while ((Math.pow(2, k) - 1) < a.length) {
            k++;
        }
        k--;
        // gap = 2^k-1 (No common factor)
        for (int gap = (int) (Math.pow(2, k) - 1); gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int tmp = a[i];
                for (j = i; j >= gap && tmp < a[j - gap]; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array1 = {12, 2, 3, 8, 5, 3, 1, 12};
        int[] array2 = {12, 2, 3, 8, 5, 3, 1, 12};
        shellsort(array1);
        shellsortHibbard(array2);
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
}
