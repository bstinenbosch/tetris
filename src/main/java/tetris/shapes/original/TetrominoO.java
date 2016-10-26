package tetris.shapes.original;

import tetris.Coordinate;
import tetris.shapes.AbstractTetromino;

public class TetrominoO extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, -1) };

    private static int color = 4;

    /**
     * ShapeO is the figure that is dropped on the gameboard.
     *
     */
    public TetrominoO() {
        super(minos, color);
    }

}
