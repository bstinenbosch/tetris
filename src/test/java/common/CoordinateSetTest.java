package common;

import common.Coordinate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoordinateSetTest {

    private CoordinateSet set;

    public CoordinateSetTest() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(5, 2));
        coordinates.add(new Coordinate(1, 2));
        coordinates.add(new Coordinate(5, 4));
        coordinates.add(new Coordinate(8, 3));
        coordinates.add(new Coordinate(7, 1));
        set = new CoordinateSet(coordinates);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_get_boundary_coordinate_on_a_empty_set_failure() {
        CoordinateSet emptySet = new CoordinateSet(new ArrayList<>());
        emptySet.getTopmost();
    }

    @Test
    public void test_topmost_coordinate_in_set() {
        assertThat("the coordinate with the highest y-value is returned",
                set.getTopmost().equals(new Coordinate(5, 4)),
                equalTo(true));
    }

    @Test
    public void test_bottommost_coordinate_in_set() {
        assertThat("the coordinate with the lowest y-value is returned",
                set.getBottommost().equals(new Coordinate(7, 1)),
                equalTo(true));
    }

    @Test
    public void test_leftmost_coordinate_in_set() {
        assertThat("the coordinate with the lowest x-value is returned",
                set.getLeftmost().equals(new Coordinate(1, 2)),
                equalTo(true));
    }

    @Test
    public void test_rightmost_coordinate_in_set() {
        assertThat("the coordinate with the highest x-value is returned",
                set.getRightmost().equals(new Coordinate(8, 3)),
                equalTo(true));
    }

    @Test
    public void test_horizontal_translation() {
        set.translateX(2);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(7, 2));
        coordinates.add(new Coordinate(3, 2));
        coordinates.add(new Coordinate(7, 4));
        coordinates.add(new Coordinate(10, 3));
        coordinates.add(new Coordinate(9, 1));
        CoordinateSet translatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are translated in horizontal direction",
                set.equals(translatedSet),
                equalTo(true));
    }

    @Test
    public void test_vertical_translation() {
        set.translateY(5);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(5, 7));
        coordinates.add(new Coordinate(1, 7));
        coordinates.add(new Coordinate(5, 9));
        coordinates.add(new Coordinate(8, 8));
        coordinates.add(new Coordinate(7, 6));
        CoordinateSet translatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are translated in vertical direction",
                set.equals(translatedSet),
                equalTo(true));
    }

    @Test
    public void test_rotation_clockwise_one_quadrant() {
        set.rotateClockwise(1);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(2, -5));
        coordinates.add(new Coordinate(2, -1));
        coordinates.add(new Coordinate(4, -5));
        coordinates.add(new Coordinate(3, -8));
        coordinates.add(new Coordinate(1, -7));
        CoordinateSet rotatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are rotated one quadrant clockwise",
                set.equals(rotatedSet),
                equalTo(true));
    }

    @Test
    public void test_rotation_clockwise_two_quadrants() {
        set.rotateClockwise(2);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(-5, -2));
        coordinates.add(new Coordinate(-1, -2));
        coordinates.add(new Coordinate(-5, -4));
        coordinates.add(new Coordinate(-8, -3));
        coordinates.add(new Coordinate(-7, -1));
        CoordinateSet rotatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are rotated two quadrants clockwise",
                set.equals(rotatedSet),
                equalTo(true));
    }

    @Test
    public void test_rotation_clockwise_three_quadrants() {
        set.rotateClockwise(3);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(-2, 5));
        coordinates.add(new Coordinate(-2, 1));
        coordinates.add(new Coordinate(-4, 5));
        coordinates.add(new Coordinate(-3, 8));
        coordinates.add(new Coordinate(-1, 7));
        CoordinateSet rotatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are rotated three quadrants clockwise",
                set.equals(rotatedSet),
                equalTo(true));
    }

    @Test
    public void test_rotation_clockwise_four_quadrants() {
        set.rotateClockwise(4);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(5, 2));
        coordinates.add(new Coordinate(1, 2));
        coordinates.add(new Coordinate(5, 4));
        coordinates.add(new Coordinate(8, 3));
        coordinates.add(new Coordinate(7, 1));
        CoordinateSet rotatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are rotated four quadrants clockwise",
                set.equals(rotatedSet),
                equalTo(true));
    }

    @Test
    public void test_rotation_clockwise_five_quadrants() {
        set.rotateClockwise(5);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(2, -5));
        coordinates.add(new Coordinate(2, -1));
        coordinates.add(new Coordinate(4, -5));
        coordinates.add(new Coordinate(3, -8));
        coordinates.add(new Coordinate(1, -7));
        CoordinateSet rotatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are rotated five quadrants clockwise",
                set.equals(rotatedSet),
                equalTo(true));
    }

    @Test
    public void test_rotation_counter_clockwise_one_quadrant() {
        set.rotateCounterClockwise(1);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(-2, 5));
        coordinates.add(new Coordinate(-2, 1));
        coordinates.add(new Coordinate(-4, 5));
        coordinates.add(new Coordinate(-3, 8));
        coordinates.add(new Coordinate(-1, 7));
        CoordinateSet rotatedSet = new CoordinateSet(coordinates);

        assertThat("the coordinates are rotated one quadrant counter clockwise",
                set.equals(rotatedSet),
                equalTo(true));
    }

    @Test
    public void test_set_contains_successful() {
        assertThat("coordinate set contains a coordinate in its set",
                set.contains(new Coordinate(5, 2)),
                equalTo(true));
    }

    @Test
    public void test_set_contains_failure() {
        assertThat("coordinate set does not contain a coordinate not in its set",
                set.contains(new Coordinate(4, 4)),
                equalTo(false));
    }

    @Test
    public void test_set_equals_other_set_successful() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(5, 2));
        coordinates.add(new Coordinate(1, 2));
        coordinates.add(new Coordinate(5, 4));
        coordinates.add(new Coordinate(8, 3));
        coordinates.add(new Coordinate(7, 1));
        CoordinateSet otherSet = new CoordinateSet(coordinates);
        assertThat("a coordinate set is equal to an other coordinate set with the same values",
                set.equals(otherSet),
                equalTo(true));
    }

    @Test
    public void test_set_equals_other_set_failure() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 4));
        coordinates.add(new Coordinate(3, 2));
        coordinates.add(new Coordinate(5, 9));
        coordinates.add(new Coordinate(2, 6));
        coordinates.add(new Coordinate(7, 1));
        CoordinateSet otherSet = new CoordinateSet(coordinates);
        assertThat("a coordinate set is not equal to an other coordinate set with different values",
                set.equals(otherSet),
                equalTo(false));
    }

    @Test
    public void test_set_equals_other_object_failure() {
        assertThat("a coordinate set is not equal to an other object that is not an instance of CoordinateSet",
                set.equals(new Object()),
                equalTo(false));
    }
}
