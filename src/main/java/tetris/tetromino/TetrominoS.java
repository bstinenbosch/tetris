package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoS extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 1) };

    private static int color = 5;

    /**
     * ShapeS is the figure that is dropped on the gameboard.
     *
     */
    public TetrominoS() {
        super(minos, color);
    }
}
