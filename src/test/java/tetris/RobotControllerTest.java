package tetris;

import tetris.tetromino.AbstractTetromino;

import javafx.application.Application;
import javafx.stage.Stage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import robot.RobotController;

public class RobotControllerTest extends Application {
    @Test
    public void testRobotController() throws InterruptedException {
        DummyController controller = new DummyController();
        RobotController.toggleRobotController(controller);
        Thread.sleep(3000);
        RobotController.toggleRobotController(controller);
        AbstractTetromino tetromino = new DummyShapeO(new Coordinate(100, 100));
        Assert.assertNotEquals(controller.getTetromino().get(0).getX(), tetromino.get(0).getX());

    }

    @Before
    public void main() {
        new Thread(() -> launch()).start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // do nothing
    }
}
