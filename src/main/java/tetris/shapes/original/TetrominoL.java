package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoL extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1) };

    private static int color = 3;

    /**
     * Creates an L-shaped tetromino consisting of four minos.
     */
    public TetrominoL() {
        super(minos, color);
    }
}
