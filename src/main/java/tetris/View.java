package tetris;

import tetris.scenes.GameScreen;
import tetris.scenes.MainScreen;
import tetris.scenes.SettingsScreen;
import tetris.tetromino.AbstractTetromino;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * MainScreen is the class containing all the GUI-related stuff. Here we draw up
 * the different screens and hook the key events.
 */
public class View extends Application {
    protected Settings settings;
    private Controller controller;
    private Stage primaryStage;

    /**
     * start inits the application and displays a loading screen
     * 
     * @param primaryStage
     *            is the root object containing the application.
     */
    @Override
    public void start(Stage primaryStage) {
        settings = new Settings();
        controller = new Controller(this, settings);
        this.primaryStage = primaryStage;
        gotoMainScreen();
        primaryStage.show();
    }

    /**
     * gotoGameScreen inits and shows the game screen, hooks the key events and
     * fires up the game controller.
     */
    public void gotoGameScreen() {
        GameScreen gameview = new GameScreen(settings);
        primaryStage.setScene(new Scene(gameview));
        gameview.setOnKeyPressed(event -> controller.handleKeyEvent(event));
        gameview.hookEvents(controller);
        gameview.requestFocus();
    }

    public void gotoSettingsScreen() {
        SettingsScreen settingsview = new SettingsScreen(settings);
        settingsview.getBackButton().setOnAction(event -> gotoMainScreen());
        primaryStage.setScene(new Scene(settingsview));
    }

    public void gotoMainScreen() {
        MainScreen mainview = new MainScreen();
        mainview.hookEvents(controller);
        mainview.getSettingsButton().setOnAction(event -> gotoSettingsScreen());
        primaryStage.setScene(new Scene(mainview));
    }

    /**
     * setColor sets the draw color pairs of the board according to predefined
     * pairs.
     * 
     * @param color
     *            the color pair ID
     */
    private void setColor(int color) {
        settings.getBoard().setFill(settings.getColor(color));
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
        for (int x = 0; x < settings.boardWidth(); x++) {
            for (int y = 0; y < settings.boardHeight(); y++) {
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
            settings.getBoard().fillRoundRect(coordinate.getX() * settings.blockSize(),
                (settings.boardHeight() - 1 - coordinate.getY()) * settings.blockSize(),
                settings.blockSize(), settings.blockSize(), settings.corner(), settings.corner());
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
        settings.getBoard().setFill(Color.BLACK);
        settings.getBoard().fillRect(0, 0, settings.boardWidth() * settings.blockSize(),
            settings.boardHeight() * settings.blockSize());
	PreviewGC.setFill(Color.BLUE);
        PreviewGC.fillRect(0, 0, BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);

    }

    /**
     * gameOver displays the "game over" text.
     */
    public void gameOver() {
        settings.getBoard().setTextAlign(TextAlignment.CENTER);
        settings.getBoard().setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
        settings.getBoard().setFill(Color.RED);
        settings.getBoard().fillText("GAME OVER", settings.boardWidth() * settings.blockSize() / 2,
            settings.boardHeight() * settings.blockSize() / 2);

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
