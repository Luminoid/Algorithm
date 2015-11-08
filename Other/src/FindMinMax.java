/**
 * Created by Ethan on 15/11/8.
 */
public class FindMinMax {
    /**
     * The total number of comparisons of this method is at most 3(n/2).
     */
    public static int[] findMinMax(int[] a) {
        int n = a.length;
        int max;
        int min;
        int returnVal[] = new int[2];
        if (n % 2 == 0) { // if n is even
            if (a[0] > a[1]) {
                max = a[0];
                min = a[1];
            } else {
                max = a[1];
                min = a[0];
            }
            for (int i = 2; i < n; i += 2) {
                if (a[i] > a[i + 1]) {
                    if (a[i] > max) {
                        max = a[i];
                    }
                    if (a[i + 1] < min) {
                        min = a[i + 1];
                    }
                } else {
                    if (a[i + 1] > max) {
                        max = a[i + 1];
                    }
                    if (a[i] < min) {
                        min = a[i];
                    }
                }
            }
        } else { // if n is odd
            max = a[0];
            min = a[0];
            for (int i = 1; i < n; i += 2) {
                if (a[i] > a[i + 1]) {
                    if (a[i] > max) {
                        max = a[i];
                    }
                    if (a[i + 1] < min) {
                        min = a[i + 1];
                    }
                } else {
                    if (a[i + 1] > max) {
                        max = a[i + 1];
                    }
                    if (a[i] < min) {
                        min = a[i];
                    }
                }
            }
        }
        returnVal[0] = max;
        returnVal[1] = min;
        return returnVal;
    }

    public static void main(String[] args) {
        int[] array1 = {12, 2, 3, 8, 5, 3, 1, 12, 0};
        int[] array2 = {17, 9, 2, 6, 1, 9, 4, 24};
        int[] result1 = findMinMax(array1);
        int[] result2 = findMinMax(array2);
        System.out.println("The max value is " + result1[0] + ", the min value is " + result1[1]);
        System.out.println("The max value is " + result2[0] + ", the min value is " + result2[1]);
    }
}
