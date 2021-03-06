package tetris.shapes.original;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

public class TetrominoS extends AbstractShape {

    private static final CoordinateSet MINOS = new CoordinateSet(
            new ArrayList<Coordinate>() {
                {
                    add(new Coordinate(-1, 0));
                    add(new Coordinate(0, 0));
                    add(new Coordinate(0, 1));
                    add(new Coordinate(1, 1));
                }
            });

    private static final int COLOR = 5;

    /**
     * Creates a S-shaped tetromino consisting of four minos.
     */
    public TetrominoS() {
        super(MINOS, COLOR);
    }
}
