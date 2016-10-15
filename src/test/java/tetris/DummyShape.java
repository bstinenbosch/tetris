package tetris;

import tetris.tetromino.AbstractTetromino;

public class DummyShape extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(0, 0),
        new Coordinate(0, 1), new Coordinate(1, 0), new Coordinate(1, 1) };

    private static int color = 1;

    /**
     * Creates a DummyShape that is similar in shape as the O-shaped Tetromino
     * @param position coordinate where shape is registered in the grid
     */
    public DummyShape(Coordinate position) {
        super(position, minos, color);
    }

}
