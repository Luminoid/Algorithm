package DivideAndConquer;

/**
 * Created by Ethan on 15/10/26.
 */
public class FindMaximumSubarray {
    static int[] crossIndex = new int[3]; // maxLeft, maxRight, leftSum+rightSum
    static int[] leftIndex = new int[3]; // leftLow, leftHigh, leftSum
    static int[] rightIndex = new int[3]; // rightLow, rightHigh, rightSum

    private static int[] findMaxCrossingSubarray(int[] arr, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        int maxRight = 0;

        // Left Part
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        // Right Part
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        crossIndex[0] = maxLeft;
        crossIndex[1] = maxRight;
        crossIndex[2] = leftSum + rightSum;
        return crossIndex;
    }

    private static int[] findMaximumSubarray(int[] arr, int low, int high) {
        int[] ret = new int[3];
        if (high == low) {
            ret[0] = low;
            ret[1] = high;
            ret[2] = arr[low];
            return ret;
        } else {
            int mid = (low + high) / 2;
            leftIndex = findMaximumSubarray(arr, low, mid);
            rightIndex = findMaximumSubarray(arr, mid + 1, high);
            // Combine
            crossIndex = findMaxCrossingSubarray(arr, low, mid, high);
            if (leftIndex[2] >= rightIndex[2] && leftIndex[2] >= crossIndex[2]) {
                return leftIndex;
            } else if (rightIndex[2] >= leftIndex[2] && rightIndex[2] >= crossIndex[2]) {
                return rightIndex;
            } else {
                return crossIndex;
            }
        }
    }

    public static int findMaximumSubarray(int[] arr) {
        int[] ret = findMaximumSubarray(arr, 0, arr.length - 1);
        return ret[2];
    }

    public static void main(String[] args) {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        System.out.println(findMaximumSubarray(arr));
    }
}