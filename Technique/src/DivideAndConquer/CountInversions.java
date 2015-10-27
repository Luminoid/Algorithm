package DivideAndConquer;

/**
 * Created by Ethan on 15/10/24.
 */
public class CountInversions {
    static int inversionNum = 0;

    private static void mergeInversions(int a[], int p, int q, int r) {
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
                inversionNum += leftArr.length - 1 - i; // subtract the MAX_VALUE
            }
        }
    }

    private static void countInversions(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            countInversions(arr, p, q);
            countInversions(arr, q + 1, r);
            mergeInversions(arr, p, q, r);
        }
    }

    private static void countInversions(int[] arr) {
        countInversions(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 6, 1};
        countInversions(array);
        System.out.println(inversionNum); // 10
    }
}
