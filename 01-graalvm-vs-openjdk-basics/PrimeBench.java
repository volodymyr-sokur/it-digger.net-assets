public class PrimeBench {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 2; i < 500_000; i++) {
            if (isPrime(i)) count++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Found " + count + " primes in " + (endTime - startTime) + "ms");
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
