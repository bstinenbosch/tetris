package tetris.scenes;

import tetris.Controller;
import tetris.Settings;
import tetris.View;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import robot.RobotController;

public class GameScreen extends Group implements IScreen {
    private Settings settings;
    private Button botButton;
    private Button restartButton;
    private Button backButton;
    private Button pauseButton;
    private ObservingLabel scoreLabel;
    private ObservingLabel levelLabel;
    private Canvas canvas;
    private Canvas canvasprev;
    private int boardWidthPixel;
    private int boardHeightPixel;
    private int previewWidth;
    private int previewHeight;

    /**
     * the gamescreen is the screen where game is actually played.
     * 
     * @param settings
     *            the game-wide settings
     */
    public GameScreen(Settings settings) {
        this.settings = settings;
        VBox rightPane = setUpRightPaneGameScreen();
        Pane leftPane = setUpLeftPaneGameScreen();
        HBox rootGameScreen = setUpRootPaneGameScreen(leftPane, rightPane);
        getChildren().add(rootGameScreen);
        rootGameScreen.setStyle("-fx-background-color: grey");
    }

    private Pane setUpLeftPaneGameScreen() {
        canvas = new GridCanvas(settings);
        Pane leftPane = new Pane();
        leftPane.getChildren().add(canvas);
        return leftPane;
    }

    private HBox setUpRootPaneGameScreen(Pane leftPane, VBox rightPane) {
        HBox rootGameScreen = new HBox(10);
        rootGameScreen.getChildren().addAll(leftPane, rightPane);
        return rootGameScreen;
    }

    private VBox setUpRightPaneGameScreen() {
        botButton = new Button("Toggle botmode");
        restartButton = new Button("restart");
        backButton = new Button("back");
        scoreLabel = new ObservingLabel("0");
        levelLabel = new ObservingLabel("0");
        pauseButton = new Button("pause");
        scoreLabel
            .setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-text-alignment:center;"
                + "-fx-alignment:center;-fx-font-weight:bold;-fx-font-size:250%");
        scoreLabel.setMinSize(100, 50);
        levelLabel
            .setStyle("-fx-background-color:pink;-fx-text-fill:black;-fx-text-alignment:center;"
                + "-fx-alignment:center;-fx-font-weight:bold;-fx-font-size:250%");
        levelLabel.setMinSize(100, 50);

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(pauseButton, botButton, restartButton, scoreLabel, levelLabel,
            backButton, setUpPreview());
        return box;
    }

    private Canvas setUpPreview() {
        canvasprev = new GridCanvasPrev(settings);
        return canvasprev;
    }

    @Override
    public void hookEvents(Controller controller) {
        botButton.setOnAction(event -> RobotController.toggleRobotController(controller));
        pauseButton.setOnAction(event -> controller.pause(pauseButton));
        restartButton.setOnAction(event -> controller.restartGame());
        backButton.setOnAction(event -> {
            controller.gameOver();
            controller.openMainScreen();
        });
        controller.addScoreObserver(scoreLabel);
        controller.addLevelObserver(levelLabel);
        canvas.setOnMouseClicked(event -> requestFocus());
        setOnKeyPressed(event -> controller.handleKeyEvent(event));
    }

    public boolean testHookEvents() {
        View view = new View();
        Controller controller = new Controller(view, settings);
        this.hookEvents(controller);
        return true;
    }

}
