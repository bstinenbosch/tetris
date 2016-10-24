package robot;

import tetris.Controller;

import javafx.application.Platform;

public class RobotController extends Thread {

    private Controller controller;
    private IRobot robot;
    private static volatile boolean playing = true;
    private static volatile RobotController robotController;

    private RobotController(Controller controller) {
        this.controller = controller;
        // robot = new RandomRobot();
        robot = new ANNRobot(controller.getGrid().width());
        this.controller.addScoreObserver(robot);
    }

    public static synchronized void toggleRobotController(Controller controller) {
        if (robotController == null) {
            robotController = new RobotController(controller);
            robotController.start();
        } else {
            playing = false;
            robotController = null;
        }
    }

    @Override
    public void run() {
        playing = true;
        // TODO load settings
        while (playing) {
            if (controller.isGameOver()) {
                robot.resetSession();
                // TODO highscore invullen in leaderboards
                Platform.runLater(() -> controller.startGame());
            } else {
                // give grid and tetromino to ANN
                robot.setGameState(controller.getGrid(), controller.getTetromino());
                // ask for action and handle it
                Platform.runLater(() -> robot.getNextAction().attempt(controller.getTetromino(),
                    controller.getGrid()));
            }
            try {
                sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        // TODO save settings
    }
}
