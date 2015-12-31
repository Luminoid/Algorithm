package DynamicProgramming;

/**
 * Created by Ethan on 15/11/22.
 * <p>
 * Matrix-chain multiplication problem: given a chain A_1,A_2,...,A_n of n matrices, where for i = 1, 2, ..., n, matrix A_i
 * has dimension p_(i-1)*p_i, fully parenthesize the product A_1 A_2 ... An in a way that minimizes the number of scalar
 * multiplications.
 */
public class MatrixChainMultiplication {
    public static void matrixChainOrder(int[] p) {
        matrixChainOrder(p, 0, p.length - 2);
    }

    public static void matrixChainOrder(int[] p, int a, int b) {
        int n = p.length - 1;
        int[][] m = new int[n][n]; // storing optimal m[i, j] costs
        int[][] s = new int[n][n]; // recording which index of k achieved the optimal cost in computing m[i, j].

        for (int l = 2; l <= n; l++) { // l is the chain length
            for (int i = 0; i < (n - l + 1); i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    // the optimal solution uses two subproblem
                    int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        printResult(s, a, b, m[a][b]);
    }

    public static void optimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + (i + 1));
        } else {
            System.out.print("(");
            optimalParens(s, i, s[i][j]);
            optimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void printResult(int[][] s, int i, int j, int min) {
        System.out.print("The optimal parenthesization is: ");
        optimalParens(s, i, j);
        System.out.println("\nThe minimum number of scalar multiplications is: " + min);
    }

    public static void main(String[] args) {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p);
    }
}
