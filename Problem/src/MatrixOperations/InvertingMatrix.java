package MatrixOperations;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Ethan on 15/12/13.
 * Using LUP decomposition to compute a matrix inverse.
 */
public class InvertingMatrix {
    static double[][] L;
    static double[][] U;
    static int[] T;

    /**
     * Precise decimal operations
     */
    private static final int DEF_DIV_SCALE = 10; // default division precision scale

    public static double add(double n1, double n2) {
        BigDecimal b1 = new BigDecimal(Double.toString(n1));
        BigDecimal b2 = new BigDecimal(Double.toString(n2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double n1, double n2) {
        BigDecimal b1 = new BigDecimal(Double.toString(n1));
        BigDecimal b2 = new BigDecimal(Double.toString(n2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double n1, double n2) {
        BigDecimal b1 = new BigDecimal(Double.toString(n1));
        BigDecimal b2 = new BigDecimal(Double.toString(n2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double n1, double n2) {
        return div(n1, n2, DEF_DIV_SCALE);
    }

    public static double div(double n1, double n2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(n1));
        BigDecimal b2 = new BigDecimal(Double.toString(n2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Computing an LUP decomposition.
     *
     * @param A nonsingular matrix
     * @throws IllegalArgumentException if A is singular
     */
    public static void lupDecomposition(double[][] A) throws IllegalArgumentException {
        int n = A.length;
        int tmpInt;
        double tmpDouble;
        T = new int[n]; // representing the permutation matrix P
        for (int i = 0; i < n; i++) {
            T[i] = i;
        }
        for (int k = 0; k < n; k++) {
            double max = 0;
            int kEx = 0;
            for (int i = k; i < n; i++) {
                if (Math.abs(A[i][k]) > max) {
                    max = Math.abs(A[i][k]);
                    kEx = i;
                }
            }
            if (max == 0) {
                throw new IllegalArgumentException(); // singular matrix
            }
            tmpInt = T[k]; // exchange T[k] and T[kEx]
            T[k] = T[kEx];
            T[kEx] = tmpInt;

            for (int i = 0; i < n; i++) {
                tmpDouble = A[k][i]; // exchange A[k][i] with A[kEx][i]
                A[k][i] = A[kEx][i];
                A[kEx][i] = tmpDouble;
            }
            for (int i = k + 1; i < n; i++) {
                A[i][k] = div(A[i][k], A[k][k]);
                for (int j = k + 1; j < n; j++) {
                    A[i][j] = sub(A[i][j], mul(A[i][k], A[k][j]));
                }
            }
        }
        ComputingLU(A);
    }

    /**
     * Computing L and U using matrix A
     */
    private static void ComputingLU(double[][] A) {
        int n = A.length;
        L = new double[n][n];
        U = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                U[i][j] = A[i][j]; // j ≥ i
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = A[i][j];
                } else if (i == j) {
                    L[i][j] = 1;
                }
            }
        }
    }

    /**
     * The procedure LUP-SOLVE solves for x by combining forward and back substitution.
     *
     * @param L unit lower-triangular matrix
     * @param U upper-triangular matrix
     * @param T the permutation matrix P is represented by the array T
     * @param b array b<sub>i</sub>
     * @return x
     */
    private static double[] LupSolve(double[][] L, double[][] U, int[] T, double[] b) {
        int n = L.length;
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) { // forward substitution
            double sum = 0;
            for (int j = 0; j <= i - 1; j++) {
                sum = add(sum, mul(L[i][j], y[j]));
            }
            y[i] = sub(b[T[i]], sum);
        }
        for (int i = n - 1; i >= 0; i--) { // back substitution
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = add(sum, mul(U[i][j], x[j]));
            }
            x[i] = div(sub(y[i], sum), U[i][i]);
        }
        return x;
    }

    public static double[][] invertingMatrix(double[][] A) {
        lupDecomposition(A);
        int n = A.length;
        double[][] X = new double[n][n];
        double[][] AInverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            double[] b = new double[n];
            b[i] = 1;
            X[i] = LupSolve(L, U, T, b);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AInverse[i][j] = X[j][i];
            }
        }
        return AInverse;
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2, 0}, {3, 4, 4}, {5, 6, 3}};
        double[][] AInverse = invertingMatrix(A);
        for (double[] arr : AInverse) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
