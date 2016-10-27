package common;

import common.Coordinate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoordinateTest {

    @Test
    public void test_x_coordinate() {
        Coordinate coordinate = new Coordinate(5, 2);
        assertThat("correct x-coordinate is retrieved",
                coordinate.getX() == 5,
                equalTo(true));
    }

    @Test
    public void test_y_coordinate() {
        Coordinate coordinate = new Coordinate(5, 2);
        assertThat("correct x-coordinate is retrieved",
                coordinate.getY() == 2,
                equalTo(true));
    }

    @Test
    public void test_horizontal_translation() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.translateX(5);
        assertThat("coordinate is translated in positive horizontal direction",
                coordinate.getX() == 10,
                equalTo(true));
    }

    @Test
    public void test_vertical_translation() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.translateY(4);
        assertThat("coordinate is translated in positive vertical direction",
                coordinate.getY() == 6,
                equalTo(true));
    }

    @Test
    public void test_clockwise_rotation_zero_quadrants() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.rotateClockwise(0);
        assertThat("coordinate is rotated zero quadrants",
                coordinate.equals(new Coordinate(5, 2)),
                equalTo(true));
    }

    @Test
    public void test_clockwise_rotation_one_quadrant() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.rotateClockwise(1);
        assertThat("coordinate is rotated one quadrant",
                coordinate.equals(new Coordinate(2, -5)),
                equalTo(true));
    }

    @Test
    public void test_clockwise_rotation_two_quadrants() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.rotateClockwise(2);
        assertThat("coordinate is rotated two quadrants",
                coordinate.equals(new Coordinate(-5, -2)),
                equalTo(true));
    }

    @Test
    public void test_clockwise_rotation_three_quadrants() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.rotateClockwise(3);
        assertThat("coordinate is rotated three quadrants",
                coordinate.equals(new Coordinate(-2, 5)),
                equalTo(true));
    }

    @Test
    public void test_clockwise_rotation_four_quadrants() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.rotateClockwise(4);
        assertThat("coordinate is rotated four quadrants",
                coordinate.equals(new Coordinate(5, 2)),
                equalTo(true));
    }

    @Test
    public void test_clockwise_rotation_five_quadrants() {
        Coordinate coordinate = new Coordinate(5, 2);
        coordinate.rotateClockwise(5);
        assertThat("coordinate is rotated five quadrants",
                coordinate.equals(new Coordinate(2, -5)),
                equalTo(true));
    }

    @Test
    public void test_coordinate_equals_itself() {
        Coordinate coordinate = new Coordinate(5, 2);
        assertThat("a coordinate is equal to itself",
                coordinate.equals(coordinate),
                equalTo(true));
    }

    @Test
    public void test_coordinate_equals_other_coordinate_successful() {
        Coordinate coordinate = new Coordinate(5, 2);
        assertThat("a coordinate is equal to an other coordinate with same values",
                coordinate.equals(new Coordinate(5, 2)),
                equalTo(true));
    }

    @Test
    public void test_coordinate_equals_other_coordinate_failure() {
        Coordinate coordinate = new Coordinate(5, 2);
        assertThat("a coordinate is not equal to an other coordinate with different values",
                coordinate.equals(new Coordinate(4, 6)),
                equalTo(false));
    }

    @Test
    public void test_coordinate_equals_other_object_failure() {
        Coordinate coordinate = new Coordinate(5, 2);
        assertThat("a coordinate is not equal to an other object that is not an instance of Coordinate",
                coordinate.equals(new Object()),
                equalTo(false));
    }
}
