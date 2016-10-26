package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoZ extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 1),
        new Coordinate(0, 1), new Coordinate(0, 0), new Coordinate(1, 0) };

    private static int color = 7;

    /**
     * ShapeZ is the figure that is dropped on the gameboard.
     *
     */
    public TetrominoZ() {
        super(minos, color);
    }

}
