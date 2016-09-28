package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoJ extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 1),
        new Coordinate(-1, 0), new Coordinate(0, 0), new Coordinate(1, 0) };

    private static int color = 2;

    /**
     * ShapeJ is the figure that is dropped on the gameboard.
     * 
     * @param position
     *            Position of shape in grid
     */
    public TetrominoJ(Coordinate position) {
        super(position, minos, color);
    }

}
