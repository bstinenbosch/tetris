package robot;

import java.util.Observer;

import tetris.Action;
import tetris.Grid;
import tetris.shapes.decorators.MovableShape;

public interface IRobot extends Observer {
    /**
     * setGameState updates the robots' knowlegde of the current state of the
     * game.
     * 
     * @param grid
     *            the game grid.
     * @param tetromino
     *            the shape that is currently dropping.
     */
    public void setGameState(Grid grid, MovableShape tetromino);

    /**
     * getNextAction determines the next action given the current game state.
     * 
     * @return the next action to execute
     */
    public Action getNextAction();

    /**
     * resetSession tells the robot that the game was over and a new one is
     * about to commence.
     */
    public void resetSession();
}
