package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoZ extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 1),
        new Coordinate(0, 1), new Coordinate(0, 0), new Coordinate(1, 0) };

    private static int color = 7;

    /**
     * Creates a Z-shaped tetromino consisting of four minos.
     */
    public TetrominoZ() {
        super(minos, color);
    }

}
