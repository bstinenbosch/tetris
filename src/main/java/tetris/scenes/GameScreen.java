package tetris.scenes;

import com.sun.prism.shader.Texture_Color_AlphaTest_Loader;
import highscore.ScoreBoard;
import tetris.Controller;
import tetris.Score;
import tetris.Settings;
import tetris.View;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import robot.RobotController;
import tetris.sound.SoundManager;
import tetris.tetromino.TetrominoFactory;

public class GameScreen extends Group implements IScreen {
    private Settings settings;
    private Button botButton;
    private Button restartButton;
    private Button backButton;
    private Button pauseButton;
    private ObservingLabel scoreLabel;
    private Canvas canvas;
    private Pane leftPane;
    private VBox rightPane;
    private GridPane rootGameScreen;
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
        GridPane rootGameScreen = setUpRootPaneGameScreen(leftPane, rightPane);
        getChildren().add(rootGameScreen);
        rootGameScreen.setStyle("-fx-background-color: grey");
    }

    private Pane setUpLeftPaneGameScreen() {
        boardWidthPixel = settings.blockSize() * settings.boardWidth();
        boardHeightPixel = settings.blockSize() * settings.boardHeight();
        canvas = new Canvas(boardWidthPixel, boardHeightPixel);
        settings.setBoard(canvas.getGraphicsContext2D());
        Pane leftPane = new Pane();
        leftPane.getChildren().add(canvas);
        return leftPane;
    }

    private GridPane setUpRootPaneGameScreen(Pane leftPane, VBox rightPane) {
        GridPane rootGameScreen = new GridPane();
        rootGameScreen.setHgap(10);
        GridPane.setConstraints(leftPane, 0, 0);
        GridPane.setConstraints(rightPane, 1, 0);
        rootGameScreen.getChildren().addAll(leftPane, rightPane);
        return rootGameScreen;
    }

    private VBox setUpRightPaneGameScreen() {
        botButton = new Button("Toggle botmode");
        restartButton = new Button("restart");
        backButton = new Button("back");
        scoreLabel = new ObservingLabel("0");
        pauseButton = new Button("pause");
        scoreLabel
            .setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-text-alignment:center;"
                + "-fx-alignment:center;-fx-font-weight:bold;-fx-font-size:250%");
        scoreLabel.setMinSize(100, 50);

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(pauseButton, botButton, restartButton, scoreLabel, backButton,
            setUpPreview());
        return box;
    }

    private Canvas setUpPreview() {
        previewWidth = settings.blockSize() * 6;
        previewHeight = settings.blockSize() * 5;
        Canvas canvas = new Canvas(previewWidth, previewHeight);
        settings.setPreview(canvas.getGraphicsContext2D());
        return canvas;
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
        canvas.setOnMouseClicked(event -> requestFocus());
        setOnKeyPressed(event -> controller.handleKeyEvent(event));
    }

    public boolean testHookEvents() {
        View ui = new View();
        Settings settings = new Settings("src/main/resources/settings.xml");
        TetrominoFactory factory = new TetrominoFactory();
        SoundManager soundManager = new SoundManager(2);
        Score score = new Score();
        ScoreBoard scoreBoard = new ScoreBoard("src/main/resources/highscores.xml");
        Controller controller = new Controller(ui, settings, factory, soundManager, score, scoreBoard);
        this.hookEvents(controller);
        return true;
    }

    public int getLeftPaneWidth() {
        return this.boardWidthPixel;
    }

    public int getLeftPaneHeight() {
        return this.boardHeightPixel;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

}
