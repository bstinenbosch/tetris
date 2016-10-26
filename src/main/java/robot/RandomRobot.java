package robot;

import java.util.Observable;

import tetris.Action;
import tetris.Grid;
import tetris.tetromino.MovableTetromino;

public class RandomRobot implements IRobot {
    @Override
    public void setGameState(Grid grid, MovableTetromino tetromino) {
        // This robot doesnt care about the game state. However, he has ADHD so
        // we regularly put him to sleep.
        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            System.out.println("The random robot got disturbed while sleeping.");
            exception.printStackTrace();
        }

    }

    @Override
    public Action getNextAction() {
        return Action.values()[(int) (Math.random() * 5)];
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
