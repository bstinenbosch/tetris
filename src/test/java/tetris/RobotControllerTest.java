package tetris;

import tetris.shapes.AbstractShape;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import robot.RobotController;

/**
 * Solution courtesy of http://stackoverflow.com/a/33778183
 *
 */
@RunWith(JfxTestRunner.class)
public class RobotControllerTest {
    @Test
    public void testRobotController() throws InterruptedException {
        DummyController controller = new DummyController();
        AbstractShape tetromino = new DummyShapeO();
        RobotController.toggleRobotController(controller);
        while (controller.getFallingTetromino().get(0).getX() == tetromino.get(0).getX()) {
            Thread.sleep(500);
        }
        RobotController.toggleRobotController(controller);
        Assert.assertNotEquals(controller.getFallingTetromino().get(0).getX(), tetromino.get(0).getX());

    }

}
