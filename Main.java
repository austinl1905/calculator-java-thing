import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextPane;
import javax.swing.text.*;
import java.awt.event.*;
import java.util.Stack;
import java.lang.Character;



public class Main {
// method to check if a character is a letter or a digit
  private static boolean letterOrDigit(char c) {
    if (Character.isLetterOrDigit(c))
      return true;
    else
      return false;
  }
// method to get the precedence of operators
  static int getPrecedence(char ch) {
    switch (ch) {
      case ('+'):
      case ('-'):
        return 1;
      case ('*'):
      case ('/'):
        return 2;
      case ('^'):
        return 3;
      default:
        return -1;
    }
  }
// method to check if expression has left associativity
  static boolean hasLeftAssociativity(char ch) {
    if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
      return true;
    } else {
      return false;
    }
  }

  static String infixToRpn(String expression) {
    // i copied this from geeksforgeeks dont judge me
    // Initialising an empty String
    // (for output) and an empty stack
    Stack<Character> stack = new Stack<>();

    // Initially empty string taken
    String output = new String("");

    // Iterating over tokens using inbuilt
    // .length() function
    for (int i = 0; i < expression.length(); ++i) {
      // Finding character at 'i'th index
      char c = expression.charAt(i);

      // If the scanned Token is an
      // operand, add it to output
      if (letterOrDigit(c))
        output += c;

      // If the scanned Token is an '('
      // push it to the stack
      else if (c == '(')
        stack.push(c);

      // If the scanned Token is an ')' pop and append
      // it to output from the stack until an '(' is
      // encountered
      else if (c == ')') {
        while (!stack.isEmpty()
            && stack.peek() != '(')
          output += stack.pop();

        stack.pop();
      }

      // If an operator is encountered then taken the
      // further action based on the precedence of the
      // operator

      else {
        while (!stack.isEmpty()
            && getPrecedence(c) <= getPrecedence(stack.peek())
            && hasLeftAssociativity(c)) {
          // peek() inbuilt stack function to
          // fetch the top element(token)

          output += stack.pop();
        }
        stack.push(c);
      }
    }

    // pop all the remaining operators from
    // the stack and append them to output
    while (!stack.isEmpty()) {
      if (stack.peek() == '(')
        return "This expression is invalid";
      output += stack.pop();
    }
    return output;
  }

  private static void createAndShowCalculator() {
    // create the main frame with grid layout
    JFrame jFrame = new JFrame("Java Calculator");
    jFrame.setLayout(new GridLayout(3, 3));
    jFrame.setSize(500, 360);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel labelPanel = new JPanel(new FlowLayout());
    ImageIcon calculatorImg = new ImageIcon("imgs/calculator.jpg");
    Image image = calculatorImg.getImage();
    Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
    calculatorImg = new ImageIcon(newimg);

    JLabel label = new JLabel(calculatorImg);

    Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
    Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    Border compoundBorder = new CompoundBorder(lineBorder, emptyBorder);

    label.setBorder(compoundBorder);
    label.setPreferredSize(new Dimension(150, 100));
    label.setText("Java Calculator");
    label.setVerticalTextPosition(SwingConstants.BOTTOM);
    label.setHorizontalTextPosition(SwingConstants.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    labelPanel.add(label);

    JPanel textPanePanel = new JPanel(new FlowLayout());
    JTextPane textPane = new JTextPane();
    textPane.setEditable(false);
    textPane.setText("This calculator puts your expressions into RPN form");
    StyledDocument doc = textPane.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    textPane.setPreferredSize(new Dimension(100, 75));
    textPane.setBorder(lineBorder);
    textPanePanel.add(textPane);

// add button that goes to the calculator frame
    
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JButton button = new JButton();
    button.setText("Start");
    button.setPreferredSize(new Dimension(50, 25));
    button.setHorizontalAlignment(JLabel.CENTER);
    button.setVerticalAlignment(JLabel.CENTER);
    button.setBorder(lineBorder);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame secondFrame = new JFrame("Java Calculator");
        secondFrame.setSize(500, 360);
        secondFrame.setLayout(new FlowLayout());
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel textFieldPanel = new JPanel(new FlowLayout());
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 20));
        textFieldPanel.add(textField);

        JPanel labelPanel = new JPanel(new FlowLayout());
        JLabel outputLabel = new JLabel();
        labelPanel.add(outputLabel);

        // add button to accept user input

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton getInputButton = new JButton("Submit");
        getInputButton.setPreferredSize(new Dimension(100, 25));
        getInputButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String expression = textField.getText();
            // output expression in reverse polish notation
            outputLabel.setText(infixToRpn(expression));
          }
        });
        buttonPanel.add(getInputButton);

        secondFrame.add(textFieldPanel);
        secondFrame.add(buttonPanel);
        secondFrame.add(labelPanel);

        secondFrame.setVisible(true);
      }
    });
    buttonPanel.add(button);
// add empty labels (this was only necessary for grid layout but i cant be bothered to remove it now)
    jFrame.add(new JLabel());
    jFrame.add(labelPanel);
    for (int i = 0; i < 2; i++) {
      jFrame.add(new JLabel());
    }
    jFrame.add(textPanePanel);
    for (int i = 0; i < 2; i++) {
      jFrame.add(new JLabel());
    }
    jFrame.add(buttonPanel);
    jFrame.add(new JLabel());

    jFrame.setVisible(true);
  }

  public static void main(String[] args) {
    // self explanatory 
    createAndShowCalculator();
  }
}
