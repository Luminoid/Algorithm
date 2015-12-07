package DynamicMultithreading.Fibonacci;

/**
 * Created by Ethan on 15/12/7.
 */
public class Fibonacci {
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            long x = fibonacci(n - 1);
            long y = fibonacci(n - 2);
            return x + y;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println("The " + i + "th \tfibP number is: " + fibonacci(i));
        }
    }
}