package tetris;

import tetris.tetromino.AbstractTetromino;

import org.junit.Test;
import tetris.tetromino.MovableTetromino;

import static org.junit.Assert.assertEquals;

public class AbstractShapeTest {

    private MovableTetromino shape = new MovableTetromino(new DummyShapeRotationTests(), new Coordinate(0, 0));

    @Test
    public void test_topmost_coordinate() {
        assertEquals(shape.top(), 2);
    }

    @Test
    public void test_bottommost_coordinate() {
        assertEquals(shape.bottom(), 0);
    }

    @Test
    public void test_leftmost_coordinate() {
        assertEquals(shape.left(), 0);
    }

    @Test
    public void test_rightmost_coordinate() {
        assertEquals(shape.right(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_90_degrees_clockwise() {
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), 1);

        assertEquals(shape.get(1).getX(), 1);
        assertEquals(shape.get(1).getY(), 1);

        assertEquals(shape.get(2).getX(), 2);
        assertEquals(shape.get(2).getY(), 1);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), 0);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_180_degrees_clockwise() {
        shape.rotateRight();
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 1);
        assertEquals(shape.get(0).getY(), 1);

        assertEquals(shape.get(1).getX(), 1);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 1);
        assertEquals(shape.get(2).getY(), -1);

        assertEquals(shape.get(3).getX(), 0);
        assertEquals(shape.get(3).getY(), 0);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_270_degrees_clockwise() {
        shape.rotateRight();
        shape.rotateRight();
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 1);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), -1);
        assertEquals(shape.get(2).getY(), 0);

        assertEquals(shape.get(3).getX(), 0);
        assertEquals(shape.get(3).getY(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_360_degrees_clockwise() {
        shape.rotateRight();
        shape.rotateRight();
        shape.rotateRight();
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 1);

        assertEquals(shape.get(2).getX(), 0);
        assertEquals(shape.get(2).getY(), 2);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_90_degrees_counterclockwise() {
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 1);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), -1);
        assertEquals(shape.get(2).getY(), 0);

        assertEquals(shape.get(3).getX(), 0);
        assertEquals(shape.get(3).getY(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_180_degrees_counterclockwise() {
        shape.rotateLeft();
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 1);
        assertEquals(shape.get(0).getY(), 1);

        assertEquals(shape.get(1).getX(), 1);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 1);
        assertEquals(shape.get(2).getY(), -1);

        assertEquals(shape.get(3).getX(), 0);
        assertEquals(shape.get(3).getY(), 0);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_270_degrees_counterclockwise() {
        shape.rotateLeft();
        shape.rotateLeft();
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), 1);

        assertEquals(shape.get(1).getX(), 1);
        assertEquals(shape.get(1).getY(), 1);

        assertEquals(shape.get(2).getX(), 2);
        assertEquals(shape.get(2).getY(), 1);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), 0);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_360_degrees_counterclockwise() {
        shape.rotateLeft();
        shape.rotateLeft();
        shape.rotateLeft();
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 1);

        assertEquals(shape.get(2).getX(), 0);
        assertEquals(shape.get(2).getY(), 2);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), 1);
    }

}
