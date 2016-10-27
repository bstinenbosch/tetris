package tetris;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class DummyShapeO extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(0, 1));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(1, 0));
                    add(new Coordinate(1, 1));
                }
            });

    private static int color = 1;

    /**
     * Creates a DummyShapeO that is similar in shape as the O-shaped Tetromino
     */
    public DummyShapeO() {
        super(MINOS, color);
    }

}
