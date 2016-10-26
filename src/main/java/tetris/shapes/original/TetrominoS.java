package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoS extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 1) };

    private static int color = 5;

    /**
     * Creates a S-shaped tetromino consisting of four minos.
     */
    public TetrominoS() {
        super(minos, color);
    }
}
