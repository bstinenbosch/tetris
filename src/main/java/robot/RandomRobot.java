package robot;

import java.util.Observable;

import tetris.Action;
import tetris.Controller;
import tetris.Grid;
import tetris.shapes.decorators.MovableShape;

import javafx.application.Platform;

public class RandomRobot implements IRobot {
    @Override
    public String setGameState(Grid grid, MovableShape tetromino, Controller controller) {
        // This robot doesnt care about the game state. However, he has ADHD so
        // we regularly put him to sleep.
        Platform.runLater(
            () -> Action.values()[(int) (Math.random() * 5)].attempt(tetromino, grid, controller));
        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            System.out.println("The random robot got disturbed while sleeping.");
            exception.printStackTrace();
        }
        return "";
    }

    @Override
    public void resetSession() {
        // Nothing to reset because RANDOM

    }

    @Override
    public void update(Observable o, Object arg) {
        // do nothing: randomrobot doesn't care about scores.
    }

}
