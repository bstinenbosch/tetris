package tetris;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import tetris.shapes.decorators.MovableShape;

import javafx.embed.swing.JFXPanel;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("deprecation")
public class ActionTest {

    @BeforeClass
    public static void initToolkit() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });

        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    @Test
    public void testChecksoftDrop() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(5, 0));
        Action.SOFT_DROP.attempt(tetromino, grid);
        assertEquals(Action.SOFT_DROP.toString(), "Soft drop");
    }

    @Test
    public void testMoveLeftTrue() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(5, 5));
        Action.MOVE_LEFT.attempt(tetromino, grid);
        assertTrue(tetromino.left() == 4);
    }

    @Test
    public void testMoveLeftFalse() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(-1, 5));
        Action.MOVE_LEFT.attempt(tetromino, grid);
        assertTrue(tetromino.left() == 0);
    }

    @Test
    public void testMoveRightFalse() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(9, 6));
        Action.MOVE_RIGHT.attempt(tetromino, grid);
        assertTrue(tetromino.left() == 8);
    }

    @Test
    public void testMoveRightTrue() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(5, 6));
        Action.MOVE_RIGHT.attempt(tetromino, grid);
        assertTrue(tetromino.right() == 7);
    }

    @Test
    public void testHardDrop() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(5, 5));
        Action.HARD_DROP.attempt(tetromino, grid);
        assertEquals(Action.HARD_DROP.toString(), "Hard drop");
        assertTrue(tetromino.bottom() == 0);
    }

    @Test
    public void testCheckMoveDownTrue() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeO(), new Coordinate(5, 5));
        Action.SOFT_DROP.attempt(tetromino, grid);
        assertTrue(tetromino.bottom() == 4);
    }

    @Test
    public void testRotateLeft() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeL(), new Coordinate(4, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
        assertTrue(tetromino.left() == 4);
    }

    @Test
    public void testRotateLeftNoFreeGrid() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeI(), new Coordinate(2, 0));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
        assertTrue(tetromino.top() == 0);
    }

    @Test
    public void testRotateRight() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeL(), new Coordinate(4, 6));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
        assertTrue(tetromino.right() == 5);
    }

    @Test
    public void testRotateRightNoFreeGrid() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeI(), new Coordinate(4, 0));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
        assertTrue(tetromino.right() == 6);
    }

    @Test
    public void testRotateLeftLeft() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeL(), new Coordinate(0, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
        assertTrue(tetromino.right() == 2);
    }

    @Test
    public void testRotateLeftRight() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeJ(), new Coordinate(9, 6));
        Action.ROTATE_LEFT.attempt(tetromino, grid);
        assertTrue(tetromino.left() == 7);
    }

    @Test
    public void testRotateRightLeft() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeL(), new Coordinate(0, 0));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
        assertTrue(tetromino.right() == 2);
    }

    @Test
    public void testRotateRightRight() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeJ(), new Coordinate(9, 5));
        Action.ROTATE_RIGHT.attempt(tetromino, grid);
        assertTrue(tetromino.left() == 7);
    }

    @Test
    public void testInvalidAction() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(controller, 10, 20);
        MovableShape tetromino = new MovableShape(new DummyShapeI(), new Coordinate(5, 5));
        assertTrue(Action.testINVALIDACTION(tetromino, grid));
    }

}
