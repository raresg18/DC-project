class BenchmarkResult {
    public int result;
    public double time;

    public BenchmarkResult(int result, double time) {
        this.result = result;
        this.time = time;
    }

    public int getResult() {
        return result;
    }

    public double getTime() {
        return time;
    }

}