package tetris;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class DummyShapeRotationTests extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(0, 0));
                    add(new Coordinate(0, 1));
                    add(new Coordinate(0, 2));
                    add(new Coordinate(1, 1));
                }
            });

    private static int color = 1;

    public DummyShapeRotationTests() {
        super(MINOS, color);
    }

}
