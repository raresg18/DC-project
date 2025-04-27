import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MatrixFileProcessor {

    public double[][] initFromFile(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner scanner = new Scanner(inputFile);

        double[][] matrix = new double[500][500];
        for(int i=0;i<500;i++){
            for(int j=0;j<500;j++){
                matrix[i][j] = scanner.nextDouble();
            }
        }

        scanner.close();
        return matrix;
    }

    public void displayMatrix(double[][] matrix, String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(fileName));

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                writer.print(matrix[i][j] + " ");
            }
            writer.println();
        }

        writer.close();
    }
}
