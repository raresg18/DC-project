class Stopwatch {
    private long startTime;

    public Stopwatch() {
        startTime = System.nanoTime();
    }

    // Restart the stopwatch
    public void start() {
        startTime = System.nanoTime();
    }

    // Get the elapsed time in seconds
    public double getElapsedTime() {
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;  // nanoseconds to seconds
    }
}
