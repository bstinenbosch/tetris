package tetris.shapes.custom;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class TrominoI extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(-1, 0));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(1, 0));
                }
            });

    private static final int COLOR = 4;

    /**
     * Creates an I-shaped tetromino consisting of four minos.
     */
    public TrominoI() {
        super(MINOS, COLOR);
    }
}