class FixedPointIntegerBenchmark {

    public static BenchmarkResult runBenchmark(int a, int b){
        //measure time
        Stopwatch stopwatch=new Stopwatch();
        stopwatch.start();

        int result=0;

        for(int i=0;i<100_000_000;i++){
            result += a+b;
            result -= a-b;
            result *= a*b;

            //dividing by 0 case
            if(b!=0)result /= b;
            else result /= 1;

            result &= a;//AND
            result |= b;//OR
            result ^= a;//XOR
            result = ~result;//NOT
            result <<= 2;//multiply by 4
            result >>= 1;//divide by two
            //<=> trying both to test CPU
        }
        double time=stopwatch.getElapsedTime();//get the time in ns
        return new BenchmarkResult(result, time);
    }
}
