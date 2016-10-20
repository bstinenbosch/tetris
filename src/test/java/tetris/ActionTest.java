package tetris;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("deprecation")
public class ActionTest {
    @Test
    public void testChecksoftDrop() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(5, 0));
        Action.SOFT_DROP.attempt(tetromino, grid);
        assertEquals(Action.SOFT_DROP.toString(), "Soft drop");
    }

    @Test
    public void testMoveLeftTrue() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(5, 5));
        Action.MOVE_LEFT.attempt(tetromino, grid);
    }

    @Test
    public void testMoveLeftFalse() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(-1, 5));
        Action.MOVE_LEFT.attempt(tetromino, grid);
    }

    @Test
    public void testMoveRightTrue() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(9, 6));
        Action.MOVE_RIGHT.attempt(tetromino, grid);
    }

    @Test
    public void testMoveRightFalse() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(5, 6));
        Action.MOVE_RIGHT.attempt(tetromino, grid);
    }

    @Test
    public void testHardDrop() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(5, 0));
        Action.HARD_DROP.attempt(tetromino, grid);
        assertEquals(Action.HARD_DROP.toString(), "Hard drop");
    }

    @Test
    public void testCheckMoveDownTrue() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(5, 5));
        Action.SOFT_DROP.attempt(tetromino, grid);
    }

    @Test
    public void testRotateLeftLeft() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(0, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
    }

    @Test
    public void testRotateLeftRight() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(10, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
    }

    @Test
    public void testRotateRightLeft() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(0, 0));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
    }

    @Test
    public void testRotateRightRight() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 10, 20);
        DummyShapeJ tetromino = new DummyShapeJ(new Coordinate(10, 5));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
    }

    @Test
    public void testInvalidAction() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(2, 2));
        Action.INVALID_ACTION.attempt(tetromino, grid);
    }

}
