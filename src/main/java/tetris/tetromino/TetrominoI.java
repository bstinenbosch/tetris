package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoI extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0) };

    private static int color = 1;

    /**
     * ShapeI is the figure that is dropped on the gameboard.
     *
     */
    public TetrominoI() {
        super(minos, color);
    }
}
