package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoJ extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(0, 1),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1) };

    private static int color = 2;

    /**
     * ShapeJ is the figure that is dropped on the gameboard.
     *
     */
    public TetrominoJ() {
        super(minos, color);
    }

}
