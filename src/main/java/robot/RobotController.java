package robot;

import tetris.Controller;
import tetris.Tick;

import javafx.application.Platform;

import robot.ANN.functions.EvaluationFunction;

public class RobotController extends Thread {

    private Controller controller;
    private IRobot robot;
    private static volatile boolean playing = true;
    private static volatile RobotController robotController;

    private RobotController(Controller controller, Tick tick) {
        this.controller = controller;
        tick.setBaseTime(30);
        tick.setTime(30);
        // robot = new RandomRobot();
        robot = new ANNRobot(controller.getGrid().width(), 5, EvaluationFunction.SIGMOID);
        this.controller.addScoreObserver(robot);
    }

    public static synchronized void toggleRobotController(Controller controller, Tick tick) {
        if (robotController == null) {
            robotController = new RobotController(controller, tick);
            robotController.start();
        } else {
            playing = false;
            robotController = null;
            tick.unpause();
        }
    }

    @Override
    public void run() {
        playing = true;
        // TODO load settings
        while (playing) {
            if (controller.isGameOver()) {
                robot.resetSession();
                try {
                    sleep(50);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                // TODO highscore invullen in leaderboards
                Platform.runLater(() -> controller.startGame());
            } else {
                // give grid and shape to ANN
                robot.setGameState(controller.getGrid(), controller.getFallingTetromino());
                // ask for action and handle it
                Platform.runLater(() -> robot.getNextAction()
                    .attempt(controller.getFallingTetromino(), controller.getGrid(), controller));
            }
            try {
                sleep(20);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        // TODO save settings
    }
}
