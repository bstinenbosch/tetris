package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoO extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, -1) };

    private static int color = 4;

    /**
     * Creates an O-shaped tetromino consisting of four minos.
     */
    public TetrominoO() {
        super(minos, color);
    }

}
