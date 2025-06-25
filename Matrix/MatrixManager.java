//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.FileNotFoundException;

public class MatrixManager {
    public static void main(String[] args) throws FileNotFoundException {
        MatrixOperationClass operationTest = new MatrixOperationClass();
        MatrixFileProcessor reader = new MatrixFileProcessor();
        double[][] matrixA = reader.initFromFile("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\matrixA.txt");
        double[][] matrixB = reader.initFromFile("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\matrixB.txt");
        
        long start=System.nanoTime();
        double[][] matrixC = operationTest.subtract(matrixA, matrixB);
        reader.displayMatrix(matrixC, "C:\\Users\\ionut\\Desktop\\DC\\DC-project\\printA.txt");
        long end=System.nanoTime();
        System.out.printf("Matrix Multiplication Time: %.2f ms%n", (end - start) / 1e6);

    }
}

