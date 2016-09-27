package tetris;

import java.util.Observable;
import java.util.Observer;

import tetris.tetromino.AbstractTetromino;

//TEST VOOR UITLEG!

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import tetris.tetromino.AbstractTetromino;

/**
 * MainScreen is the class containing all the GUI-related stuff. Here we draw up
 * the different screens and hook the key events.
 */
public class View extends Application {
    private static final int BLOCK_SIZE = 20;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CORNER = 3;
    private Controller controller;
    private Stage primaryStage;
    private GraphicsContext board;
    private GraphicsContext PreviewGC;
    private Button settingsButton;
//    private GameSettingsPanel gameSettingsPanel = new GameSettingsPanel();
    private ScreenController screenController = new ScreenController();
    private MainScreen mainScreen = new MainScreen();
    private SettingsScreen settingsScreen = new SettingsScreen();

    private class ObservingLabel extends Label implements Observer {

        public ObservingLabel(String string) {
            super(string);
        }

        @Override
        public void update(Observable o, Object arg) {
            Platform.runLater(() -> this.setText(Integer.toString((int) arg)));
        }

    }

    private ObservingLabel scoreLabel;

    public ObservingLabel getScoreLabel() {
        return scoreLabel;
    }

    /**
     * start inits the application and displays a loading screen
     * 
     * @param primaryStage
     *            is the root object containing the application.
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new Controller(this);
        this.primaryStage = primaryStage;
        gotoLauncher();
//        primaryStage.setTitle("Tetris");
//        primaryStage.show();
    }

    public void resetFocus() {
        primaryStage.getScene().getRoot().requestFocus();
    }

    /**
     * gotoLauncher navigates to the launcher and contains its definition.
     */
    private void gotoLauncher() {
        screenController.addScreen("Main", mainScreen);
        screenController.addScreen("Settings", settingsScreen);
        screenController.setScreen("Main");

        hookLauncherEvents();
        hookSettingsEvents();
        hookBackToMainMenuButtonEvents();

        Group root = new Group();
        root.getChildren().addAll(screenController);
        Scene scene = new Scene(root);
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void hookSettingsEvents() {
        Button settingsButton = mainScreen.getSettingsButton();
        settingsButton.setOnAction(event -> screenController.setScreen("Settings"));
    }

    private void hookLauncherEvents() {
        Button startNewGameButton = mainScreen.getStartNewGameButton();
        startNewGameButton.setOnAction(event -> controller.startGame(BOARD_WIDTH, BOARD_HEIGHT));
    }

    private void hookBackToMainMenuButtonEvents() {
        Button backToMainMenuButton = settingsScreen.getBackToMainScreenButton();
        backToMainMenuButton.setOnAction(event -> screenController.setScreen("Main"));
    }

    /**
     * gotoGameScreen inits and shows the game screen, hooks the key events and
     * fires up the game controller.
     */
    public void gotoGameScreen() {
        scoreLabel = new ObservingLabel("0");
        scoreLabel
            .setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-text-alignment:center;"
                + "-fx-alignment:center;-fx-font-weight:bold;-fx-font-size:250%");
        scoreLabel.setMinSize(100, 50);
        Pane leftPane = setUpLeftPaneGameScreen();
        GridPane rightPane = setUpRightPaneGameScreen();
        GridPane rootGameScreen = setUpRootPaneGameScreen(leftPane, rightPane);

        Scene gameScreen = new Scene(rootGameScreen);
        primaryStage.setScene(gameScreen);
        gameScreen.getRoot().requestFocus();
    }

    private Pane setUpLeftPaneGameScreen() {
        Canvas canvas = new Canvas(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
        board = canvas.getGraphicsContext2D();
        Pane leftPane = new Pane();
        leftPane.getChildren().add(canvas);
        leftPane.setStyle("-fx-background-color: grey");
        return leftPane;
    }

    private GridPane setUpRootPaneGameScreen(Pane leftPane, GridPane rightPane) {
        GridPane rootGameScreen = new GridPane();
        rootGameScreen.setHgap(10);
        GridPane.setConstraints(leftPane, 0, 0);
        GridPane.setConstraints(rightPane, 1, 0);
        rootGameScreen.getChildren().addAll(leftPane, rightPane);
        rootGameScreen.setOnKeyPressed(event -> controller.handleKeyEvent(event));
        return rootGameScreen;
    }

    private GridPane setUpRightPaneGameScreen() {
        Button exitButton = new Button("exit");
        Button restartButton = new Button("restart");
        GridPane PreviewPane = setUpPreviewPane();
        hookGameScreenEvents(exitButton, restartButton);

        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);
        rightPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(exitButton, 0, 0);
        GridPane.setConstraints(restartButton, 0, 1);
        GridPane.setConstraints(scoreLabel, 0, 2);
        GridPane.setConstraints(PreviewPane, 0, 4);
        rightPane.getChildren().addAll(exitButton, restartButton, PreviewPane, scoreLabel);
        rightPane.setStyle("-fx-background-color: grey");
        return rightPane;
    }

