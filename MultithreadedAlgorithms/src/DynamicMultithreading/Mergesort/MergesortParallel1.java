package DynamicMultithreading.Mergesort;

import java.util.Arrays;

/**
 * Created by Ethan on 15/10/24.
 * Spawn the first recursive call of mergeSort.
 */
public class MergesortParallel1 {
    public class SpawnThread extends Thread {
        int[] a;
        int p;
        int q;

        public SpawnThread(int[] a, int p, int q) {
            this.a = a;
            this.p = p;
            this.q = q;
        }

        @Override
        public void run() {
            mergeSort(a, p, q);
        }
    }

    private static void merge(int[] a, int p, int q, int r) {
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
            MergesortParallel1 msp1 = new MergesortParallel1();
            SpawnThread spawnThread = msp1.new SpawnThread(a, p, q);
            spawnThread.start(); // spawn
            mergeSort(a, q + 1, r);
            try {
                spawnThread.join(); // sync
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
