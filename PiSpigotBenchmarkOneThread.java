import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PiSpigotBenchmarkOneThread {
    // default number of fractional digits to compute




    /**
     * Runs pi computation and writes to file.
     * @param digits number of fractional digits to compute
     * @return elapsed time in milliseconds
     */
    public long benchmarkComputation(int digits) {
        long startTime = System.nanoTime();
        String piString = computePiDigits(digits);
        long endTime = System.nanoTime();

        writeToFile("computed_pi.txt", piString);
        return (endTime - startTime) / 1_000_000;
    }


    private String computePiDigits(int digits) {
        // we need one extra iteration to get the integer “3”
        System.out.println("Computing " + digits + " of pi...");
        int totalDigits = digits + 1;
        // array length ≃ 10*totalDigits/3 + 1
        int arrayLength = totalDigits * 10 / 3 + 1;
        int[] a = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            a[i] = 2;
        }

        StringBuilder sb = new StringBuilder(totalDigits + 1);
        StringBuilder predigits = new StringBuilder();
        int newline_buffer = 0;

        for (int iter = 0; iter < totalDigits; iter++) {

            newline_buffer += 1;
            int carry = 0;
            // inner “spigot” loop, backwards through the array
            for (int i = arrayLength - 1; i > 0; i--) {
                int tmp   = a[i] * 10 + carry;
                int denom = 2 * i + 1;
                a[i]      = tmp % denom;
                carry     = (tmp / denom) * i;
            }
            // now handle a[0] to extract the next output digit
            int tmp = a[0] * 10 + carry;
            a[0]    = tmp % 10;
            int digit = tmp / 10;

            // buffer or flush according to the Rabinowitz–Wagon “predigit” rules
            if (digit < 9) {
                // flush any buffered nines
                sb.append(predigits);
                predigits.setLength(0);
                sb.append((char)('0' + digit));
            } else if (digit == 9) {
                // could be part of a long run of 9’s
                predigits.append('9');
            } else {  // digit == 10
                // overflow: bump the last real digit
                for (int k = 0; k < predigits.length(); k++) {
                    predigits.setCharAt(k, '0');
                }
                int lastIndex = sb.length() - 1;
                char lastChar = sb.charAt(lastIndex);
                sb.setCharAt(lastIndex, (char)(lastChar + 1));
                sb.append(predigits);
                predigits.setLength(0);
            }
            if(newline_buffer >=100) {
                sb.append('\n');
                newline_buffer = 0;
            }

        }

        // any trailing buffered nines
        sb.append(predigits);
        // insert the decimal point after the first character (“3”)
        sb.insert(1, '.');
        return sb.toString();
    }

    /** Helper to write the final string into a file. */
    private void writeToFile(String filename, String content) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println(content);
        } catch (IOException e) {
            System.err.println("Error writing " + filename + ": " + e.getMessage());
        }
    }
}
