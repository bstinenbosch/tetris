package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoI extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0) };

    /**
     * ShapeI is the figure that is dropped on the gameboard.
     * 
     * @param position
     *            Position of shape in grid
     */
    public TetrominoI(Coordinate position) {
        super(position, minos);
    }
}
