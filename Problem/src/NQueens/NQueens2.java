package NQueens;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/11/25.
 * This method is based on backtracking by one-dimensional int array representing the queen position in every line.
 */
public class NQueens2 {

    /**
     * All feasible solutions are stored in an ArrayList.
     */
    protected static ArrayList<int[]> allSol = new ArrayList<int[]>();

    /**
     * Decide whether position[i] is a valid condition for placing a queen.
     * Whether column and diagonals are empty.
     */
    private static boolean isValid(int[] position, int row, int col) {
        // column and diagonals
        for (int i = 0; i < row; i++) {
            if (position[i] == col || (row - i) == Math.abs(col - position[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * recursive part
     */
    private static void nQueens(int[] position, int row) {
        int size = position.length;
        if (row == position.length) {
            // Array copy
            int[] positionCopy = new int[size];
            System.arraycopy(position, 0, positionCopy, 0, size);
            allSol.add(positionCopy);
        } else {
            for (int col = 0; col < size; col++) {
                if (isValid(position, row, col)) {
                    position[row] = col;
                    nQueens(position, row + 1);
                    position[row] = -1;
                }
            }
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

        nQueens(position, 0);
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
