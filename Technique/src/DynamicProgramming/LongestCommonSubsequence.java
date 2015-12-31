package DynamicProgramming;

/**
 * Created by Ethan on 15/11/22.
 * <p>
 * Longest-common-subsequence problem: given two sequences X = x_1, x_2, ..., x_m and Y = y_1, y_2, ..., y_n and
 * wish to find a maximum-length common subsequence of X and Y .
 */
public class LongestCommonSubsequence {
    public static int[][] LcsLength(char[] x, char[] y) {
        int m = x.length;
        int n = y.length;
        int[][] c = new int[m + 1][n + 1]; // optimal subproblem solution
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) { // x[i] != y[i]
                    c[i][j] = c[i - 1][j];
                } else {
                    c[i][j] = c[i][j - 1];
                }
            }
        }
        return c;
    }

    public static void printLcs(int[][] c, char[] x, char[] y, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (x[i-1] == y[j-1]) {
            printLcs(c, x, y, i - 1, j - 1);
            System.out.print(x[i - 1]+" ");
        } else if (c[i - 1][j] >= c[i][j - 1]) {
            printLcs(c, x, y, i - 1, j);
        } else {
            printLcs(c, x, y, i, j - 1);
        }
    }

    public static void solveLcs(char[] x, char[] y) {
        printLcs(LcsLength(x, y), x, y, x.length, y.length);
    }

    public static void main(String[] args) {
        char[] x = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] y = {'B', 'D', 'C', 'A', 'B', 'A'};
        solveLcs(x, y);
    }
}