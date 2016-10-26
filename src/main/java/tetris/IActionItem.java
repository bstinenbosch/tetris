package tetris;

import tetris.shapes.decorators.MovableShape;

import javax.activity.InvalidActivityException;

public interface IActionItem {

    /**
     * the attempt method attempts to perform the specified action..
     * 
     * @param tetromino
     *            with this shape
     * @param grid
     *            on this grid
     * @throws InvalidActivityException
     */
    boolean attempt(MovableShape tetromino, Grid grid);
}
