package test.java;

import org.junit.Test;
import main.java.ShapeL;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShapeLTest {

    private ShapeL shape = new ShapeL();

    @Test
    public void testShapeWithoutRotation() {
        int[][] expectedShape = {
                {0, 0, 3},
                {3, 3, 3},
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
                {0, 3, 0},
                {0, 3, 0},
                {0, 3, 3}
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
                {3, 3, 3},
                {3, 0, 0}
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
                {3, 3, 0},
                {0, 3, 0},
                {0, 3, 0}
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
