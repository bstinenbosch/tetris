package tetris.shapes.custom;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class TrominoJ extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(-1, 0));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(0, -1));
                }
            });

    private static final int COLOR = 2;

    /**
     * Creates an I-shaped tetromino consisting of four minos.
     */
    public TrominoJ() {
        super(MINOS, COLOR);
    }
}