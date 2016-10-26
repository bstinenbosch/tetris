package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoI extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0) };

    private static int color = 1;

    /**
     * Creates an I-shaped tetromino consisting of four minos.
     */
    public TetrominoI() {
        super(minos, color);
    }
}
