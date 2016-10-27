package tetris.shapes.original;

import common.CoordinateSet;
import common.Coordinate;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class TetrominoI extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(-1, 0));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(1, 0));
                    add(new Coordinate(2, 0));
                }
            });

    private static final int COLOR = 1;

    /**
     * Creates an I-shaped tetromino consisting of four minos.
     */
    public TetrominoI() {
        super(MINOS, COLOR);
    }
}
