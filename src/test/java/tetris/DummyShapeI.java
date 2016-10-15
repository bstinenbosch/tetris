package tetris;

import tetris.tetromino.AbstractTetromino;

public class DummyShapeI extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
            new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0) };

    private static int color = 1;

    /**
     * Creates a DummyShapeO that is similar in shape as the O-shaped Tetromino
     * @param position coordinate where shape is registered in the grid
     */
    public DummyShapeI(Coordinate position) {
        super(position, minos, color);
    }

}
