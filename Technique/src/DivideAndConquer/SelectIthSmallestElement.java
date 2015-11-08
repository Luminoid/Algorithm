package DivideAndConquer;

/**
 * Created by Ethan on 15/11/8.
 */
public class SelectIthSmallestElement {

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
        int m = p + (int) (Math.random() * (r - p));
        int tmp = arr[r];
        arr[r] = arr[m];
        arr[m] = tmp;
        return partition(arr, p, r);
    }

    private static int select(int[] a, int p, int r, int i) {
        if (p == r) {
            return a[p];
        }
        int q = randomizedPartition(a, p, r);
        int k = q - p + 1;
        if (i == k) { // the pivot value is the answer
            return a[q];
        } else if (i < k) {
            return select(a, p, q - 1, i);
        } else {
            return select(a, q + 1, r, i - k);
        }
    }

    public static int select(int[] a, int i) {
        return select(a, 0, a.length - 1, i);
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 17, 0};
        System.out.println(select(array, 5));
    }
}
