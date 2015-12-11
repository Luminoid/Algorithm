package NQueens;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/11/26.
 */
public class Test {
    public static void main(String[] args) {
        long startTime;
        long endTime;
        for (int i = 1; i <= 15; i++) {
            NQueens1.allSol = new ArrayList<boolean[][]>();
            startTime = System.currentTimeMillis();
            NQueens1.solveNQueens(i, false);
            endTime = System.currentTimeMillis();
            NQueens1.allSol.clear();
            System.out.println("Method 1: The time of size " + i + " is: " + (endTime - startTime));
        }

        for (int i = 1; i <= 15; i++) {
            NQueens2.allSol = new ArrayList<int[]>();
            startTime = System.currentTimeMillis();
            NQueens2.solveNQueens(i, false);
            endTime = System.currentTimeMillis();
            NQueens2.allSol.clear();
            System.out.println("Method 2: The time of size " + i + " is: " + (endTime - startTime));
        }

        for (int i = 1; i <= 15; i++) {
            NQueens3.allSol = new ArrayList<int[]>();
            startTime = System.currentTimeMillis();
            NQueens3.solveNQueens(i, false);
            endTime = System.currentTimeMillis();
            NQueens3.allSol.clear();
            System.out.println("Method 3: The time of size " + i + " is: " + (endTime - startTime));
        }

        for (int i = 1; i <= 15; i++) {
            NQueens4.solNum = 0;
            startTime = System.currentTimeMillis();
            NQueens4.solveNQueens(i);
            endTime = System.currentTimeMillis();
            System.out.println("Method 4: The time of size " + i + " is: " + (endTime - startTime));
        }

    }
}
