package main.java;

//import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class TetrisKeyListener implements KeyListener {

	private EventHandler<KeyEvent> onKeyDown;
	private EventHandler<KeyEvent> onKeyUp;
	private EventHandler<KeyEvent> onKeyLeft;
	private EventHandler<KeyEvent> onKeyRight;

    public void keyTyped(KeyEvent e) {
        //nothing will happen
    }

    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()){
    	case KeyEvent.VK_UP:
    		onKeyUp.handle(e);
    	}

    }

    public void keyReleased(KeyEvent e) {
        //nothing will happen
    }
}
