package tetris;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractShapeTest {

    @Test
    public void test_topmost_coordinate() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));

        assertEquals(shape.top(), 1);
    }

    @Test
    public void test_bottommost_coordinate() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));

        assertEquals(shape.bottom(), 0);
    }

    @Test
    public void test_leftmost_coordinate() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));

        assertEquals(shape.left(), -1);
    }

    @Test
    public void test_rightmost_coordinate() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));

        assertEquals(shape.right(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_90_degrees_clockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), 1);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 1);
        assertEquals(shape.get(2).getY(), 0);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), -1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_180_degrees_clockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateRight();
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 1);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 0);
        assertEquals(shape.get(2).getY(), -1);

        assertEquals(shape.get(3).getX(), -1);
        assertEquals(shape.get(3).getY(), -1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_270_degrees_clockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateRight();
        shape.rotateRight();
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), -1);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), -1);
        assertEquals(shape.get(2).getY(), 0);

        assertEquals(shape.get(3).getX(), -1);
        assertEquals(shape.get(3).getY(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_360_degrees_clockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateRight();
        shape.rotateRight();
        shape.rotateRight();
        shape.rotateRight();

        assertEquals(shape.get(0).getX(), -1);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 0);
        assertEquals(shape.get(2).getY(), 1);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_90_degrees_counterclockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), -1);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), -1);
        assertEquals(shape.get(2).getY(), 0);

        assertEquals(shape.get(3).getX(), -1);
        assertEquals(shape.get(3).getY(), 1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_180_degrees_counterclockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateLeft();
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 1);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 0);
        assertEquals(shape.get(2).getY(), -1);

        assertEquals(shape.get(3).getX(), -1);
        assertEquals(shape.get(3).getY(), -1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_270_degrees_counterclockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateLeft();
        shape.rotateLeft();
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), 0);
        assertEquals(shape.get(0).getY(), 1);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 1);
        assertEquals(shape.get(2).getY(), 0);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), -1);
    }

    @Test
    public void test_if_tetromino_can_be_rotated_360_degrees_counterclockwise() {
        AbstractShape shape = new DummyShape(new Coordinate(0, 0));
        shape.rotateLeft();
        shape.rotateLeft();
        shape.rotateLeft();
        shape.rotateLeft();

        assertEquals(shape.get(0).getX(), -1);
        assertEquals(shape.get(0).getY(), 0);

        assertEquals(shape.get(1).getX(), 0);
        assertEquals(shape.get(1).getY(), 0);

        assertEquals(shape.get(2).getX(), 0);
        assertEquals(shape.get(2).getY(), 1);

        assertEquals(shape.get(3).getX(), 1);
        assertEquals(shape.get(3).getY(), 1);
    }

}
