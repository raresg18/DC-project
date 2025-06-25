public class FixedPointIntegerBenchmark {

    public static int NUM_ITERATIONS;

    public static int add(int a, int b) {
        int result = 0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += a + b;
        }
        return result;
    }

    public static int subtract(int a, int b) {
        int result = 0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += a - b;
        }
        return result;
    }

    public static int multiply(int a, int b) {
        int result = 1;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result *= a * b;
        }
        return result;
    }


    public static int[] divide(int a, int b) {
        if (b == 0) return new int[]{0, 0}; // Prevent division by zero

        int[] results = new int[2]; // Store final result and change
        int initialResult = a / b; // First division result
        int lastResult = initialResult;
        int rest = a % b;
        for (int i = 1; i < NUM_ITERATIONS; i++) {
            int operation = a / b;
            rest = lastResult % operation;
            lastResult = lastResult / operation;
            // Perform repeated division
        }

        results[0] = lastResult; // Final division result
        results[1] = rest;

        return results; // Return final result and change
    }


    public static int andOperation(int a, int b) {
        int result = a;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result &= b;
        }
        return result;
    }

    public static int orOperation(int a, int b) {
        int result = a;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result |= b;
        }
        return result;
    }

    public static int xorOperation(int a, int b) {
        int result = a;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result ^= b;
        }
        return result;
    }
}