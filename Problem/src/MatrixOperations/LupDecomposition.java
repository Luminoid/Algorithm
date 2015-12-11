package MatrixOperations;

/**
 * Created by Ethan on 15/12/11.
 * A practical and numerically stable approach to solve systems of linear equations.
 * <p>Some important equations:</p>
 * <ol>
 * <li> Ax = b </li>
 * <li> PA = LU (L: unit lower-triangular matrix, U: upper-triangular matrix, P: permutation matrix) </li>
 * <li> PAx = Pb = LUx </li>
 * <li> Ly = Pb (y = Ux. Using forward substitution to get y) </li>
 * <li> Ux = y (Using back substitution to get x) </li>
 * <li> A = P<sup>-1</sup>LU </li>
 * </ol>
 */
public class LupDecomposition {

}
