// Import the Swing library for the UI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Define the Calculator class that extends JFrame
public class Calculator extends JFrame {

  // Declare the UI components as private fields
  private JTextField textField; // The text field to display the result
  private JButton[] buttons; // The buttons for the digits and operators
  private String[] labels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"}; // The labels for the buttons
  private double result = 0; // The result of the calculation
  private String operator = "="; // The current operator
  private boolean startOfNumber = true; // A flag to indicate the start of a new number

  // Define the constructor of the Calculator class
  public Calculator() {
      // Set the title, size, layout and default close operation of the frame
      setTitle("Basic Calculator");
      setSize(300, 300);
      setLayout(new BorderLayout());
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create and add the text field to the frame
      textField = new JTextField("0");
      textField.setHorizontalAlignment(JTextField.RIGHT);
      add(textField, BorderLayout.NORTH);

      // Create and add a panel to hold the buttons to the frame
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(4, 4));
      add(panel, BorderLayout.CENTER);

      // Create an array of buttons and add them to the panel
      buttons = new JButton[16];
      for (int i = 0; i < 16; i++) {
          buttons[i] = new JButton(labels[i]);
          panel.add(buttons[i]);
      }

      // Add an action listener to each button
      for (int i = 0; i < 16; i++) {
          buttons[i].addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  // Get the source of the action event
                  JButton source = (JButton) e.getSource();
                  // Get the label of the source button
                  String label = source.getText();
                  // Perform different actions based on the label
                  switch (label) {
                      case "+":
                      case "-":
                      case "*":
                      case "/":
                          calculate(Double.parseDouble(textField.getText())); // Calculate the result with the previous operator and number
                          operator = label; // Update the current operator
                          startOfNumber = true; // Set the flag to true for a new number
                          break;
                      case "=":
                          calculate(Double.parseDouble(textField.getText())); // Calculate the result with the previous operator and number
                          operator = "="; // Reset the current operator to =
                          startOfNumber = true; // Set the flag to true for a new number
                          break;
                      case ".":
                          if (startOfNumber) { // If it is the start of a new number, set the text field to 0.
                              textField.setText("0.");
                              startOfNumber = false; // Set the flag to false
                          } else if (!textField.getText().contains(".")) { // If it is not the start of a new number and there is no . in the text field, append . to it
                              textField.setText(textField.getText() + ".");
                          }
                          break;
                      default: // For digits from 0 to 9
                          if (startOfNumber) { // If it is the start of a new number, set the text field to the digit
                              textField.setText(label);
                              startOfNumber = false; // Set the flag to false
                          } else { // If it is not the start of a new number, append the digit to the text field
                              textField.setText(textField.getText() + label);
                          }
                  }
              }
          });
      }

      // Make the frame visible
      setVisible(true);
  }

  // Define a method to calculate the result based on the operator and number
  private void calculate(double number) {
      switch (operator) {
          case "+":
              result += number;
              break;
          case "-":
              result -= number;
              break;
          case "*":
              result *= number;
              break;
          case "/":
              result /= number;
              break;
          case "=":
              result = number;
              break;
      }
      textField.setText("" + result); // Set the text field to display the result
  }

  // Define a main method to create an instance of Calculator class
  public static void main(String[] args) {
      Calculator calculator = new Calculator();
  }
}