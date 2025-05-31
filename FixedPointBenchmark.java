class FixedPointIntegerBenchmark {

    public static int add(int a, int b) {
        return a+b;
    }

    public static int subtract(int a, int b) {
        return a-b;
    }

    public static int multiply(int a, int b) {
        return a*b;
    }

    public static int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            return a / 1;
        }
    }

    public static int andOperation(int a, int b) {
        return a & b;
    }

    public static int orOperation(int a, int b) {
        return a | b;
    }

    public static int xorOperation(int a, int b) {
        return a ^ b;
    }


}
