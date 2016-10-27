package tetris.shapes.original;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class TetrominoZ extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(-1, 1));
                    add(new Coordinate(0, 1));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(1, 0));
                }
            });

    private static final int COLOR = 7;

    /**
     * Creates a Z-shaped tetromino consisting of four minos.
     */
    public TetrominoZ() {
        super(MINOS, COLOR);
    }

}
