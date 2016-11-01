package robot;

import tetris.Controller;
import tetris.Tick;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import logging.Logger;
import robot.ANN.functions.EvaluationFunction;

public class RobotController extends Thread {

    private Controller controller;
    private IRobot robot;
    private Label inputLabel;
    private double initialX;
    private double initialY;
    private Stage stage;
    private static volatile boolean playing = true;
    private static volatile RobotController robotController;

    private RobotController(Controller controller, Tick tick) {
        this.controller = controller;
        tick.setBaseTime(20);
        tick.setTime(20);
        robot = new ANNRobot(controller.getGrid().width(), 10, EvaluationFunction.SIGMOID);
        this.controller.addScoreObserver(robot);
        setName("Robot thread");
        Platform.runLater(() -> generateIOscreen());
    }

    public static synchronized void toggleRobotController(Controller controller, Tick tick) {
        Logger.info(controller, "robot toggled");
        if (robotController == null) {
            robotController = new RobotController(controller, tick);
            robotController.start();
        } else {
            playing = false;
            robotController.closeStage();
            robotController = null;
        }
    }

    private void closeStage() {
        Platform.runLater(() -> stage.close());
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
                String state = robot.setGameState(controller.getGrid(),
                    controller.getFallingTetromino(), controller);
                Platform.runLater(() -> inputLabel.setText(state));
            }
            try {
                sleep(20);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
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

    private void generateIOscreen() {
        inputLabel = new Label("");
        Group root = new Group();
        root.setStyle("-fx-background-color:white");
        root.getChildren().addAll(inputLabel);
        makeDraggable(root);
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 500, 20));
        stage.show();
    }

    private void makeDraggable(Group group) {
        group.setOnMousePressed((mouseEvent) -> {
            initialX = mouseEvent.getSceneX();
            initialY = mouseEvent.getSceneY();
        });
        group.setOnMouseDragged((mouseEvent) -> {
            group.getScene().getWindow().setX(mouseEvent.getScreenX() - initialX);
            group.getScene().getWindow().setY(mouseEvent.getScreenY() - initialY);
        });
    }
}
