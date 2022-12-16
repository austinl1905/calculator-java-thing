import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextPane;
import javax.swing.text.*;
import java.awt.event.*;

public class Main {
  private static void createAndShowCalculator() {
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
    textPane.setText("don't use more than two operands at once I beg of you");
    StyledDocument doc = textPane.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    textPane.setPreferredSize(new Dimension(100, 75));
    textPane.setBorder(lineBorder);
    textPanePanel.add(textPane);
    
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

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton getInputButton = new JButton("Submit");
        getInputButton.setPreferredSize(new Dimension(100, 25));
        getInputButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String input = textField.getText();
        // yeah I have no idea what goes on here either
        String[] substrings = input.split("[+\\-*/\\^]");
        
        int operand1 = Integer.parseInt(substrings[0]);
        int operand2 = Integer.parseInt(substrings[1]);
        char operator = input.charAt(substrings[0].length());
        int result = 0;
        switch (operator) {
          case '+':
            result = operand1 + operand2;
            break;
          case '-':
            result = operand1 - operand2;
            break;
          case '*':
            result = operand1 * operand2;
            break;
          case '/':
            result = operand1 / operand2;
            break;
          case '^':
            result = (int) Math.pow(operand1, operand2);
        }
        outputLabel.setText(Integer.toString(result));
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
    createAndShowCalculator();
  }
}
