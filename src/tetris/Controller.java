package tetris;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Custom Painting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.addKeyListener(new MyKeyListener()); //Hiermee laad je de KeyListener in!!

        frame.add(panel);

        frame.setSize(500, 500);
        frame.setVisible(true);

        //make sure the JPanel has the focus
        panel.requestFocusInWindow();
	}
	
	public void keyTyped (KeyEvent e){
		//nothing will happen
	}
s
	public void keyPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_D){
			System.out.println("Hoppa het werkt");
		}
		else if(keyCode == KeyEvent.VK_E){
			System.out.println("Hoppa het werkt");
		}
		else if(keyCode == KeyEvent.VK_C){
			System.out.println("Hoppa het werkt");
		}
	}
	
	public void keyReleased (KeyEvent e){
		//nothing will happen
	}

}
