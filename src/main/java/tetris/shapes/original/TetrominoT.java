package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractTetromino;

public class TetrominoT extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 0) };

    private static int color = 6;

    /**
     * ShapeT is the figure that is dropped on the gameboard.
     *
     */
    public TetrominoT() {
        super(minos, color);
    }
}
