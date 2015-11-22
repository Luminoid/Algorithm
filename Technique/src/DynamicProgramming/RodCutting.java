package DynamicProgramming;

/**
 * Created by Ethan on 15/11/17.
 * <p/>
 * Rod-cutting problem: Given a rod of length n inches and a table of prices p_i for i = 1, 2, ... , n, determine the
 * maximum revenue r_n obtainable by cutting up the rod and selling the pieces.
 * Two equivalent ways to implement a dynamic-programming approach:
 * <ol>
 * <li>top-down with memoization</li>
 * <li>bottom-up method</li>
 * </ol>
 */
public class RodCutting {
    public static int bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                q = Math.max(q, p[j - 1] + r[i - j]);
            }
            r[i] = q;
        }
        return r[n];
    }

    public static void main(String[] args) {
        int[] p = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        for (int i = 1; i <= 10; i++) {
            System.out.println("The optimal revenue: " + bottomUpCutRod(p, i));
        }
    }
}
