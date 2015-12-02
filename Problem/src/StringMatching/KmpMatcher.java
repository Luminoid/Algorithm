package StringMatching;

/**
 * Created by Ethan on 15/11/29.
 * The Knuth-Morris-Pratt algorithm.
 */
public class KmpMatcher {
    public static int[] computePrefixFunction(String P, char base) {
        int m = P.length();
        int[] prefix = new int[m];
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && (P.charAt(k) - base) != (P.charAt(q) - base)) {
                k = prefix[k];
            }
            if ((P.charAt(k) - base) == (P.charAt(q) - base)) {
                k++;
            }
            prefix[q] = k;
        }
        return prefix;
    }

    public static void kmpMatcher(String T, String P, char base) {
        int n = T.length();
        int m = P.length();
        int[] prefix = computePrefixFunction(P, base); // prefix function
        int q = 0; // number of characters matched
        for (int i = 0; i < n; i++) { // scan the text from left to right
            while (q > 0 && (P.charAt(q) - base) != (T.charAt(i) - base)) {
                q = prefix[q - 1]; // next character does not match
            }
            if ((P.charAt(q) - base) == (T.charAt(i) - base)) {
                q++; // next character matches
            }
            if (q == m) { // is all of P matched?
                System.out.println("Pattern occurs with shift " + (i + 1 - m));
                q = prefix[q - 1]; // look for the next match
            }
        }
    }

    public static void main(String[] args) {
        String T = "abababacaba";
        String P = "aba";
        kmpMatcher(T, P, 'a');
    }
}
