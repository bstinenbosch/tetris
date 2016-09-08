package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {

    public void keyTyped(KeyEvent e) {
        //nothing will happen
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_D) {
            System.out.println("Hoppa het werkt");
        } else if (keyCode == KeyEvent.VK_E) {
            System.out.println("Hoppa het werkt");
        } else if (keyCode == KeyEvent.VK_C) {
            System.out.println("Hoppa het werkt");
        }
    }

    public void keyReleased(KeyEvent e) {
        //nothing will happen
    }
}
