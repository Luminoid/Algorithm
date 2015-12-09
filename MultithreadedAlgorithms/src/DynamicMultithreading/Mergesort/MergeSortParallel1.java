package DynamicMultithreading.MergeSort;

import java.util.Arrays;

/**
 * Created by Ethan on 15/10/24.
 * Spawn the first recursive call of mergeSort.
 */
public class MergeSortParallel1 {
    static int l; // array length

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

    private static void mergeSort(int[] a, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            if ((r - p) >= l) {
                MergeSortParallel1 msp1 = new MergeSortParallel1();
                SpawnThread spawnThread = msp1.new SpawnThread(a, p, q);
                spawnThread.start(); // spawn
                mergeSort(a, q + 1, r);
                try {
                    spawnThread.join(); // sync
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                mergeSort(a, p, q);
                mergeSort(a, q + 1, r);
            }
            MergeSort.merge(a, p, q, r);
        }
    }

    public static void mergeSort(int[] a) {
        l = a.length;
        l /= 10;
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
