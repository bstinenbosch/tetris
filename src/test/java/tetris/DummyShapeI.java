package tetris;

import tetris.shapes.AbstractShape;

public class DummyShapeI extends AbstractShape {

    private static final Coordinate[] minos = new Coordinate[] { new Coordinate(-1, 0),
            new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0) };

    private static int color = 1;

    /**
     * Creates a DummyShapeO that is similar in shape as the O-shaped Tetromino
     */
    public DummyShapeI() {
        super(minos, color);
    }

}
