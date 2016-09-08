package tetris;

import java.awt.event.*;
import javax.swing.*;

public class Controller {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Custom Painting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.addKeyListener(new TetrisKeyListener()); //Hiermee laad je de KeyListener in!!

        frame.add(panel);

        frame.setSize(500, 500);
        frame.setVisible(true);

        //make sure the JPanel has the focus
        panel.requestFocusInWindow();
	}

}
	
