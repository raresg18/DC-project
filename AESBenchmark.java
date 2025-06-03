import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class AESBenchmark {

    public static BenchmarkResult runBenchmark(int dataSizeMB) throws Exception {
        int dataSize = dataSizeMB * 1024 * 1024;

        // Generate random data
        byte[] data = new byte[dataSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(data);

        // Generate AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES-128 bit key
        SecretKey secretKey = keyGen.generateKey();

        Cipher encryptCipher = Cipher.getInstance("AES/ECB/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        Cipher decryptCipher = Cipher.getInstance("AES/ECB/NoPadding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

        Stopwatch stopwatch = new Stopwatch();

        // Encryption Benchmark
        stopwatch.start();
        byte[] encrypted = encryptCipher.doFinal(data);
        double encryptTimeSec = stopwatch.getElapsedTime();
        double encryptSpeedMBs = dataSizeMB / encryptTimeSec;

        // Decryption Benchmark
        stopwatch.start();
        byte[] decrypted = decryptCipher.doFinal(encrypted);
        double decryptTimeSec = stopwatch.getElapsedTime();
        double decryptSpeedMBs = dataSizeMB / decryptTimeSec;

        return new BenchmarkResult(encryptSpeedMBs, decryptSpeedMBs);
    }

    public static class BenchmarkResult {
        public final double encryptSpeedMBs;
        public final double decryptSpeedMBs;

        public BenchmarkResult(double encryptSpeedMBs, double decryptSpeedMBs) {
            this.encryptSpeedMBs = encryptSpeedMBs;
            this.decryptSpeedMBs = decryptSpeedMBs;
        }
    }
}
