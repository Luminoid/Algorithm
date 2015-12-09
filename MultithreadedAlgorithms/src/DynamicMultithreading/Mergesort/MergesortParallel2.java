package DynamicMultithreading.MergeSort;

import java.util.Arrays;

/**
 * Created by Ethan on 15/12/9.
 * Fashion a multithreaded version of merging by using nested parallelism
 */
public class MergeSortParallel2 {
    public class MPThread extends Thread {
        int[] T;
        int p1;
        int q1;
        int p2;
        int q2;
        int[] A;
        int p3;

        public MPThread(int[] T, int p1, int q1, int p2, int q2, int[] A, int p3) {
            this.T = T;
            this.p1 = p1;
            this.q1 = q1;
            this.p2 = p2;
            this.q2 = q2;
            this.A = A;
            this.p3 = p3;
        }

        @Override
        public void run() {
            mergeP(T, p1, q1, p2, q2, A, p3);
        }
    }

    public class MSPThread extends Thread {
        int[] A;
        int p;
        int q;
        int[] T;
        int i;

        public MSPThread(int[] A, int p, int q, int[] T, int i) {
            this.A = A;
            this.p = p;
            this.q = q;
            this.T = T;
            this.i = i;
        }

        @Override
        public void run() {
            mergeSortP(A, p, q, T, i);
        }
    }

    private static int binarySearch(int x, int[] T, int p, int r) {
        int low = p;
        int high = Math.max(p, r + 1);
        while (low < high) {
            int mid = (low + high) / 2;
            if (x <= T[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    private static void mergeP(int[] T, int p1, int r1, int p2, int r2, int[] A, int p3) {
        int n1 = r1 - p1 + 1;
        int n2 = r2 - p2 + 1;
        if (n1 < n2) { // ensure that n1 ≥ n2
            int tmp;
            tmp = p1; // exchange p1 with p2
            p1 = p2;
            p2 = tmp;
            tmp = r1; // exchange r1 with r2
            r1 = r2;
            r2 = tmp;
            tmp = n1; // exchange n1 with n2
            n1 = n2;
            n2 = tmp;
        }
        if (n1 != 0) {
            int q1 = (p1 + r1) / 2;
            int q2 = binarySearch(T[q1], T, p2, r2);
            int q3 = p3 + (q1 - p1) + (q2 - p2);
            A[q3] = T[q1];

            MergeSortParallel2 msp2 = new MergeSortParallel2();
            MPThread mpThread = msp2.new MPThread(T, p1, q1 - 1, p2, q2 - 1, A, p3);
            mpThread.start(); // spawn
            mergeP(T, q1 + 1, r1, q2, r2, A, q3 + 1);
            try {
                mpThread.join(); // sync
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void mergeSortP(int[] A, int p, int r, int[] B, int s) {
        int n = r - p + 1;
        if (n == 1) {
            B[s] = A[p];
        } else {
            int[] T = new int[n];
            int q = (p + r) / 2;
            int qn = q - p + 1;
            MergeSortParallel2 msp2 = new MergeSortParallel2();
            MSPThread mspThread = msp2.new MSPThread(A, p, q, T, 0);
            mspThread.start(); // spawn
            mergeSortP(A, q + 1, r, T, qn);
            try {
                mspThread.join(); // sync
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mergeP(T, 0, qn - 1, qn, n - 1, B, s);
        }
    }

    public static void mergeSortP(int[] A) {
        int[] B = new int[A.length];
        mergeSortP(A, 0, A.length - 1, B, 0);
        System.arraycopy(B, 0, A, 0, B.length);
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        mergeSortP(array);
        System.out.println(Arrays.toString(array));
    }

}