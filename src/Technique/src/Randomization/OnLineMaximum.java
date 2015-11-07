package Randomization;

/**
 * Created by Ethan on 15/11/7.
 */
public class OnLineMaximum {
    /**
     * The on-line hiring problem.
     * This method has at least 1/e chances to hire the best assistant
     */
    public static int onLineMaximum(int score[]) {
        int n = score.length;
        int bestScore = Integer.MIN_VALUE;
        int k = (int) (n / Math.E);
        for (int i = 0; i < k; i++) {
            if (score[i] > bestScore) {
                bestScore = score[i];
            }
        }
        for (int i = k; i < n; i++) {
            if (score[i] > bestScore) {
                return score[i];
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 8, 5, 1, 12, 7, 11, 14};
        System.out.println(onLineMaximum(array));
    }
}
