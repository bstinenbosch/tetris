package robot;

import java.util.Observable;

import tetris.Controller.Score;
import tetris.Grid;
import tetris.tetromino.AbstractTetromino;

public class RandomRobot implements IRobot {

    private int score;

    public void setGameState(Grid grid, AbstractTetromino tetromino) {
        // This robot doesnt care about the game state. However, he has ADHD so
        // we regularly put him to sleep.
        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            System.out.println("The random robot got disturbed while sleeping.");
            exception.printStackTrace();
        }

    }

    public int getNextAction() {
        return (int) (Math.random() * 5);
    }

    public void resetSession() {
        // Nothing to reset because RANDOM

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Score && arg instanceof Integer) {
            score = (int) arg;
        } else {
            throw new UnsupportedOperationException(
                "RandomRobot instance received an update from an unknown source.");
        }
    }

}
