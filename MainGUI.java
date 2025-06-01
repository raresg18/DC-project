import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//import BenchmarkResult;


public class MainGUI {

    public class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            backgroundImage = new ImageIcon(fileName).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Scale the image to fit the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public MainGUI() {
        JFrame frame = new JFrame();
        frame.setContentPane(new BackgroundPanel("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\coolcpu.jpg"));
        frame.setLayout(new BorderLayout());

        frame.setSize(1000, 600);  // Set the size of the window (1000x600)
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        String[] operations = {"Add", "Multiply", "Divide", "Sin", "Cos", "Sqrt", "Subtract (for Matrix)",
                "Subtract (Fixed Point)",
                "AND (Fixed Point)",
                "OR (Fixed Point)",
                "XOR (Fixed Point)",
               };
        JComboBox<String> operationBox = new JComboBox<>(operations);

        String[] iterationsOptions = {"1", "100", "5000", "10000000", "50000000", "100000000", "200000000"};
        JComboBox<String> iterationsBox = new JComboBox<>(iterationsOptions);

        String[] fileCapacityOptions = {"512 MB", "1 GB", "2 GB", "3 GB"};
        JComboBox<String> fileCapacityBox = new JComboBox<>(fileCapacityOptions);

        // Create six buttons
        JButton button1 = new JButton("FIXED POINT OPERATIONS");
        JButton button2 = new JButton("FLOATING POINT OPERATIONS");
        JButton button3 = new JButton("MATRIX OPERATIONS");
        JButton button4 = new JButton("PI OPERATION");
        JButton button5 = new JButton("SORTING OPERATION");
        JButton button6 = new JButton("FILE PROCESSING OPERATION");
        JButton button7 = new JButton("THREADS OPERATION");
        JButton button8 = new JButton("RANDOM MATRIX OPERATION");

        // Set button sizes using setPreferredSize
        Dimension buttonSize = new Dimension(360, 40); // 80px wide and 30px tall
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        button6.setPreferredSize(buttonSize);
        button7.setPreferredSize(buttonSize);
        button8.setPreferredSize(buttonSize);

        // Set the minimum size for consistency
        button1.setMinimumSize(buttonSize);
        button2.setMinimumSize(buttonSize);
        button3.setMinimumSize(buttonSize);
        button4.setMinimumSize(buttonSize);
        button5.setMinimumSize(buttonSize);
        button6.setMinimumSize(buttonSize);
        button7.setMinimumSize(buttonSize);
        button8.setMinimumSize(buttonSize);

        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);

