package tetris;

import org.junit.Test;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import tetris.Controller;

import static org.junit.Assert.assertTrue;

public class ControllerTest {
	@Test
	public void tests(){
		assertTrue(runGame());
	}
	
	public boolean runGame(){
		
		// TODO een manier vinden om de controller te testen zonder view?
		
		/*
		//Controller c = new Controller(new View());
		View.launch("test");
		c.startGame(10, 20);
		while(!c.isGameOver()){
			try {
				Thread.sleep((long)-Math.log(Math.max(Math.random(), Float.MIN_VALUE)));
			} catch (InterruptedException e) {
				return false;
			}
			randomKeyStroke(c);
		}*/
		return true;
	}


	private void randomKeyStroke(Controller c){
		EventHandler<KeyEvent> eventhandler = c.getOnKeyPressed();
		switch((int) (Math.random() * 4)){
		case 0:	
			eventhandler.handle(new KeyEvent(null, null, null, KeyCode.DOWN, false, false, false, false));
			break;
		case 1:	
			eventhandler.handle(new KeyEvent(null, null, null, KeyCode.UP, false, false, false, false));
			break;
		case 2:	
			eventhandler.handle(new KeyEvent(null, null, null, KeyCode.LEFT, false, false, false, false));
			break;
		case 3:	
			eventhandler.handle(new KeyEvent(null, null, null, KeyCode.RIGHT, false, false, false, false));
			break;			
		}
	}

}
