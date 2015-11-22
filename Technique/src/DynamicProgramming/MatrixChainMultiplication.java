package DynamicProgramming;

/**
 * Created by Ethan on 15/11/22.
 * <p/>
 * Matrix-chain multiplication problem: given a chain A_1,A_2,...,A_n of n matrices, where for i = 1, 2, ..., n, matrix A_i
 * has dimension p_(i-1)*p_i, fully parenthesize the product A_1 A_2 ... An in a way that minimizes the number of scalar
 * multiplications.
 */
public class MatrixChainMultiplication {
    public static void matrixChainOrder(int[] p, int a, int b) {
        int n = p.length - 1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        for (int l = 2; l <= n; l++) { // l is the chain length
            for (int i = 0; i < (n - l + 1); i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        StringBuilder ret = new StringBuilder();
        StringBuilder str = optimalParens(s, a, b, ret);
        str.toString();
        System.out.println("The optimal parenthesization is: " + str);
        System.out.println("The minimum number of scalar multiplications is: " + m[a][b]);
    }

    public static StringBuilder optimalParens(int[][] s, int i, int j, StringBuilder str) {
        if (i == j) {
            str.append("A" + i);
        } else {
            str.append("(");
            optimalParens(s, i, s[i][j], str);
            optimalParens(s, s[i][j] + 1, j, str);
            str.append(")");
        }
        return str;
    }

    public static void main(String[] args) {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p, 0, p.length - 2);
    }
}
