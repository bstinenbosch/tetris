package tetris;

import org.junit.Test;

import tetris.tetromino.AbstractTetromino;

import static org.junit.Assert.assertEquals;

public class AbstractShapeTest {

    private AbstractTetromino shape = new DummyShape(new Coordinate(0, 0));

    @Test
    public void test_topmost_coordinate() {
        assertEquals(shape.top(), 1);
    }

    @Test
    public void test_bottommost_coordinate() {
        assertEquals(shape.bottom(), 0);
    }

    @Test
    public void test_leftmost_coordinate() {
        assertEquals(shape.left(), -1);
    }

    @Test
    public void test_rightmost_coordinate() {
        assertEquals(shape.right(), 1);
    }

}
