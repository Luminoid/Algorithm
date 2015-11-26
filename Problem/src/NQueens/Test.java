package NQueens;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/11/26.
 */
public class Test {
    public static void main(String[] args) {
        long startTime;
        long endTime;
        NQueens1 nQueens1 = new NQueens1();
        for (int i = 1; i < 16; i++) {
            nQueens1.allSol = new ArrayList<boolean[][]>();
            startTime = System.currentTimeMillis();
            nQueens1.solveNQueens(i, false);
            endTime = System.currentTimeMillis();
            System.out.println("Method 1: The time of size "+i+" is: " + (endTime-startTime));
        }

        NQueens1 nQueens2 = new NQueens1();
        for (int i = 1; i < 16; i++) {
            nQueens2.allSol = new ArrayList<boolean[][]>();
            startTime = System.currentTimeMillis();
            nQueens2.solveNQueens(i, false);
            endTime = System.currentTimeMillis();
            System.out.println("Method 2: The time of size "+i+" is: " + (endTime-startTime));
        }

        NQueens1 nQueens3 = new NQueens1();
        for (int i = 1; i < 16; i++) {
            nQueens3.allSol = new ArrayList<boolean[][]>();
            startTime = System.currentTimeMillis();
            nQueens3.solveNQueens(i, false);
            endTime = System.currentTimeMillis();
            System.out.println("Method 3: The time of size "+i+" is: " + (endTime-startTime));
        }


    }
}
