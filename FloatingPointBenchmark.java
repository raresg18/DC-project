class FloatingPointBenchmark {

    public static int NUM_ITERATIONS;

    public static double add(double a, double b) {
        double result = 0.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += a + b;
        }
        return result;
    }

    public static double multiply(double a, double b) {
        double result = 1.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result *= a * b;
            result = Math.IEEEremainder(result, 1e6);
        }
        return result;
    }

    public static double divide(double a, double b) {
        double result = 1.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            if(i==0){
                result = a / b;
            }
            else {
                result /= a / b;
            }
        }
        return result;
    }

    public static double sin(double a) {
        double result = 0.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += Math.sin(a + i * 1e-6);
        }
        return result;
    }

    public static double cos(double a) {
        double result = 0.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += Math.cos(a + i * 1e-6);
        }
        return result;
    }

    public static double sqrt() {
        double result = 0.0;
        for (int i = 1; i <= NUM_ITERATIONS; i++) {
            result += Math.sqrt(i);
        }
        return result;
    }
}