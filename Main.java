import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JTextPane;

public class Main {
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("Java Calculator");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon calculatorImg = new ImageIcon("imgs/calculator.jpg");
        Image image = calculatorImg.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        calculatorImg = new ImageIcon(newimg);

        JLabel label = new JLabel(calculatorImg);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(150, 100));

        label.setText("Java Calculator");
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setText("An excuse for me to test math methods in Java lol");
        textPane.setHorizontalAlignment(JTextPane.CENTER);
        textPane.setVerticalAlignment(JTextPane.BOTTOM);
        
        

        jFrame.add(label);
        jFrame.setVisible(true);
    }
    
  public static void main(String[] args) {
    createAndShowGUI();
  }
}
