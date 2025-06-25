import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RandomFileGenerator {
    public static FileInputStream generateFile(int size) throws IOException {
        String filePath = "C:\\Users\\ionut\\Desktop\\DC\\DC-project\\random_1GB.bin";
        FileOutputStream out = new FileOutputStream(filePath);
        Random rand = new Random();
        byte[] buffer = new byte[1024*1024];
        for (int i = 0; i < size; i++) {
            rand.nextBytes(buffer);
            out.write(buffer);
        }
        out.close();
        return new FileInputStream(filePath);
    }

}
