import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Stopwatch {
    private long startTime;

    public Stopwatch(){
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

class GUI {

    public GUI(){
        JFrame frame = new JFrame();


        JButton button = new JButton("ADD");

        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);

        JLabel resultLabel = new JLabel("Result: ");
        JLabel timeLabel = new JLabel("Time: ");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(270, 270, 90, 270));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(textField1);
        panel.add(textField2);
        panel.add(button);
        panel.add(resultLabel);
        panel.add(timeLabel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    //Stopwatch stopwatch = new Stopwatch();
                    int num1 = (int) Integer.parseInt(textField1.getText());
                    int num2 = (int) Integer.parseInt(textField2.getText());

                    //FIXED POINT INTEGER
                    BenchmarkResult result = FixedPointIntegerBenchmark.runBenchmark(num1, num2);
                    resultLabel.setText("Result: " + result.result);
                    timeLabel.setText("Time: " + String.format("%.9f", result.time) + " seconds");

                    /// WHAT WAS IN MAIN BEFORE ADDING THE LAST 6 LINES OF CODE(THE INITIAL TEMPLATE)
                    /**
                     Stopwatch stopwatch = new Stopwatch();
                     double num1 = (double) Integer.parseInt(textField1.getText());
                     double num2 = (double) Integer.parseInt(textField2.getText());stopwatch.start();

                     double resultSum = num1 + num2;
                     double elapsedTime = stopwatch.getElapsedTime();
                     String resultTime = (String.format("%.9f", elapsedTime));

                    timeLabel.setText("Time: " + resultTime);
                    resultLabel.setText("Result: " + resultSum);
                     */

                     //MATRIX MULTIPLICATION
        

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers.");
                }
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Benchmark");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();

    }
}
