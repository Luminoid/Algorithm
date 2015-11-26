package NQueens;

/**
 * Created by Ethan on 15/11/26.
 * This method is based on method 3, removing the function of displaying chessboard.
 */
public class NQueens4 {
    /**
     * The number of all feasible solutions.
     */
    protected static int solNum = 0;
    private static int mask;

    /**
     * @param colb    which column bit is infeasible
     * @param leftdb  which left diagonal bit is infeasible
     * @param rightdb which right diagonal bit is infeasible
     */
    private static void nQueens(int colb, int leftdb, int rightdb) {
        int feasibleBit, pos;
        if (colb != mask) { // Still have space to place another queen
            feasibleBit = mask & (~(colb | leftdb | rightdb)); // find all feasible bits
            while (feasibleBit != 0) {
                pos = feasibleBit & (~feasibleBit + 1); // find the rightmost 1
                feasibleBit -= pos; // remove the rightmost 1 of feasible bits
                nQueens(colb | pos, (leftdb | pos) << 1, (rightdb | pos) >> 1); // set infeasible bits of next line
            }
        } else { // find a solution
            solNum++;
        }
    }

    public static void solveNQueens(int n) {
        mask = (1 << n) - 1; // set n bit to 1
        nQueens(0, 0, 0);
        System.out.println("The solution number is: " + solNum);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 25; i++) {
            solNum = 0;
            solveNQueens(i);
        }
    }
}
