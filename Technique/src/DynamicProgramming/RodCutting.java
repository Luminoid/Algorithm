package DynamicProgramming;

/**
 * Created by Ethan on 15/11/17.
 * <p/>
 * Rod-cutting problem: Given a rod of length n inches and a table of prices p_i for i = 1, 2, ... , n, determine the
 * maximum revenue r_n obtainable by cutting up the rod and selling the pieces.
 */
public class RodCutting {
    public static void bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n+1];
        for (int i = 0; i < n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                q = Math.max(q, p[i] + r[j - i]);
            }
            r[i] = q;
        }
    }

    public static void main(String[] args) {
        int[] p = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    }
}
