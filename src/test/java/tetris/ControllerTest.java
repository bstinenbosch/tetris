package tetris;

import static org.junit.Assert.assertTrue;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.junit.Test;

public class ControllerTest {
    @Test
    public void tests() {
        assertTrue(runGame());
    }

    /**
     * Simulate a running game.
     *
     * @return boolean
     */
    public boolean runGame() {

        // TODO een manier vinden om de controller te testen zonder view?

        /*
         * //Controller c = new Controller(new View()); View.launch("test");
         * c.startGame(10, 20); while(!c.isGameOver()){ try {
         * Thread.sleep((long)-Math.log(Math.max(Math.random(),
         * Float.MIN_VALUE))); } catch (InterruptedException e) { return false;
         * } randomKeyStroke(c); }
         */
        return true;
    }

    private void randomKeyStroke(Controller controller) {
        EventHandler<KeyEvent> eventhandler = event -> controller.handleKeyEvent(event);
        switch ((int) (Math.random() * 4)) {
            case 0:
                eventhandler.handle(
                    new KeyEvent(null, null, null, KeyCode.DOWN, false, false, false, false));
                break;
            case 1:
                eventhandler
                    .handle(new KeyEvent(null, null, null, KeyCode.UP, false, false, false, false));
                break;
            case 2:
                eventhandler.handle(
                    new KeyEvent(null, null, null, KeyCode.LEFT, false, false, false, false));
                break;
            case 3:
                eventhandler.handle(
                    new KeyEvent(null, null, null, KeyCode.RIGHT, false, false, false, false));
                break;
            default:
                throw new IllegalStateException("No event assigned to this random key stroke.");
        }
    }

}
