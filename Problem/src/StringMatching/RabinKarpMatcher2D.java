package StringMatching;

/**
 * Created by Ethan on 15/11/27.
 * The Rabin-Karp algorithm on two-dimensional array.
 * Reference: CLRS, Exercises 32.2-3
 */
public class RabinKarpMatcher2D {

    private static int[] horizontalInitialization(String[] T, int m2, int d, int q, int base) {
        // T = n1*n2
        int n1 = T.length;
        int[] horizontalMatrix = new int[n1];

        // Initializing
        for (int i = 0; i < n1; i++) {
            int tmp = 0;
            for (int j = 0; j < m2; j++) {
                tmp = (d * tmp + T[i].charAt(j) - base) % q;
            }
            horizontalMatrix[i] = tmp;
        }

        return horizontalMatrix;
    }

    private static int[][] verticalRollingHash(String[] T, int m1, int q, int base) {
        // T = n1*n2
        int n1 = T.length;
        int n2 = T[0].length();
        int[][] verticalMatrix = new int[n1 - m1 + 1][n2];

        // Initializing
        for (int j = 0; j < n2; j++) {
            int tmp = 0;
            for (int i = 0; i < m1; i++) {
                tmp += (T[i].charAt(j) - base);
            }
            verticalMatrix[0][j] = tmp % q;
        }

        // Vertical rolling hash
        for (int j = 0; j < n2; j++) {
            for (int i = 0; i < n1 - m1; i++) {
                verticalMatrix[i + 1][j] = (verticalMatrix[i][j] + (T[i + m1].charAt(j) - base) - (T[i].charAt(j) - base)) % q;
            }
        }
        return verticalMatrix;
    }

    public static void rabinKarpMatcher2D(String[] T, String[] P) {
        rabinKarpMatcher2D(T, P, 26, 29, 'a');
    }

    /**
     * For String P, compute the hash value of m numbers in every line first and hash these mh numbers next.
     *
     * @param T text
     * @param P pattern
     * @param d radix
     * @param q prime
     */
    public static void rabinKarpMatcher2D(String[] T, String[] P, int d, int q, char base) {
        // T = n1*n2
        int n1 = T.length;
        int n2 = T[0].length();
        // P = m1*m2
        int m1 = P.length;
        int m2 = P[0].length();
        int h = (int) Math.pow(d, m2 - 1) % q;
        int p = 0; // corresponding decimal value of P
        int t = 0; // corresponding decimal value of S
        int[][] tArr = new int[n1 - m1 + 1][n2 - m2 + 1];
        int[] horizontalMatrix = horizontalInitialization(T, m2, d, q, base);
        int[][] verticalMatrix = verticalRollingHash(T, m1, q, base);

        // Preprocessing p and t
        int[] pTmp = new int[m1]; // temporary array for computing p
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {
                pTmp[i] = (d * pTmp[i] + P[i].charAt(j) - base) % q;
            }
        }
        for (int i = 0; i < m1; i++) {
            p = (p + pTmp[i]) % q;
        }

        for (int i = 0; i < m1; i++) {
            t = (t + horizontalMatrix[i]) % q;
        }
        tArr[0][0] = t;

        // Matching
        for (int i = 0; i <= n1 - m1; i++) { // vertical shift
            for (int s = 0; s <= n2 - m2; s++) { // horizontal shift
                if (p == tArr[i][s]) {
                    boolean isEquivalent = true;
                    for (int j = 0; j < m1; j++) {
                        if (!P[j].equals(T[i + j].substring(s, s + m2))) {
                            isEquivalent = false;
                        }
                    }
                    if (isEquivalent) {
                        System.out.println("Pattern occurs with horizontal shift " + s + " and vertical shift " + i);
                    }
                }
                if (s < n2 - m2) {
                    // Horizontal rolling hash
                    tArr[i][s + 1] = (d * ((tArr[i][s] - verticalMatrix[i][s] * h) % q + q) + verticalMatrix[i][s + m2]) % q;
                }
            }
            if (i < n1 - m1) {
                tArr[i + 1][0] = (tArr[i][0] + (horizontalMatrix[i + m1] - horizontalMatrix[i]) % q + q) % q;
            }
        }
    }

    public static void main(String[] args) {
        String[] T = {"abcdefgh", "ijklmnop", "qrstuvwx", "yzabcdef", "ghijklmn"};
        String[] P = {"def", "lmn"};
        rabinKarpMatcher2D(T, P); // 3,0; 5,3
    }
}
