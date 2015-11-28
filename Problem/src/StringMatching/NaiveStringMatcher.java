package StringMatching;

/**
 * Created by Ethan on 15/11/26.
 */
public class NaiveStringMatcher {
    /**
     * @param T Text
     * @param P Pattern
     */
    public static void naiveStringMatcher(String T, String P) {
        int n = T.length();
        int m = P.length();
        for (int s = 0; s <= n - m; s++) {
            if (P.equals(T.substring(s, s + m))) {
                System.out.println("Pattern occurs with shift " + s);
            }
        }
    }

    public static void main(String[] args) {
        naiveStringMatcher("abcdebcdbcde", "bcde");
    }
}
