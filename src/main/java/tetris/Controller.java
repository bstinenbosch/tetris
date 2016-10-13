package tetris;

import java.util.Observer;

import javafx.application.Platform;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import logging.Logger;

public class Controller {

    private Score score;
    private View ui;
    private Grid grid;
    private AbstractTetromino tetromino;
    private AbstractTetromino tetromino2;
    private boolean gameOver = false;
    private TetrominoMovementHandler movementHandler = new TetrominoMovementHandler(this);
    private Tick timer = new Tick(event -> {
        movementHandler.lowerTetromino(tetromino, grid);
        redraw();
    });

    private Settings settings;

    /**
     * the Controller class determines the game flow and does the actual event
     * handling.
     * 
     * @param ui
     *            the application in which the game is running
     */
    public Controller(View ui, Settings settings) {
        this.settings = settings;
        this.ui = ui;
        Logger.setDebugOn();
        score = new Score();
        score.addObserver(timer);
    }

    /**
     * handler for key events.
     * 
     * @param event
     *            the key event to handle.
     */
    public void handleKeyEvent(KeyEvent event) {
        if (!gameOver) {
            switch (settings.getKeyBindings().getKey(event.getCode())) {
                case "ROTATE RIGHT":
                    movementHandler.checkRotateRight(tetromino, grid);
                    break;
                case "MOVE LEFT":
                    movementHandler.checkMoveLeft(tetromino, grid);
                    break;
                case "MOVE RIGHT":
                    movementHandler.checkMoveRight(tetromino, grid);
                    break;
                case "ROTATE LEFT":
                    movementHandler.checkRotateLeft(tetromino, grid);
                    break;
                case "SOFT DROP":
                    movementHandler.lowerTetromino(tetromino, grid);
                    break;
                case "HARD DROP":
                    movementHandler.hardDrop(tetromino, grid);
                    break;
                default:
                    break;
            }
            redraw();
        }
    }

    /**
     * drops a new tetromino and makes sure that it is drawn on the canvas.
     */
    public void dropNewTetromino() {
        score.add(grid.clearLines());

        grid.clearLines();
        Coordinate position = new Coordinate(grid.width() / 2, grid.height());
        Coordinate position2 = new Coordinate(2, 2);

        tetromino = TetrominoFactory.createRandom(position);
        tetromino2 = TetrominoFactory.getLast(position2);

        redraw();
        Logger.log(this, Logger.LogType.INFO, "dropped a new tetromino");
    }

    /**
     * redraw empties the canvas and redraws the gameboard and the current
     * active tetromino.
     */
    private void redraw() {
        clearBoard();
        clearPreview();
        drawGrid();
        drawTetromino();
        drawTetrominoPreview();
    }

    /**
     * gameOver handles the end of the game.
     */
    public void gameOver() {
        timer.pause();
        gameOver = true;
        Logger.log(this, Logger.LogType.INFO, "game restarted");
        settings.getBoard().setTextAlign(TextAlignment.CENTER);
        settings.getBoard().setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
        settings.getBoard().setFill(Color.RED);
        settings.getBoard().fillText("GAME OVER", settings.boardWidth() * settings.blockSize() / 2,
                settings.boardHeight() * settings.blockSize() / 2);
        // runLater prevent "Not on FX thread" error, don't know why. Have to look into this later.
        Platform.runLater(new Runnable() {
            @Override public void run() {
                ui.gotoPromptNameScreen();
            }
        });
    }

    /**
     * stop handles asynchronous threads when the application is closed.
     */
    public void stop() {
        timer.requestStop();
        ui.stop();
        Logger.log(this, Logger.LogType.INFO, "closing application");
        Logger.setDebugOff();
    }

    /**
     * starts the game.
     */
    public void startGame() {
        ui.gotoGameScreen();
        score.reset();
        gameOver = false;
        grid = new Grid(settings.boardWidth(), settings.boardHeight());
        dropNewTetromino();
        timer.start();
        Logger.log(this, Logger.LogType.INFO, "game started");
    }

    public void openSettings() {
        ui.gotoSettingsScreen();
    }

    public void openMainScreen() {
        ui.gotoMainScreen();
    }

    public void openPromptName() {
        ui.gotoPromptNameScreen();
    }

    public void addScoreObserver(Observer observer) {
        score.addObserver(observer);
    }

    /**
     * restartGame should only be called after startgame has been called once.
     */
    public void restartGame() {
        gameOver = false;
        grid.clearBoard();
        score.reset();
        dropNewTetromino();
        timer.unpause();
        ui.resetFocus();
        Logger.log(this, Logger.LogType.INFO, "game restarted");
    }

    /**
     * drawGrid draws the entire gameboard. As tetrominos reach their final
     * place, they are registered on the grid to be drawn by this function.
     * 
     * @param grid
     *            the gameboard to draw on the canvas
     */
    private void drawGrid() {
        for (int x = 0; x < settings.boardWidth(); x++) {
            for (int y = 0; y < settings.boardHeight(); y++) {
                drawRectangle(grid.get(x, y), new Coordinate(x, y));
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
    private void drawTetromino() {
        for (int i = 0; i < 4; i++) {
            drawRectangle(tetromino.getColor(), tetromino.get(i));
        }
    }

    private void drawTetrominoPreview() {
        for (int i = 0; i < 4; i++) {
            drawRectanglePreview(tetromino2.getColor(), tetromino2.get(i));
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
            settings.getBoard().setFill(settings.getColor(color));
            settings.getBoard().fillRoundRect(coordinate.getX() * settings.blockSize(),
                (settings.boardHeight() - 1 - coordinate.getY()) * settings.blockSize(),
                settings.blockSize(), settings.blockSize(), settings.corner(), settings.corner());
        }
    }

    private void drawRectanglePreview(int color, Coordinate coordinate) {
        if (color > 0) {
            settings.getPreview().setFill(settings.getColor(color));
            settings.getPreview().fillRoundRect(coordinate.getX() * settings.blockSize(),
                (5 - 1 - coordinate.getY()) * settings.blockSize(), settings.blockSize(),
                settings.blockSize(), settings.corner(), settings.corner());
        }
    }

    /**
     * clearBoard erases the current board so it can be redrawn.
     */
    private void clearBoard() {
        settings.getBoard().setFill(Color.BLACK);
        settings.getBoard().fillRect(0, 0, settings.boardWidth() * settings.blockSize(),
            settings.boardHeight() * settings.blockSize());
    }

    private void clearPreview() {
        settings.getPreview().setFill(Color.BLACK);
        settings.getPreview().fillRect(0, 0, 6 * settings.blockSize(), 5 * settings.blockSize());
    }

    public void registerHighScore(CharSequence playerName) {
        System.out.println(playerName);
    }
}
