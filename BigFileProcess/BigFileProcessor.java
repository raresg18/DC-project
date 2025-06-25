import java.io.*;
import java.util.Scanner;

public class BigFileProcessor {
    private final FileInputStream fis;
    public long totalBytesRead = 0;
    public BigFileProcessor(int size) throws IOException {
        RandomFileGenerator rfg = new RandomFileGenerator();
        this.fis = RandomFileGenerator.generateFile(size);
    }

    public void readFile() {
        try{
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1){
                totalBytesRead+=bytesRead;
            }
            System.out.println("Finished reading file");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public long getTotalBytesRead() {
        return totalBytesRead;
    }

    public static void main(String[] args) throws IOException {
        BigFileProcessor bfp = new BigFileProcessor(512);
        bfp.readFile();

    }

}
