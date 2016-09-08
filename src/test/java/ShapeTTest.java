package test.java;

import org.junit.Test;
import main.java.ShapeT;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShapeTTest {

    private ShapeT shape = new ShapeT();

    @Test
    public void testShapeWithoutRotation() {
        int[][] expectedShape = {
                {0, 6, 0},
                {6, 6, 6},
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
                {0, 6, 0},
                {0, 6, 6},
                {0, 6, 0}
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
                {6, 6, 6},
                {0, 6, 0}
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
                {0, 6, 0},
                {6, 6, 0},
                {0, 6, 0}
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