        JLabel resultLabel = new JLabel("Result: ");
        JLabel timeLabel = new JLabel("Time: ");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridBagLayout());
        gridPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 5, 10, 5); // Add some space around the buttons

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

        gbc.gridx = 2;
        gbc.gridy = 3;
        gridPanel.add(button8, gbc);

        // Panel for combo boxes (operations and iterations)
        JLabel label1 = new JLabel("Select Operation:");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Verdana", Font.BOLD, 22));

        JLabel label2 = new JLabel("Select Iterations:");
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Verdana", Font.BOLD, 22));

        JLabel label3 = new JLabel("Select File Capacity:");
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Verdana", Font.BOLD, 22));

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(3, 2, 10, 10));
        comboPanel.setOpaque(false);

        comboPanel.add(label1);
        comboPanel.add(operationBox);
        comboPanel.add(label2);
        comboPanel.add(iterationsBox);
        comboPanel.add(label3);
        comboPanel.add(fileCapacityBox);

        // Centering the text fields and labels below the buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2, 10, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(textField1);
        bottomPanel.add(textField2);
        bottomPanel.add(resultLabel);
        bottomPanel.add(timeLabel);

        // Add the gridPanel (with buttons), comboPanel, and bottomPanel to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(comboPanel, BorderLayout.NORTH);  // Place comboBox at the top (North)
        frame.add(bottomPanel, BorderLayout.SOUTH);  // Place text fields and results at the bottom (South)

        // Color all buttons
        Color buttonColor = new Color(70, 130, 180); // Steel Blue
        Color textColor = Color.WHITE;
        for (JButton button : new JButton[]{button1, button2, button3, button4, button5, button6, button7, button8}) {
            button.setBackground(buttonColor);
            button.setForeground(textColor);
            button.setFocusPainted(false); // remove ugly focus
            button.setFont(new Font("Arial", Font.BOLD, 16));
        }

        // ComboBox Font
        operationBox.setFont(new Font("Verdana", Font.PLAIN, 14));
        iterationsBox.setFont(new Font("Verdana", Font.PLAIN, 14));
        fileCapacityBox.setFont(new Font("Verdana", Font.PLAIN, 14));

        //  Label Font
        resultLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        resultLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        timeLabel.setForeground(Color.WHITE);

        // TextField Padding
        textField1.setBorder(BorderFactory.createCompoundBorder(
                textField1.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        textField2.setBorder(BorderFactory.createCompoundBorder(
                textField2.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        frame.setIconImage(new ImageIcon("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\icon.png").getImage());



      button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedIterations = (String) iterationsBox.getSelectedItem();
                    int numIterations = Integer.parseInt(selectedIterations.replace(",", "").trim());

                    Stopwatch stopwatch = new Stopwatch();
                    int num1 = Integer.parseInt(textField1.getText());
                    int num2 = Integer.parseInt(textField2.getText());
                    String operation = ((String) operationBox.getSelectedItem()).replace(" (Fixed Point)", "");

                    FixedPointIntegerBenchmark.NUM_ITERATIONS = numIterations;

                    stopwatch.start();

                    int result = 0;
                    int change = 0;
                    boolean validOperation = true;
                    String resultMessage = "";

                    switch (operation) {
                        case "Add":
                            result = FixedPointIntegerBenchmark.add(num1, num2);
                            break;
                        case "Subtract":
                            result = FixedPointIntegerBenchmark.subtract(num1, num2);
                            break;
                        case "Multiply":
                            result = FixedPointIntegerBenchmark.multiply(num1, num2);
                            break;
                        case "Divide":
                            int[] divisionResults = FixedPointIntegerBenchmark.divide(num1, num2);
                            result = divisionResults[0]; // Final result
                            change = divisionResults[1]; // Corrected integer change
                            break;
                        case "AND":
                            result = FixedPointIntegerBenchmark.andOperation(num1, num2);
                            break;
                        case "OR":
                            result = FixedPointIntegerBenchmark.orOperation(num1, num2);
                            break;
                        case "XOR":
                            result = FixedPointIntegerBenchmark.xorOperation(num1, num2);
                            break;
                        case "Sin":
                            resultMessage = "This is sin for " + num1;
                            break;
                        case "Cos":
                            resultMessage = "This is cos for " + num1;
                            break;
                        case "Sqrt":
                            resultMessage = "This is sqrt for " + num1;
                            break;
                        default:
                            validOperation = false;
                            resultLabel.setText("Selected operation is not supported for Fixed Point.");
                            break;
                    }

                    double elapsedTime = stopwatch.getElapsedTime();

                    if (validOperation) {
                        resultLabel.setText(resultMessage + " | Final Result: " + result +
                                " | Change: " + change);
                        timeLabel.setText("Time: " + String.format("%.9f", elapsedTime) + " seconds");
                    }

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
                    int ok=1;
                    stopwatch.start();
                    switch(operation) {
                        case "Add":
                            matrixC=operationTest.sum(matrixA, matrixB);
                            break;
                        case "Multiply":
                            matrixC=operationTest.multiply(matrixA, matrixB);
                            break;
                        case "Subtract (for Matrix)":
                            matrixC=operationTest.subtract(matrixA, matrixB);
                            break;
                        default:
                            System.out.println("Invalid operation");
                            ok=0;
                            break;
                    }
                    fileProcessor.displayMatrix(matrixC, "C:\\Users\\ionut\\Desktop\\DC\\DC-project\\printA.txt");

                    fileProcessor.displayMatrix(matrixC, "matrixResult.txt");
                    if(ok==1)
                    {
                        resultLabel.setText("Matrix result written to matrixResult.txt");
                        try {
                            File resultFile = new File("matrixResult.txt");
                            if (resultFile.exists()) {
                                Desktop.getDesktop().open(resultFile);
                            } else {
                                resultLabel.setText("Result file not found!");
                            }
                        } catch (IOException ex) {
                            resultLabel.setText("Failed to open result file.");
                            ex.printStackTrace();
                        }
                    }
                    else{
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

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    int no_digits = Integer.parseInt(textField1.getText());
                    PiSpigotBenchmarkOneThread piComputer = new PiSpigotBenchmarkOneThread();

                    timeLabel.setText("Time: "+piComputer.benchmarkComputation(no_digits)/1000.0 + "seconds");
                    resultLabel.setText("Computed "+no_digits+" digits of pi into computed_pi.txt.");
                    try {
                        Process proc = Runtime.getRuntime().exec("notepad.exe computed_pi.txt");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }



            }
        });


        button5.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();

          
            Sort sorter = new Sort();
            //String result = sorter.run();

            double elapsedTime = stopwatch.getElapsedTime();

           // resultLabel.setText("<html>" + result.replaceAll("\n", "<br>") + "</html>");
            timeLabel.setText("Time: " + String.format("%.9f", elapsedTime) + " seconds");

        } catch (Exception ex) {
            resultLabel.setText("An error occurred during sorting.");
            ex.printStackTrace();
        }
    }
});


        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Stopwatch stopwatch = new Stopwatch();
                    stopwatch.start();
                    String fileCapacity = (String) fileCapacityBox.getSelectedItem();
                    BigFileProcessor bfp = null;
                    int ok=1;
                    switch (fileCapacity) {
                        case "512 MB":
                            bfp = new BigFileProcessor(512);
                            break;
                        case "1 GB":
                            bfp = new BigFileProcessor(1024);
                            break;
                        case "2 GB":
                            bfp = new BigFileProcessor(2048);
                            break;
                        case "3 GB":
                            bfp = new BigFileProcessor(3072);
                            break;
                        default:
                            System.out.println("Invalid file capacity");
                            ok=0;
                            break;
                    }
                    if(ok==1){
                        bfp.readFile();
                        resultLabel.setText("Result: " + bfp.getTotalBytesRead() + " total read bytes");
                    }
                    else{
                        resultLabel.setText("Invalid file capacity");
                    }
                    double elapsedTime = stopwatch.getElapsedTime();
                    timeLabel.setText("Result: " + String.format("%.3f", elapsedTime) + " seconds");
                } catch (IOException ex) {
                    resultLabel.setText("An error reading and processing the file occurred.");
                    throw new RuntimeException(ex);
                }
            }
        });

        button8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int rows = Integer.parseInt(textField1.getText());
                    int cols = Integer.parseInt(textField2.getText());
                    Stopwatch stopwatch = new Stopwatch();
                    stopwatch.start();
                    MatrixFileProcessor fileProcessor = new MatrixFileProcessor();
                    File resultFile = new File("RandomMatrix.txt");
                    double[][] matrix = fileProcessor.generateRandomMatrix("C:\\Users\\ionut\\Desktop\\DC\\DC-project\\RandomMatrix.txt",rows,cols);
                    double elapsedTime = stopwatch.getElapsedTime();
                    resultLabel.setText("Matrix written to RandomMatrix.txt");
                    timeLabel.setText("Time: "+elapsedTime+" seconds");
                    if(resultFile.exists()){
                        Desktop.getDesktop().open(resultFile);
                    }
                    else{
                        resultLabel.setText("Result file not found!");
                    }

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mystery Inc. Benchmark");
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new MainGUI();
    }

}
