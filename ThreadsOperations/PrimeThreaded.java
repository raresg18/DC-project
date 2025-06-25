import java.util.ArrayList;
import java.util.List;

public class PrimeThreaded {
    public static class PrimeWorker extends Thread {
        private long start, end;
        private List<Long> primes = new ArrayList<>();

        public PrimeWorker(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (long i = start; i <= end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
        }

        public List<Long> getPrimes() {
            return primes;
        }

        private boolean isPrime(long n) {
            if (n < 2) return false;
            for (long i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }
}
