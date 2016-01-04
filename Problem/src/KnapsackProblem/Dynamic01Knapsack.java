package KnapsackProblem;

/**
 * Created by Ethan on 16/1/4.
 */
public class Dynamic01Knapsack {
    /**
     * @param value  the value of items
     * @param weight the weight of items
     * @param n      the number of items
     * @param W      the maximum weight
     * @return the optimal solution
     */
    public static int dynamic01Knapsack(int value[], int weight[], int n, int W) {
        int[][] c = new int[n + 1][W + 1];
        for (int i = 0; i < n; i++) { // every item
            for (int w = 1; w <= W; w++) { // at every weight
                if (weight[i] <= w) {
                    if (value[i] + c[i][w - weight[i]] > c[i][w]) {
                        c[i + 1][w] = value[i] + c[i][w - weight[i]];
                    } else {
                        c[i + 1][w] = c[i][w];
                    }
                } else {
                    c[i + 1][w] = c[i][w];
                }
            }
        }
        return c[n][W];
    }

    public static void main(String[] args) {
        int[] value = {60, 100, 120};
        int[] weight = {1, 2, 3};
        int n = 3;
        int W = 5;
        System.out.println("The max weight is: " + dynamic01Knapsack(value, weight, n, W));
    }

}
