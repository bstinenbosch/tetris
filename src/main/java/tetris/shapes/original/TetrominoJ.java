package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public class TetrominoJ extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(0, 1),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1) };

    private static int color = 2;

    /**
     * Creates a J-shaped tetromino consisting of four minos.
     */
    public TetrominoJ() {
        super(minos, color);
    }

}
