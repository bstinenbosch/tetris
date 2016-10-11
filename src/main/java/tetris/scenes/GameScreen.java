package tetris.scenes;

import tetris.Controller;
import tetris.Settings;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameScreen extends Group implements IScreen {
    private Settings settings;
    private Button exitButton;
    private Button restartButton;
    private Button backButton;
    private ObservingLabel scoreLabel;

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
    }

    private Pane setUpLeftPaneGameScreen() {
        int boardWidthPixel = settings.blockSize() * settings.boardWidth();
        int boardHeightPixel = settings.blockSize() * settings.boardHeight();
        Canvas canvas = new Canvas(boardWidthPixel, boardHeightPixel);
        settings.setBoard(canvas.getGraphicsContext2D());
        Pane leftPane = new Pane();
        leftPane.getChildren().add(canvas);
        leftPane.setStyle("-fx-background-color: grey");
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
        exitButton = new Button("exit");
        restartButton = new Button("restart");
        backButton = new Button("back");
        scoreLabel = new ObservingLabel("0");
        scoreLabel
            .setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-text-alignment:center;"
                + "-fx-alignment:center;-fx-font-weight:bold;-fx-font-size:250%");
        scoreLabel.setMinSize(100, 50);

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(exitButton, restartButton, scoreLabel, backButton, setUpPreview());
        box.setStyle("-fx-background-color: grey");
        return box;
    }

    private Canvas setUpPreview() {
        Canvas canvas = new Canvas(settings.blockSize() * 6, settings.blockSize() * 5);
        settings.setPreview(canvas.getGraphicsContext2D());
        return canvas;
    }

    @Override
    public void hookEvents(Controller controller) {
        exitButton.setOnAction(event -> controller.stop());
        restartButton.setOnAction(event -> controller.restartGame());
        backButton.setOnAction(event -> controller.openMainScreen());
        controller.addScoreObserver(scoreLabel);
        setOnKeyPressed(event -> controller.handleKeyEvent(event));
    }

}
