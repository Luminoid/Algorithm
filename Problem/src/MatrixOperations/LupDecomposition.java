package MatrixOperations;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Ethan on 15/12/11.
 * A practical and numerically stable approach to solve systems of linear equations.
 * <p>Some important equations:</p>
 * <h4> Overview of LUP decomposition </h4>
 * <ol>
 * <li> Ax = b </li>
 * <li> PA = LU (L: unit lower-triangular matrix, U: upper-triangular matrix, P: permutation matrix) </li>
 * <li> PAx = Pb = LUx </li>
 * <li> Ly = Pb (y = Ux. Using forward substitution to get y) </li>
 * <li> Ux = y (Using back substitution to get x) </li>
 * <li> A = P<sup>-1</sup>LU </li>
 * </ol>
 * <h4> Computing an LU decomposition </h4>
 * <ol>
 * <li>  </li>
 * </ol>
 */
public class LupDecomposition {
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



    public static void main(String[] args) {
        double[][] L = {{1, 0, 0}, {0.2, 1, 0}, {0.6, 0.5, 1}};
        double[][] U = {{5, 6, 3}, {0, 0.8, -0.6}, {0, 0, 2.5}};
        int[] T = {2, 0, 1};
        double[] b = {3, 7, 8};
        System.out.println(Arrays.toString(LupSolve(L, U, T, b)));
    }
}