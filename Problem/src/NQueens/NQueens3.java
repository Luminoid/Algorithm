package NQueens;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/11/26.
 * This method is based on method 1, but using bit operation to decide whether the position is feasible
 */
public class NQueens3 {

    /**
     * All feasible solutions are stored in an ArrayList.
     */
    private static ArrayList<boolean[][]> allSol = new ArrayList<boolean[][]>();
    private static int mask;

    private static void setQueen(boolean[][] chessboard, int row, int pos, boolean bool) {
        int size = chessboard.length;
        int col = 0;
        while (pos != 1) {
            pos >>= 1;
            col++;
        }
        chessboard[row][size - col - 1] = bool;
    }

    /**
     * @param colb    which column bit is infeasible
     * @param leftdb  which left diagonal bit is infeasible
     * @param rightdb which right diagonal bit is infeasible
     */
    private static void nQueens(boolean[][] chessboard, int colb, int leftdb, int rightdb, int row) {
        int feasibleBit, pos;
        if (colb != mask) { // Still have space to place another queen
            feasibleBit = mask & (~(colb | leftdb | rightdb)); // find all feasible bits
            while (feasibleBit != 0) {
                pos = feasibleBit & (~feasibleBit + 1); // find the rightmost 1
                feasibleBit -= pos; // remove the rightmost 1 of feasible bits
                setQueen(chessboard, row, pos, true);
                nQueens(chessboard, colb | pos, (leftdb | pos) << 1, (rightdb | pos) >> 1, row + 1); // set infeasible bits of next line
                setQueen(chessboard, row, pos, false); // restore the change
            }
        } else { // find a solution
            // Array copy
            int size = chessboard.length;
            boolean[][] chessboardCopy = new boolean[size][];
            for (int j = 0; j < size; j++) {
                chessboardCopy[j] = new boolean[size];
                System.arraycopy(chessboard[j], 0, chessboardCopy[j], 0, size);
            }
            allSol.add(chessboardCopy);
        }
    }

    /**
     * Output format
     */
    private static void printSol() {
        System.out.println("[");
        for (boolean[][] chessboard : allSol) {
            for (boolean[] line : chessboard) {
                if (line == chessboard[0]) {
                    System.out.print(" [\"");
                } else {
                    System.out.print("\n  \"");
                }
                for (boolean element : line) {
                    if (element) {
                        System.out.print("Q");
                    } else {
                        System.out.print(".");
                    }
                }
                if (line == chessboard[chessboard.length - 1]) {
                    System.out.print("\"");
                } else {
                    System.out.print("\",");
                }
            }
            if (chessboard == allSol.get(allSol.size() - 1)) {
                System.out.print("]\n");
            } else {
                System.out.print("],\n\n");
            }
        }
        System.out.println("]");
    }

    /**
     * @param detail Whether user needs all the solutions printed
     */
    public static void solveNQueens(int n, boolean detail) {
        // chessboard initialization
        boolean[][] chessboard = new boolean[n][n]; // chessboard initialization
        mask = (1 << n) - 1; // set n bit to 1
        nQueens(chessboard, 0, 0, 0, 0);

        int solNum = allSol.size();
        System.out.println("The solution number is: " + solNum);
        if (detail) {
            printSol();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            allSol = new ArrayList<boolean[][]>();
            solveNQueens(i, false);
        }
    }
}
