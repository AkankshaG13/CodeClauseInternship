import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

public class ScientificCalculator {
    private JFrame frame;
    private JTextField display;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator().createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));

        
        addButton(buttonPanel, "sin");
        addButton(buttonPanel, "cos");
        addButton(buttonPanel, "tan");
        addButton(buttonPanel, "sqrt");
        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "/");
        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addButton(buttonPanel, "*");
        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addButton(buttonPanel, "-");
        addButton(buttonPanel, "0");
        addButton(buttonPanel, ".");
        addButton(buttonPanel, "=");
        addButton(buttonPanel, "+");
        addButton(buttonPanel, "CLR");

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addButton(JPanel panel, String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                handleButtonClick(command);
            }
        });
        panel.add(button);
    }

    private void handleButtonClick(String command) {
        String currentText = display.getText();

        if (command.equals("=")) {
            try {
                double result = evaluateExpression(currentText);
                display.setText(Double.toString(result));
            } catch (Exception e) {
                display.setText("Error");
            }
        } else if (command.equals("sqrt")) {
            try {
                double result = Math.sqrt(Double.parseDouble(currentText));
                display.setText(Double.toString(result));
            } catch (Exception e) {
                display.setText("Error");
            }
        } else if (command.equals("CLR")) { // Handle CLR button
            display.setText("");
        } else {
            display.setText(currentText + command);
        }
    }

    private double evaluateExpression(String expression) {
        try {
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                double parseExpression() {
                    double x = parseTerm();
                    for (;;) {
                        if      (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                        }
                        }
                        double parseTerm() {
                            double x = parseFactor();
                            for (;;) {
                                if      (eat('*')) x *= parseFactor(); // multiplication
                                else if (eat('/')) x /= parseFactor(); // division
                                else return x;
                            }
                        }
            
                        double parseFactor() {
                            if (eat('+')) return parseFactor(); // unary plus
                            if (eat('-')) return -parseFactor(); // unary minus
            
                            double x;
                            int startPos = this.pos;
                            if (eat('(')) { // parentheses
                                x = parseExpression();
                                eat(')');
                            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                                x = Double.parseDouble(expression.substring(startPos, this.pos));
                            } else if (ch >= 'a' && ch <= 'z') { // functions
                                while (ch >= 'a' && ch <= 'z') nextChar();
                                String func = expression.substring(startPos, this.pos);
                                x = parseFactor();
                                if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                                else throw new RuntimeException("Unknown function: " + func);
                            } else {
                                throw new RuntimeException("Unexpected: " + (char) ch);
                            }
            
                            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
            
                            return x;
                        }
                    }.parse();
                } catch (Exception e) {
                    throw new RuntimeException("Error evaluating expression: " + expression);
                }
            }
        }            