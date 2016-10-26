package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoT extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 0) };

    private static int color = 6;

    /**
     * Creates a T-shaped tetromino consisting of four minos.
     */
    public TetrominoT() {
        super(minos, color);
    }
}
