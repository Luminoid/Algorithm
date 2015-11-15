package DivideAndConquer;

/**
 * Created by Ethan on 15/10/27.
 * <p/>
 * Strassen’s algorithm for matrix multiplication
 */
public class Strassen {
    // Output
    private static void output(int[][] result) {
        for (int[] b : result) {
            for (int a : b) {
                System.out.print(a + "\t");
            }
            System.out.println("");
        }
    }

    // Matrix addition (n*n)
    private static int[][] add(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    // Matrix subtraction (n*n)
    private static int[][] sub(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    // Matrix multiplication (2*2)
    private static int[][] mul(int[][] a, int[][] b) {
        int n = 2;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private static int[][] strassen(int[][] a, int[][] b) {
        int n = a.length / 2;
        int[][] c = new int[2 * n][2 * n];

        int[][] a11 = new int[n][n];
        int[][] a12 = new int[n][n];
        int[][] a21 = new int[n][n];
        int[][] a22 = new int[n][n];
        int[][] b11 = new int[n][n];
        int[][] b12 = new int[n][n];
        int[][] b21 = new int[n][n];
        int[][] b22 = new int[n][n];

        if (a.length == 2) {
            c = mul(a, b); // base case
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a11[i][j] = a[i][j];
                    a12[i][j] = a[i][j + n];
                    a21[i][j] = a[i + n][j];
                    a22[i][j] = a[i + n][j + n];
                    b11[i][j] = b[i][j];
                    b12[i][j] = b[i][j + n];
                    b21[i][j] = b[i + n][j];
                    b22[i][j] = b[i + n][j + n];
                }
            }

            int[][] p1 = sub(strassen(a11, b12), strassen(a11, b22));
            int[][] p2 = add(strassen(a11, b22), strassen(a12, b22));
            int[][] p3 = add(strassen(a21, b11), strassen(a22, b11));
            int[][] p4 = sub(strassen(a22, b21), strassen(a22, b11));
            int[][] p5 = strassen(add(a11, a22), add(b11, b22));
            int[][] p6 = strassen(sub(a12, a22), add(b21, b22));
            int[][] p7 = strassen(sub(a11, a21), add(b11, b12));

            int[][] c11 = sub(add(add(p5, p4), p6), p2);
            int[][] c12 = add(p1, p2);
            int[][] c21 = add(p3, p4);
            int[][] c22 = sub(sub(add(p1, p5), p3), p7);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = c11[i][j];
                    c[i][j + n] = c12[i][j];
                    c[i + n][j] = c21[i][j];
                    c[i + n][j + n] = c22[i][j];
                }
            }
        }
        return c;
    }

    public static int[][] strassenMatrixMul(int[][] a, int[][] b) {
        int k;
        int n = a.length;
        for (k = 0; n > 0; k++) {
            n >>= 1;
        }
        n = a.length;
        int nPower = (int) Math.pow(2, k);
        if (n == nPower / 2) { // n = 2^k
            return strassen(a, b);
        } else {
            // Solve the problem when n != 2^k
            int[][] ap = new int[nPower][nPower];
            int[][] bp = new int[nPower][nPower];
            int[][] cp;
            int[][] c = new int[n][n]; // return value
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ap[i][j] = a[i][j];
                    bp[i][j] = b[i][j];
                }
            }
            // Add 0
            for (int i = 0; i < nPower; i++) {
                for (int j = 0; j < nPower; j++) {
                    ap[i][j] += 0;
                    bp[i][j] += 0;
                }
            }
            cp = strassen(ap, bp);
            // Get rid of 0
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = cp[i][j];
                }
            }
            return c;
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1,
                1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}};
        int[][] b = {{2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2,
                2}, {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2}};
        int[][] c = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] d = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        output(strassenMatrixMul(a, b));
        output(strassenMatrixMul(c, d));

    }
}
