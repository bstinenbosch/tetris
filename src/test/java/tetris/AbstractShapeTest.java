package tetris;

import common.Coordinate;
import common.CoordinateSet;
import org.junit.Test;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AbstractShapeTest {

    private AbstractShape shape = new DummyShapeRotationTests();

    @Test
    public void test_topmost_coordinate() {
        assertEquals(shape.top().getY(), 2);
    }

    @Test
    public void test_bottommost_coordinate() {
        assertEquals(shape.bottom().getY(), 0);
    }

    @Test
    public void test_leftmost_coordinate() {
        assertEquals(shape.left().getX(), 0);
    }

    @Test
    public void test_rightmost_coordinate() {
        assertEquals(shape.right().getX(), 1);
    }

    @Test
    public void test_get_color() {
        assertEquals(shape.getColor(), 1);
    }

    @Test
    public void test_get_minos() {
        assertEquals(shape.getMinos().equals(new CoordinateSet(new ArrayList<Coordinate>() {{
            add(new Coordinate(0, 0));
            add(new Coordinate(0, 1));
            add(new Coordinate(0, 2));
            add(new Coordinate(1, 1));
        }})), true);
    }

}
