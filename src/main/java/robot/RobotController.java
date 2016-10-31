package robot;

import tetris.Controller;
import tetris.Tick;

import javafx.application.Platform;

import logging.Logger;
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
        robot = new ANNRobot(controller.getGrid().width(), 10, EvaluationFunction.SIGMOID);
        this.controller.addScoreObserver(robot);
        setName("Robot thread");
    }

    public static synchronized void toggleRobotController(Controller controller, Tick tick) {
        Logger.info(controller, "robot toggled");
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
                // TODO highscore invullen in leaderboards
                Platform.runLater(() -> controller.startGame());
                waitForRestart();
            } else {
                robot.setGameState(controller.getGrid(), controller.getFallingTetromino());
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

    private void waitForRestart() {
        while (controller.isGameOver()) {
            try {
                sleep(50);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
