package robot;

import java.util.Observer;

import tetris.Controller;
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
    public String setGameState(Grid grid, MovableShape tetromino, Controller controller);

    /**
     * resetSession tells the robot that the game was over and a new one is
     * about to commence.
     */
    public void resetSession();

}
