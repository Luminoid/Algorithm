package DivideAndConquer;

/**
 * Created by Ethan on 15/10/24.
 */
public class BinarySearch {

    private static int iterativeBinarySearch(int[] arr, int v, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (v == arr[mid]) {
                return mid;
            } else if (v > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int iterativeBinarySearch(int[] arr, int v) {
        return (iterativeBinarySearch(arr, v, 0, arr.length - 1));
    }

    private static int recursiveBinaryTree(int[] arr, int v, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (v == arr[mid]) {
            return mid;
        } else if (v > arr[mid]) {
            return (recursiveBinaryTree(arr, v, mid + 1, high));
        } else {
            return (recursiveBinaryTree(arr, v, low, mid - 1));
        }
    }

    public static int recursiveBinaryTree(int[] arr, int v) {
        return recursiveBinaryTree(arr, v, 0, arr.length - 1);
    }

    public static void main(String[] args) {
//        the sequence array is sorted
        int[] array = {1, 3, 4, 6, 10, 14, 17, 25};
        int a = iterativeBinarySearch(array, 14);
        int b = recursiveBinaryTree(array, 14);
        System.out.println(a);
        System.out.println(b);
    }
}