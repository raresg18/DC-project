interface IBenchmark {
void initialize(Object... params);
void warmUp();
void run();
void run(Object... options);
void clean();
String getResult();
}

public class CPUThreadedRoots implements IBenchmark {

    private double result;
    private int size;
    private boolean running;

    @Override
    public void initialize(Object... params) {
        if (params != null && params.length > 0 && params[0] instanceof Integer) {
            size = (Integer) params[0];
        } else {
            throw new IllegalArgumentException("Expected an Integer for size");
        }
    }

    @Override
    public void warmUp() {
        int cores = Runtime.getRuntime().availableProcessors();
        run(cores); // warm up using all available cores
    }

    @Override
    public void run() {
        run(Runtime.getRuntime().availableProcessors()); // fallback run
    }

    @Override
    public void run(Object... options) {
        if (options == null || options.length < 1 || !(options[0] instanceof Integer)) {
            throw new IllegalArgumentException("Number of threads must be provided");
        }

        int nThreads = (Integer) options[0];
        Thread[] threads = new Thread[nThreads];
        final int jobPerThread = size / nThreads;

        running = true;
        result = 0.0;

        for (int i = 0; i < nThreads; ++i) {
            int start = i * jobPerThread + 1;
            int end = (i == nThreads - 1) ? size : (i + 1) * jobPerThread;

            SquareRootTask task = new SquareRootTask(start, end);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < nThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        running = false;
    }

    @Override
    public void clean() {
        // Not required
    }

    @Override
    public String getResult() {
        return String.format("%.2f", result);
    }

    // Simple performance score: higher is better
    public double getScore(int threadCount, double timeMs) {
        return size * threadCount / timeMs;
    }

    class SquareRootTask implements Runnable {

        private final int from, to;
        private final double precision = 1e-4;

        public SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            double localResult = 0.0;
            for (int i = from; i <= to && running; ++i) {
                localResult += getNewtonian(i);
            }
            addToGlobalResult(localResult);
        }

        private double getNewtonian(double x) {
            double s = x;
            while (Math.abs(s * s - x) > precision) {
                s = (x / s + s) / 2;
            }
            return s;
        }

        private synchronized void addToGlobalResult(double value) {
            result += value;
        }
    }
}


class TestThreadedRoots {
    public static void main(String[] args) {
        CPUThreadedRoots benchmark = new CPUThreadedRoots();

        int workload = 1_000_000; // total numbers
        benchmark.initialize(workload);
        benchmark.warmUp();

        System.out.println("Threads | Time (ms) | Result | Score");
        System.out.println("----------------------------------------");

        for (int threads = 1; threads <= 8; threads *= 2) {
            long start = System.nanoTime();
            benchmark.run(threads);
            long end = System.nanoTime();

            double timeMs = (end - start) / 1e6;
            String result = benchmark.getResult();
            double score = benchmark.getScore(threads, timeMs);

            System.out.printf("%7d | %9.2f | %s | %.2f\n", threads, timeMs, result, score);
        }
    }
}