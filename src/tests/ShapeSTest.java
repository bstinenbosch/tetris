package tests;

import org.junit.Test;
import tetris.ShapeS;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShapeSTest {

    private ShapeS shape = new ShapeS();

    @Test
    public void testShapeWithoutRotation() {
        int[][] expectedShape = {
                {0, 5, 5},
                {5, 5, 0},
                {0, 0, 0}
        };

        int[][] actualShape = shape.getShape(0);

        // check height
        assertEquals(expectedShape.length, actualShape.length, 0);
        // check width
        assertEquals(expectedShape[1].length, actualShape[1].length, 0);
        // check each 'cell'
        assertEquals(Arrays.deepEquals(expectedShape, actualShape), true);
    }

    @Test
    public void testShapeWith90DegreesRotation() {
        int[][] expectedShape = {
                {0, 5, 0},
                {0, 5, 5},
                {0, 0, 5}
        };

        int[][] actualShape = shape.getShape(1);

        // check height
        assertEquals(expectedShape.length, actualShape.length, 0);
        // check width
        assertEquals(expectedShape[1].length, actualShape[1].length, 0);
        // check each 'cell'
        assertEquals(Arrays.deepEquals(expectedShape, actualShape), true);
    }

    @Test
    public void testShapeWith180DegreesRotation() {
        int[][] expectedShape = {
                {0, 0, 0},
                {0, 5, 5},
                {5, 5, 0}
        };

        int[][] actualShape = shape.getShape(2);

        // check height
        assertEquals(expectedShape.length, actualShape.length, 0);
        // check width
        assertEquals(expectedShape[1].length, actualShape[1].length, 0);
        // check each 'cell'
        assertEquals(Arrays.deepEquals(expectedShape, actualShape), true);
    }

    @Test
    public void testShapeWith270DegreesRotation() {
        int[][] expectedShape = {
                {5, 0, 0},
                {5, 5, 0},
                {0, 5, 0}
        };

        int[][] actualShape = shape.getShape(3);

        // check height
        assertEquals(expectedShape.length, actualShape.length, 0);
        // check width
        assertEquals(expectedShape[1].length, actualShape[1].length, 0);
        // check each 'cell'
        assertEquals(Arrays.deepEquals(expectedShape, actualShape), true);
    }

}
