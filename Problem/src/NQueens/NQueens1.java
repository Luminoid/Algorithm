package NQueens;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/11/25.
 * This method is based on backtracking.
 */
public class NQueens1 {

    /**
     * All feasible solutions are stored in an ArrayList.
     */
    private static ArrayList<boolean[][]> allSol = new ArrayList<boolean[][]>();

    /**
     * Decide whether chessboard[row][col] is a valid condition for placing a queen.
     * Whether column and diagonals are empty.
     */
    private static boolean isValid(boolean[][] chessboard, int row, int col) {
        // column
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col]) {
                return false;
            }
        }
        // diagonal (left-top)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j]) {
                return false;
            }
        }
        // diagonal (right-top)
        for (int i = row - 1, j = col + 1; i >= 0 && j < chessboard.length; i--, j++) {
            if (chessboard[i][j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * recursive part
     */
    private static void nQueens(boolean[][] chessboard, int row) {
        int size = chessboard.length;
        if (row == chessboard.length) {
            allSol.add(chessboard);
        } else {
            for (int i = 0; i < size; i++) {
                if (isValid(chessboard, row, i)) {
                    chessboard[row][i] = true;
                    nQueens(chessboard, row+1);
                    chessboard[row][i] = false;
                }
            }
        }
    }

    /**
     * * Output format
     * @param detail Whether user needs all the solutions printed
     */
    private static void printSol(boolean detail) {
        System.out.println("[");
        if (detail){
            for (boolean[][] chessboard : allSol) {
                for (boolean[] line : chessboard) {
                    if (line==chessboard[0]){
                        System.out.print(" [\"");
                    }else {
                        System.out.print("\n  \"");
                    }
                    for (boolean element : line) {
                        if (element) {
                            System.out.print("Q");
                        } else {
                            System.out.print(".");
                        }
                    }
                    if (line==chessboard[chessboard.length-1]){
                        System.out.print("\"");
                    }else {
                        System.out.print("\",");
                    }
                }
                if (chessboard==allSol.get(allSol.size()-1)){
                    System.out.print("]\n");
                }else {
                    System.out.print("],\n\n");
                }
            }
            System.out.println("]");
        }
    }

    public static void solveNQueens(int n, boolean detail) {
        boolean[][] chessboard = new boolean[n][n]; // chessboard initialization
        nQueens(chessboard, 0);
        int solNum = allSol.size();
        System.out.println("The solution number is: " + solNum);
        printSol(detail);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            allSol = new ArrayList<boolean[][]>();
            solveNQueens(i, false);
        }
    }
}