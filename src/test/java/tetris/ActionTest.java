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
        Assert.assertTrue(controller.isGameOver);
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
        Assert.assertTrue(controller.isGameOver);

    }
}
