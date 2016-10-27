// package tetris;
//
// import tetris.tetromino.AbstractTetromino;
//
// import org.junit.Assert;
// import org.junit.Test;
// import org.junit.runner.RunWith;
//
// import robot.RobotController;
//
/// **
// * Solution courtesy of http://stackoverflow.com/a/33778183
// *
// */
// @RunWith(JfxTestRunner.class)
// public class RobotControllerTest {
// @Test
// public void testRobotController() throws InterruptedException {
// DummyController controller = new DummyController();
// AbstractTetromino tetromino = new DummyShapeO(new Coordinate(100, 100));
// RobotController.toggleRobotController(controller);
// while (controller.getTetromino().get(0).getX() == tetromino.get(0).getX()) {
// Thread.sleep(500);
// }
// RobotController.toggleRobotController(controller);
// Assert.assertNotEquals(controller.getTetromino().get(0).getX(),
// tetromino.get(0).getX());
//
// }
//
// }
