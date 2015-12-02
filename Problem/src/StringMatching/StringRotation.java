package StringMatching;

/**
 * Created by Ethan on 15/12/2.
 * String cyclic rotation judging algorithm based on KMP in linear time.
 * Reference: CLRS, Exercises 32.2-3
 * Give a linear-time algorithm to determine whether a text T is a cyclic rotation of another string T‘.
 * For example, arc and car are cyclic rotations of each other.
 */
public class StringRotation {
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

    public static int kmpMatcher(String T, String P, char base) {
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
                break;
            }
        }
        return q;
    }

    public static boolean isRotated(String s1, String s2, char base) {
        int n = s1.length();
        if (s2.length() == n) {
            int index = kmpMatcher(s1, s2, base);
            if (index == n) {
                return false; // s1 == s2
            } else {
                return (s1.substring(0, n - index).equals(s2.substring(index, n)));
            }
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        String s1 = "ABCDEF";
        String s2 = "CDEFAB";
        if (isRotated(s1, s2, 'a')) {
            System.out.println("String s2 can be rotated to/from s1.");
        } else {
            System.out.println("String s2 can't be rotated to/from s1.");
        }
    }
}
