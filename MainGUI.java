import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;



public class MainGUI {

    public MainGUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setSize(1000, 600);  // Set the size of the window (800x600)
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        String[] operations = {"Add", "Multiply", "Divide", "Sin", "Cos", "Sqrt", "Subtract (for Matrix)"};
        JComboBox<String> operationBox = new JComboBox<>(operations);

        String[] iterationsOptions = {"1", "100", "5000", "10000000", "50000000", "100000000", "200000000"};
        JComboBox<String> iterationsBox = new JComboBox<>(iterationsOptions);

        // Create six buttons
        JButton button1 = new JButton("FIXED POINT OPERATIONS");
        JButton button2 = new JButton("FLOATING POINT OPERATIONS");
        JButton button3 = new JButton("MATRIX OPERATIONS");
        JButton button4 = new JButton("PI OPERATION");
        JButton button5 = new JButton("SORTING OPERATION");
        JButton button6 = new JButton("FILE PROCESSING OPERATION.");
        JButton button7 = new JButton("THREADS OPERATION");

        // Set button sizes using setPreferredSize
        Dimension buttonSize = new Dimension(360, 40); // 80px wide and 30px tall
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        button6.setPreferredSize(buttonSize);
        button7.setPreferredSize(buttonSize);

        // Set the minimum size for consistency
        button1.setMinimumSize(buttonSize);
        button2.setMinimumSize(buttonSize);
        button3.setMinimumSize(buttonSize);
        button4.setMinimumSize(buttonSize);
        button5.setMinimumSize(buttonSize);
        button6.setMinimumSize(buttonSize);
        button7.setMinimumSize(buttonSize);

        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);

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

        gbc.gridx = 0;
        gbc.gridy = 3;
        gridPanel.add(button7, gbc);

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

         button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Stopwatch stopwatch = new Stopwatch();
                    MatrixOperationClass operationTest = new MatrixOperationClass();
                    MatrixFileProcessor fileProcessor = new MatrixFileProcessor();
                    double[][] matrixA = fileProcessor.initFromFile("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\matrixA.txt");
                    double[][] matrixB = fileProcessor.initFromFile("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\matrixB.txt");
                    double[][] matrixC = new double[500][500];
                    String operation = (String) operationBox.getSelectedItem();

                    int ok=0;
                    long start=System.nanoTime();
                    switch(operation) {
                        case "Add":
                            matrixC=operationTest.sum(matrixA, matrixB);
                            ok=1;
                            break;
                        case "Multiply":
                            matrixC=operationTest.multiply(matrixA, matrixB);
                            ok=1;
                            break;
                        case "Subtract (for Matrix)":
                            matrixC=operationTest.subtract(matrixA, matrixB);
                            ok=1;
                            break;
                        default:
                            System.out.println("Invalid operation");
                            ok=-1;
                            break;
                    }
                    fileProcessor.displayMatrix(matrixC, "C:\\Users\\ionut\\Desktop\\DC\\DC-project\\printA.txt");
                    long end=System.nanoTime();

                    fileProcessor.displayMatrix(matrixC, "matrixResult.txt");
                    if(ok==1)
                    {
                        resultLabel.setText("Matrix result written to matrixResult.txt");
                    }
                    else if(ok==-1){
                        resultLabel.setText("This operation can't be performed");
                    }

                    double elapsedTime = stopwatch.getElapsedTime();
                    timeLabel.setText("Time: " + String.format("%.9f", elapsedTime) + " seconds");

                } catch (FileNotFoundException ex) {
                    resultLabel.setText("Error reading matrix files.");
                    ex.printStackTrace();
                } catch (Exception ex) {
                    resultLabel.setText("An error occurred.");
                    ex.printStackTrace();
                }
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Benchmark");
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new MainGUI();
    }
}
