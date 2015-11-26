package NQueens;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/11/26.
 * This method is based on method 2, but using bit operation to decide whether the position is feasible
 */
public class NQueens3 {

    /**
     * All feasible solutions are stored in an ArrayList.
     */
    protected static ArrayList<int[]> allSol = new ArrayList<int[]>();
    private static int mask;

    private static void setQueen(int[] position, int row, int pos, boolean bool) {
        if (bool) {
            int size = position.length;
            int col = 0;
            while (pos != 1) {
                pos >>= 1;
                col++;
            }
            position[row] = (size - col - 1);
        } else {
            position[row] = -1;
        }
    }

    /**
     * @param colb    which column bit is infeasible
     * @param leftdb  which left diagonal bit is infeasible
     * @param rightdb which right diagonal bit is infeasible
     */
    private static void nQueens(int[] position, int colb, int leftdb, int rightdb, int row) {
        int feasibleBit, pos;
        int size = position.length;
        if (colb != mask) { // Still have space to place another queen
            feasibleBit = mask & (~(colb | leftdb | rightdb)); // find all feasible bits
            while (feasibleBit != 0) {
                pos = feasibleBit & (~feasibleBit + 1); // find the rightmost 1
                feasibleBit -= pos; // remove the rightmost 1 of feasible bits
                setQueen(position, row, pos, true);
                // set infeasible bits of next line
                nQueens(position, colb | pos, (leftdb | pos) << 1, (rightdb | pos) >> 1, row + 1);
                setQueen(position, row, pos, false); // restore the change
            }
        } else { // find a solution
            // Array copy
            int[] positionCopy = new int[size];
            System.arraycopy(position, 0, positionCopy, 0, size);
            allSol.add(positionCopy);
        }
    }

    /**
     * Output format
     */
    private static void printSol() {
        System.out.println("[");
        for (int[] position : allSol) {
            int size = position.length;
            for (int val : position) {
                int s = size;
                if (val == position[0]) {
                    System.out.print(" [\"");
                } else {
                    System.out.print("\n  \"");
                }
                while (size - s != val) {
                    System.out.print(".");
                    s--;
                }
                System.out.print("Q");
                s--;
                while (s > 0) {
                    System.out.print(".");
                    s--;
                }
                if (val == position[position.length - 1]) {
                    System.out.print("\"");
                } else {
                    System.out.print("\",");
                }
            }
            if (position == allSol.get(allSol.size() - 1)) {
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
        int[] position = new int[n];
        for (int i = 0; i < position.length; i++) {
            position[i] = -1;
        }
        mask = (1 << n) - 1; // set n bit to 1
        nQueens(position, 0, 0, 0, 0);

        int solNum = allSol.size();
        System.out.println("The solution number is: " + solNum);
        if (detail) {
            printSol();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            allSol = new ArrayList<int[]>();
            solveNQueens(i, false);
        }
    }
}