    private GridPane setUpPreviewPane() {
        Canvas canvas = new Canvas(BLOCK_SIZE * 6, BLOCK_SIZE * 5);
        PreviewGC = canvas.getGraphicsContext2D();

        GridPane PreviewPane = new GridPane();
        PreviewPane.getChildren().addAll(canvas);

        return PreviewPane;
    }

    private void hookGameScreenEvents(Button exitButton, Button restartButton) {
        exitButton.setOnAction(event -> controller.stop());
        restartButton.setOnAction(event -> controller.restartGame());
    }

    /**
     * setColor sets the draw color pairs of the board according to predefined
     * pairs.
     * 
     * @param color
     *            the color pair ID
     */
    private void setColor(int color) {
        switch (color) {
            case 1:
                board.setFill(Color.WHITE);
                break;
            case 2:
                board.setFill(Color.WHITE);
                break;
            case 3:
                board.setFill(Color.WHITE);
                break;
            case 4:
                board.setFill(Color.WHITE);
                break;
            case 5:
                board.setFill(Color.WHITE);
                break;
            case 6:
                board.setFill(Color.WHITE);
                break;
            case 7:
                board.setFill(Color.WHITE);
                break;
            default:
                throw new IllegalArgumentException(
                    String.format("Color %d is not a valid color number.", color));
        }
    }

    private void setColorPreview(int color) {
        switch (color) {
            case 1:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[0].getValue());
                break;
            case 2:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[1].getValue());
                break;
            case 3:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[2].getValue());
                break;
            case 4:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[3].getValue());
                break;
            case 5:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[4].getValue());
                break;
            case 6:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[5].getValue());
                break;
            case 7:
                PreviewGC.setFill(gameSettingsPanel.getColorPickers()[6].getValue());
                break;
            default:
                throw new IllegalArgumentException(
                    String.format("Color %d is not a valid color number.", color));
        }
    }

    /**
     * drawGrid draws the entire gameboard. As tetrominos reach their final
     * place, they are registered on the grid to be drawn by this function.
     * 
     * @param grid
     *            the gameboard to draw on the canvas
     */
    public void drawGrid(Grid grid) {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                drawRectangle(grid.get(x, y), new Coordinate(x, y));
            }
        }
    }

    public void drawGridPreview(Grid grid) {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                drawRectanglePreview(grid.get(x, y), new Coordinate(x, y));
            }
        }
    }

    /**
     * drawTetromino employs the structure of a tetromino to draw it on a
     * gameboard.
     * 
     * @param tetromino
     *            the tetromino to draw
     */
    public void drawTetromino(AbstractTetromino tetromino) {
        for (int i = 0; i < 4; i++) {
            drawRectangle(tetromino.getColor(), tetromino.get(i));
        }
    }

    public void drawTetrominoPreview(AbstractTetromino tetromino) {
        for (int i = 0; i < 4; i++) {
            drawRectanglePreview(tetromino.getColor(), tetromino.get(i));
        }
    }

    /**
     * drawRectangle draws one cube on the game grid.
     * 
     * @param board
     *            specifies the gameboard(canvas) to draw on
     * @param color
     *            specifies the color pair to draw in (color pairs provided by
     *            setColor)
     * @param coordinate
     *            the cube in the grid that is to be drawn.
     */
    private void drawRectangle(int color, Coordinate coordinate) {
        if (color > 0) {
            setColor(color);
            board.setLineWidth(BLOCK_SIZE / 10.);
            board.fillRoundRect(coordinate.getX() * BLOCK_SIZE,
                (BOARD_HEIGHT - 1 - coordinate.getY()) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER,
                CORNER);
            board.strokeRoundRect(coordinate.getX() * BLOCK_SIZE,
                (BOARD_HEIGHT - 1 - coordinate.getY()) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER,
                CORNER);
        }
    }

    private void drawRectanglePreview(int color, Coordinate coordinate) {
        if (color > 0) {
            setColorPreview(color);
            PreviewGC.setLineWidth(BLOCK_SIZE / 10.);
            PreviewGC.fillRoundRect(coordinate.getX() * BLOCK_SIZE,
                (BOARD_HEIGHT - 1 - coordinate.getY()) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER,
                CORNER);
            PreviewGC.strokeRoundRect(coordinate.getX() * BLOCK_SIZE,
                (BOARD_HEIGHT - 1 - coordinate.getY()) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER,
                CORNER);
        }
    }

    /**
     * clearBoard erases the current board so it can be redrawn.
     */
    public void clearBoard() {
        board.setFill(Color.BLACK);
        board.fillRect(0, 0, BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);
        PreviewGC.setFill(Color.BLUE);
        PreviewGC.fillRect(0, 0, BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);

    }

    /**
     * gameOver displays the "game over" text.
     */
    public void gameOver() {
        board.setTextAlign(TextAlignment.CENTER);
        board.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
        // board.setFill(Color.GREY);
        // board.fillText("GAME OVER", BOARD_WIDTH * BLOCK_SIZE / 2,
        // BOARD_HEIGHT * BLOCK_SIZE / 2);

    }

    /**
     * stop is called when the application terminates. Here any asynchronous
     * threads should be terminated.
     */
    public void stop() {
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
