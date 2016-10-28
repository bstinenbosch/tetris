package tetris.shapes.original;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class TetrominoT extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(-1, 0));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(0, 1));
                    add(new Coordinate(1, 0));
                }
            });

    private static final int COLOR = 6;

    /**
     * Creates a T-shaped tetromino consisting of four minos.
     */
    public TetrominoT() {
        super(MINOS, COLOR);
    }
}
