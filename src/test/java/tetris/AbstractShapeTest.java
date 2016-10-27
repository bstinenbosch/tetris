package tetris;

import common.Coordinate;
import common.CoordinateSet;
import org.junit.Test;
import tetris.shapes.AbstractShape;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractShapeTest {

    private AbstractShape shape = new DummyShapeJ();

    @Test
    public void test_topmost_mino() {
        assertThat("the coordinate of the topmost is calculated properly",
                shape.top().getY() == 0,
                equalTo(true));
    }

    @Test
    public void test_bottommost_mino() {
        assertThat("the coordinate of the bottommost is calculated properly",
                shape.bottom().getY() == -1,
                equalTo(true));
    }

    @Test
    public void test_leftmost_mino() {
        assertThat("the coordinate of the leftmost is calculated properly",
                shape.left().getY() == 0,
                equalTo(true));
    }

    @Test
    public void test_rightmost_mino() {
        assertThat("the coordinate of the rightmost is calculated properly",
                shape.right().getX() == 1,
                equalTo(true));
    }

    @Test
    public void test_get_color() {
        assertThat("the correct color index is returned",
                shape.getColor() == 1,
                equalTo(true));
    }

    @Test
    public void test_get_minos() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(-1, 0));
        coordinates.add(new Coordinate(0, 0));
        coordinates.add(new Coordinate(1, 0));
        coordinates.add(new Coordinate(1, -1));
        CoordinateSet expectedMinos = new CoordinateSet(coordinates);

        assertThat("the correct color index is returned",
                shape.getMinos().equals(expectedMinos),
                equalTo(true));
    }

}
