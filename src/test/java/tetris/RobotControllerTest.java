package tetris;

import tetris.tetromino.AbstractTetromino;

import javafx.application.Application;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import robot.RobotController;

public class RobotControllerTest extends Application {
    @Test
    public void testRobotController() throws InterruptedException {
        DummyController controller = new DummyController();
        AbstractTetromino tetromino = new DummyShapeO(new Coordinate(100, 100));
        RobotController.toggleRobotController(controller);
        while (controller.getTetromino().get(0).getX() == tetromino.get(0).getX()) {
            Thread.sleep(500);
        }
        RobotController.toggleRobotController(controller);
        Assert.assertNotEquals(controller.getTetromino().get(0).getX(), tetromino.get(0).getX());

    }

    @Before
    public void startApp() {
        new Thread(() -> launch()).start();
    }

    @After
    public void stopApp() throws Exception {
        stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // do nothing
    }
}
