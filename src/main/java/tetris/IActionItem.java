package tetris;

import tetris.tetromino.AbstractTetromino;

import javax.activity.InvalidActivityException;

public interface IActionItem {

    /**
     * the attempt method attempts to perform the specified action..
     * 
     * @param tetromino
     *            with this tetromino
     * @param grid
     *            on this grid
     * @throws InvalidActivityException
     */
    boolean attempt(AbstractTetromino tetromino, Grid grid);
}
