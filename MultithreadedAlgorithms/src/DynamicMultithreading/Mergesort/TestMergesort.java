package DynamicMultithreading.Mergesort;

import java.util.Arrays;

/**
 * Created by Ethan on 15/12/9.
 */
public class TestMergesort {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int num = 50*i;

            int[] array = new int[num];
            for (int j = 0; j < num; j++) {
                array[j] = (int) (Math.random() * num);
            }

            System.out.println("Basic Mergesort");
            MergesortParallel1.mergeSort(array);
            System.out.println(Arrays.toString(array));

        }

    }
}
