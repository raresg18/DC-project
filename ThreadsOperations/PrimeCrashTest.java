import java.util.Scanner;

public class PrimeCrashTest {

    private static long[] lastPrime = new long[1];
    private static int[] callCount = new int[1];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the start number: ");
        long startNumber = readLong(scanner);

        System.out.print("Enter the end number: ");
        long endNumber = readLong(scanner);

        if (startNumber > endNumber) {
            System.out.println("Start number must be less than or equal to end number.");
            scanner.close();
            return;
        }

        long startTime = System.nanoTime();

        findPrimeRecursive(startNumber, endNumber, callCount, lastPrime);

        long endTime = System.nanoTime();
        long durationNano = endTime - startTime;
        long durationMicro = durationNano / 1_000;
        long durationMilli = durationNano / 1_000_000;

        System.out.println("\nFinished checking primes.");
        System.out.println("Last prime found: " + (lastPrime[0] == 0 ? "None" : lastPrime[0]));
        System.out.println("Total recursive calls: " + callCount[0]);
        System.out.println("Runtime: " + durationMicro + " µs (" + durationMilli + " ms)");

        scanner.close();
    }

    private static long readLong(Scanner scanner) {
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter a valid number:");
            scanner.next(); // discard invalid input
        }
        return scanner.nextLong();
    }

    public static void findPrimeRecursive(long current, long end, int[] callCount, long[] lastPrime) {
        if (current > end) {
            return; // base case: stop recursion
        }

        callCount[0]++;

        if (isPrime(current)) {
            lastPrime[0] = current;
        }

        findPrimeRecursive(current + 1, end, callCount, lastPrime);
    }

    private static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}