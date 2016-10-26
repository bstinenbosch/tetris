package tetris;

import tetris.tetromino.AbstractTetromino;

public class DummyShapeRotationTests extends AbstractTetromino {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(0, 0),
        new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(1, 1) };

    private static int color = 1;

    public DummyShapeRotationTests() {
        super(minos, color);
    }

}
