//package tetris;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//import tetris.shapes.AbstractShape;
//
//import javafx.embed.swing.JFXPanel;
//
//import javax.swing.SwingUtilities;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import robot.RobotController;
//
///**
// * Solution courtesy of http://stackoverflow.com/a/33778183
// *
// */
//@RunWith(JfxTestRunner.class)
//public class RobotControllerTest {
//
//    @BeforeClass
//    public static void initToolkit() throws InterruptedException {
//        final CountDownLatch latch = new CountDownLatch(1);
//        SwingUtilities.invokeLater(() -> {
//            new JFXPanel();
//            latch.countDown();
//        });
//
//        if (!latch.await(5L, TimeUnit.SECONDS))
//            throw new ExceptionInInitializerError();
//    }
//
//    @Test
//    public void testRobotController() throws InterruptedException {
//        View view = new View();
//        Settings settings = new Settings();
//        DummyController controller = new DummyController(view, settings);
//        AbstractShape tetromino = new DummyShapeO();
//        RobotController.toggleRobotController(controller);
//        while (controller.getFallingTetromino().get(0).getX() == tetromino.get(0).getX()) {
//            Thread.sleep(500);
//        }
//        RobotController.toggleRobotController(controller);
//        Assert.assertNotEquals(controller.getFallingTetromino().get(0).getX(),
//            tetromino.get(0).getX());
//
//    }
//
//}
