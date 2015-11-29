package StringMatching;

/**
 * Created by Ethan on 15/11/27.
 */
public class RabinKarpMatcher {
    public static void rabinKarpMatcher(String T, String P) {
        rabinKarpMatcher(T, P, 26, 29, 'a');
    }

    /**
     * @param T text
     * @param P pattern
     * @param d radix
     * @param q prime
     */
    public static void rabinKarpMatcher(String T, String P, int d, int q, char base) {
        int n = T.length();
        int m = P.length();
        int h = (int) Math.pow(d, m - 1) % q;
        int p = 0; // corresponding decimal value of P
        int[] t = new int[n - m + 1];
        for (int i = 0; i < m; i++) { // Preprocessing
            p = (d * p + P.charAt(i) - base) % q;
            t[0] = (d * t[0] + T.charAt(i) - base) % q;
        }
        for (int s = 0; s <= n - m; s++) { // Matching
            if (p == t[s]) {
                if (P.equals(T.substring(s, s + m))) ;
                System.out.println("Pattern occurs with shift " + s);
            }
            if (s < n - m) {
                // avoid the negative result modulo q
                t[s + 1] = (d * (t[s] - ((T.charAt(s) - base) * h) % q + q) + (T.charAt(s + m) - base)) % q;
            }
        }
    }

    public static void main(String[] args) {
        rabinKarpMatcher("abcdebcdbcde", "bcde");
    }

}
