package tetris;

import tetris.tetromino.AbstractTetromino;

public class DummyShape extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
        new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 1) };

    public DummyShape(Coordinate position) {
        super(position, minos);
    }

}
