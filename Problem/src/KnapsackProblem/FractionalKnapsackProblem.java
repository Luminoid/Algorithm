package KnapsackProblem;

/**
 * Created by Ethan on 16/1/4.
 * Using greedy algorithm to solve Fractional Knapsack Problem
 */
public class FractionalKnapsackProblem {
    /**
     * Sorting by descending order
     */
    private static int partition(double[] unitPrice, int[] value, int[] weight, int p, int r) {
        double x = unitPrice[r]; // pivot element
        int y = value[r];
        int z = weight[r];

        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (unitPrice[j] >= x) {
                i++;
                double tmp = unitPrice[i];
                unitPrice[i] = unitPrice[j];
                unitPrice[j] = tmp;

                int tmpInt = value[i];
                value[i] = value[j];
                value[j] = tmpInt;
                tmpInt = weight[i];
                weight[i] = weight[j];
                weight[j] = tmpInt;
            }
        }
        unitPrice[r] = unitPrice[i + 1];
        unitPrice[i + 1] = x;

        value[r] = value[i + 1];
        value[i + 1] = y;
        weight[r] = weight[i + 1];
        weight[i + 1] = z;

        return i + 1;
    }

    private static void quickSort(double[] unitPrice, int[] value, int[] weight, int p, int r) {
        if (p < r) {
            int q = partition(unitPrice, value, weight, p, r);
            quickSort(unitPrice, value, weight, p, q - 1);
            quickSort(unitPrice, value, weight, q + 1, r);
        }
    }

    private static void quickSort(double[] unitPrice, int[] value, int[] weight) {
        quickSort(unitPrice, value, weight, 0, unitPrice.length - 1);
    }

    /**
     * @param value  the value of items
     * @param weight the weight of items
     * @param n      the number of items
     * @param W      the maximum weight
     * @return the optimal value
     */
    public static double fractionalKnapsack(int value[], int weight[], int n, int W) {
        double[] unitPrice = new double[n];
        for (int i = 0; i < n; i++) {
            unitPrice[i] = (double) value[i] / (double) weight[i];
        }
        quickSort(unitPrice, value, weight); // descending order
        int item = 0;
        double totalValue = 0;
        for (int i = 0; i < n; i++) {
            if (item + weight[i] <= W) {
                totalValue += value[i];
                item += weight[i];
            } else {
                for (; item < W; item++) {
                    totalValue += unitPrice[i];
                }
                return totalValue;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[] value = {60, 100, 120};
        int[] weight = {1, 2, 3};
        int n = 3;
        int W = 5;
        System.out.println("The max weight is: " + fractionalKnapsack(value, weight, n, W));
    }
}
