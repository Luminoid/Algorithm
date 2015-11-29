package StringMatching;

/**
 * Created by Ethan on 15/11/28.
 */
public class FiniteAutomatonMatcher {
    private static int[][] computeTransitionFunction(String P, char[] alphabet) {
        int m = P.length();
        int[][] transition = new int[m + 1][alphabet.length];
        for (int q = 0; q <= m; q++) {
            for (char c : alphabet) {
                int k = Math.min(m, q + 1);
                while (!(P.substring(0, q) + c).endsWith(P.substring(0, k))) {
                    k--;
                }
                transition[q][c - alphabet[0]] = k;
            }
        }
        return transition;
    }

    /**
     * @param T          Input text
     * @param transition Transition function
     * @param m          Accepting state
     * @param alphabet   Finite input alphabet
     */
    public static void finiteAutomatonMatcher(String T, int[][] transition, int m, char[] alphabet) {
        int n = T.length();
        int q = 0;
        for (int i = 0; i < n; i++) {
            q = transition[q][T.charAt(i) - alphabet[0]];
            if (q == m) {
                System.out.println("Pattern occurs with shift " + (i + 1 - m));
            }
        }
    }

    public static void finiteAutomata(String T, String P, char[] alphabet) {
        finiteAutomatonMatcher(T, computeTransitionFunction(P, alphabet), P.length(), alphabet);
    }

    public static void main(String[] args) {
        String T = "abababacaba";
        String P = "aba";
        char[] alphabet = {'a', 'b', 'c'};
        finiteAutomata(T, P, alphabet);
    }
}