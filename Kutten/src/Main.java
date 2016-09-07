
import javax.swing.*;

public class Main{

    public static void main(String[] args) {

        JFrame frame = new JFrame("Custom Painting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.addKeyListener(new MyKeyListener());

        frame.add(panel);

        frame.setSize(500, 500);
        frame.setVisible(true);

        //make sure the JPanel has the focus
        panel.requestFocusInWindow();
    }
}