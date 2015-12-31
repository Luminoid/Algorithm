package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ethan on 15/11/17.
 * <p>
 * Rod-cutting problem: Given a rod of length n inches and a table of prices p_i for i = 1, 2, ... , n, determine the
 * maximum revenue r_n obtainable by cutting up the rod and selling the pieces.
 * Two equivalent ways to implement a dynamic-programming approach:
 * <ol>
 * <li>top-down with memoization</li>
 * <li>bottom-up method</li>
 * </ol>
 */
public class RodCutting {

    public static class Solution {
        int[] r;
        int[] s;

        Solution(int[] r, int[] s) {
            this.r = new int[r.length];
            this.s = new int[s.length];
            System.arraycopy(r, 0, this.r, 0, r.length); // Deep copy
            System.arraycopy(s, 0, this.s, 0, s.length);
        }
    }

    public static Solution bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n + 1]; // optimal revenue
        int[] s = new int[n + 1]; // optimal choice
        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                // the optimal solution uses one subproblem
                if (q < p[j - 1] + r[i - j]) {
                    q = p[j - 1] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }
        return (new Solution(r, s));
    }

    public static void printCutRodSolution(int[] p, int n) {
        Solution solution = bottomUpCutRod(p, n);
        ArrayList<Integer> choice = new ArrayList<Integer>();
        int tmp = n;
        while (tmp > 0) {
            choice.add(solution.s[tmp]);
            tmp -= solution.s[tmp];
        }
        System.out.println("The optimal cut of length " + n + ": " + Arrays.toString(choice.toArray()));
    }

    public static void main(String[] args) {
        int[] p = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        Solution solution = bottomUpCutRod(p, 10);
        int[] r = solution.r;
        int[] s = solution.s;
        System.out.println("The optimal revenue is: " + Arrays.toString(r));
        System.out.println("The optimal choices are: " + Arrays.toString(s));

        for (int i = 1; i <= 10; i++) {
            printCutRodSolution(p, i);
        }
    }
}
