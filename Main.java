import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

class Stopwatch {
    private long startTime;

    public Stopwatch() {
        startTime = System.nanoTime();
    }

    // Restart the stopwatch
    public void start() {
        startTime = System.nanoTime();
    }

    // Get the elapsed time in seconds
    public double getElapsedTime() {
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;  // nanoseconds to seconds
    }
}

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


class FloatingPointBenchmark {

    public static int NUM_ITERATIONS;

    public static double add(double a, double b) {
        double result = 0.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += a + b;
        }
        return result;
    }

    public static double multiply(double a, double b) {
        double result = 1.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result *= a * b;
            result = Math.IEEEremainder(result, 1e6);
        }
        return result;
    }

    public static double divide(double a, double b) {
        double result = 1.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result /= a / b;
        }
        return result;
    }

    public static double sin(double a) {
        double result = 0.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += Math.sin(a + i * 1e-6);
        }
        return result;
    }

    public static double cos(double a) {
        double result = 0.0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            result += Math.cos(a + i * 1e-6);
        }
        return result;
    }

    public static double sqrt() {
        double result = 0.0;
        for (int i = 1; i <= NUM_ITERATIONS; i++) {
            result += Math.sqrt(i);
        }
        return result;
    }
}

interface MatrixOperations {
    public double[][] sum(double[][] A, double[][] B);
    public double[][] subtract(double[][] A, double[][] B);
    public double[][] multiply(double[][] A, double[][] B);
    public double[][] transpose(double[][] A);

}

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

    public class MatrixManager {
        public static void main(String[] args) throws FileNotFoundException {
            MatrixOperationClass operationTest = new MatrixOperationClass();
            MatrixFileProcessor reader = new MatrixFileProcessor();
            double[][] matrixA = reader.initFromFile("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\matrixA.txt");
            double[][] matrixB = reader.initFromFile("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\matrixB.txt");

            long start=System.nanoTime();
            double[][] matrixC = operationTest.multiply(matrixA, matrixB);
            reader.displayMatrix(matrixC, "C:\\Users\\ionut\\Desktop\\DC\\DC-project\\printA.txt");
            long end=System.nanoTime();
            System.out.printf("Matrix Multiplication Time: %.2f ms%n", (end - start) / 1e6);

        }
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

class MatrixOperationClass implements MatrixOperations {
    public double[][] A;
    public double[][] B;

    public double[][] sum (double[][] A, double[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }

        return result;
    }


    public double[][] subtract (double[][] A, double[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    public double[][] multiply (double[][] A, double[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    result[i][j] = A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    @Override
    public double[][] transpose(double[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[j][i];
            }
        }

        return result;
    }

}

class GUI {

    public GUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setSize(500, 400);  // Set the size of the window (800x600)
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        String[] operations = {"Add", "Multiply", "Divide", "Sin", "Cos", "Sqrt"};
        JComboBox<String> operationBox = new JComboBox<>(operations);

        String[] iterationsOptions = {"1", "100", "5000", "10000000", "50000000", "100000000", "200000000"};
        JComboBox<String> iterationsBox = new JComboBox<>(iterationsOptions);

        // Create six buttons
        JButton button1 = new JButton("FIXED POINT");
        JButton button2 = new JButton("FLOATING POINT");
        JButton button3 = new JButton("MATRIX OPERATION");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");
        JButton button6 = new JButton("Button 6");

        // Set button sizes using setPreferredSize
        Dimension buttonSize = new Dimension(200, 30); // 80px wide and 30px tall
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        button6.setPreferredSize(buttonSize);

        // Set the minimum size for consistency
        button1.setMinimumSize(buttonSize);
        button2.setMinimumSize(buttonSize);
        button3.setMinimumSize(buttonSize);
        button4.setMinimumSize(buttonSize);
        button5.setMinimumSize(buttonSize);
        button6.setMinimumSize(buttonSize);

        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);

        JLabel resultLabel = new JLabel("Result: ");
        JLabel timeLabel = new JLabel("Time: ");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10); // Add some space around the buttons

        // Left Upper Corner
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridPanel.add(button1, gbc);

        // Left Middle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridPanel.add(button2, gbc);

        // Left Lower Corner
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridPanel.add(button3, gbc);

        // Right Upper Corner
        gbc.gridx = 2;
        gbc.gridy = 0;
        gridPanel.add(button4, gbc);

        // Right Middle
        gbc.gridx = 2;
        gbc.gridy = 1;
        gridPanel.add(button5, gbc);

        // Right Lower Corner
        gbc.gridx = 2;
        gbc.gridy = 2;
        gridPanel.add(button6, gbc);

        // Panel for combo boxes (operations and iterations)
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(2, 2, 10, 10)); // Two rows, two columns
        comboPanel.add(new JLabel("Select Operation:"));
        comboPanel.add(operationBox);
        comboPanel.add(new JLabel("Select Iterations:"));
        comboPanel.add(iterationsBox);

        // Centering the text fields and labels below the buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2, 10, 10));
        bottomPanel.add(textField1);
        bottomPanel.add(textField2);
        bottomPanel.add(resultLabel);
        bottomPanel.add(timeLabel);

        // Add the gridPanel (with buttons), comboPanel, and bottomPanel to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(comboPanel, BorderLayout.NORTH);  // Place comboBox at the top (North)
        frame.add(bottomPanel, BorderLayout.SOUTH);  // Place text fields and results at the bottom (South)



        // Action listener for the button click to perform addition
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(textField1.getText());
                    int num2 = Integer.parseInt(textField2.getText());

                    Stopwatch stopwatch = new Stopwatch();
                    BenchmarkResult benchmarkResult = FixedPointIntegerBenchmark.runBenchmark(num1, num2);

                    int result = benchmarkResult.getResult();
                    double elapsedTime = benchmarkResult.getTime();

                    resultLabel.setText("Result: " + result);
                    timeLabel.setText("Time: " + String.format("%.9f", elapsedTime) + " seconds");

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers.");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedIterations = (String) iterationsBox.getSelectedItem();
                    int numIterations = Integer.parseInt(selectedIterations.replace(",", "").trim());

                    Stopwatch stopwatch = new Stopwatch();
                    double a = Double.parseDouble(textField1.getText());
                    double b = Double.parseDouble(textField2.getText());
                    String operation = (String) operationBox.getSelectedItem();

                    FloatingPointBenchmark.NUM_ITERATIONS = numIterations;

                    stopwatch.start();

                    double result = 0.0;
                    switch (operation) {
                        case "Add":
                            result = FloatingPointBenchmark.add(a, b);
                            break;
                        case "Multiply":
                            result = FloatingPointBenchmark.multiply(a, b);
                            break;
                        case "Divide":
                            result = FloatingPointBenchmark.divide(a, b);
                            break;
                        case "Sin":
                            result = FloatingPointBenchmark.sin(a);
                            break;
                        case "Cos":
                            result = FloatingPointBenchmark.cos(b);
                            break;
                        case "Sqrt":
                            result = FloatingPointBenchmark.sqrt();
                            break;
                    }

                    double elapsedTime = stopwatch.getElapsedTime();

                    resultLabel.setText("Result: " + String.format("%.3f", result));
                    timeLabel.setText("Time: " + String.format("%.9f", elapsedTime) + " seconds");

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers.");
                }
            }
        });

        // button3.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         try {
        //             MatrixFileProcessor fileProcessor = new MatrixFileProcessor();
        //             MatrixOperationClass matrixOperations = new MatrixOperationClass();

        //             double[][] matrixA = fileProcessor.initFromFile("matrixA.txt");
        //             double[][] matrixB = fileProcessor.initFromFile("matrixB.txt");

        //             Stopwatch stopwatch = new Stopwatch();
        //             double[][] resultMatrix = matrixOperations.multiply(matrixA, matrixB);

        //             fileProcessor.displayMatrix(resultMatrix, "matrixResult.txt");

        //             resultLabel.setText("Matrix result written to matrixResult.txt");

        //             double elapsedTime = stopwatch.getElapsedTime();
        //             timeLabel.setText("Time: " + String.format("%.9f", elapsedTime) + " seconds");

        //         } catch (FileNotFoundException ex) {
        //             resultLabel.setText("Error reading matrix files.");
        //             ex.printStackTrace();
        //         } catch (Exception ex) {
        //             resultLabel.setText("An error occurred.");
        //             ex.printStackTrace();
        //         }
        //     }
        // });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Benchmark");
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new GUI();
    }
}
