package DynamicMultithreading.Fibonacci;

/**
 * Created by Ethan on 15/12/7.
 */
public class FibonacciParallel {
    public class SpawnThread extends Thread {
        int n;
        int ans;

        public SpawnThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            ans = fibP(n);
        }
    }

    public static int fibP(int n) {
        if (n <= 1) {
            return n;
        } else {
            FibonacciParallel fibonacciParallel = new FibonacciParallel();
            SpawnThread spawnThread = fibonacciParallel.new SpawnThread(n - 1);
            spawnThread.start(); // spawn
            int y = fibP(n - 2);
            try {
                spawnThread.join(); // sync
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int x = spawnThread.ans;
            return x + y;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 18; i++) {
            System.out.println("The " + i + "th \tfibP number is: " + fibP(i));
        }
    }
}
