package tetris;

import org.junit.Assert;
import org.junit.Test;

public class ActionTest {
    @Test
    public void testChecksoftDrop() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(0, 0));
        Action.SOFT_DROP.attempt(tetromino, grid);
        Assert.assertTrue(controller.isGameOverBoolean);
    }

    @Test
    public void testVoidMoves() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(0, 0));
        Action.MOVE_LEFT.attempt(tetromino, grid);
        Action.MOVE_RIGHT.attempt(tetromino, grid);
        Action.ROTATE_LEFT.attempt(tetromino, grid);
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
    }

    @Test
    public void testHardDrop() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeO tetromino = new DummyShapeO(new Coordinate(0, 0));
        Action.HARD_DROP.attempt(tetromino, grid);
        Assert.assertTrue(controller.isGameOverBoolean);

    }

    @Test
    public void testRotateLeftLeft() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(2, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
    }

    @Test
    public void testRotateLeftRight() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(10, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
    }

    @Test
    public void testRotateRightLeft() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(0, 6));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
    }

    @Test
    public void testRotateRightRight() {
        DummyController controller = new DummyController();
        Grid grid = new Grid(controller, 2, 2);
        DummyShapeI tetromino = new DummyShapeI(new Coordinate(10, 6));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
    }

}
