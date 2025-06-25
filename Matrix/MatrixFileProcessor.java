import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

class MatrixFileProcessor {

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

    public double[][] generateRandomMatrix(String fileName,int rows, int cols) throws FileNotFoundException {
        Random rand = new Random();
        double[][] matrix = new double[rows][cols];

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j] = 100+rand.nextDouble()*900;
            }
        }
        displayMatrix(matrix,fileName);
        return matrix;
    }

    public void displayMatrix(double[][] matrix, String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(fileName));

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                writer.print(matrix[i][j] + " ");
            }
            writer.println();
        }

        writer.close();
    }
}