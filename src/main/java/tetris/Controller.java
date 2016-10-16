package tetris;

import java.util.Observer;

import tetris.scenes.PreviewAdapter;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import highscore.GameEntry;
import highscore.ScoreBoard;
import logging.Logger;

public class Controller {

    private Score score;
    private ScoreBoard scoreBoard;
    private View ui;
    private Grid grid;
    private AbstractTetromino tetromino;
    private AbstractTetromino tetromino2;
    private int leftOffSet;
    private int bottomOffSet;
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
        scoreBoard = new ScoreBoard("src/main/resources/highscores.xml");
        score.addObserver(timer);
        timer.start();
    }

    /**
     * handler for key events.
     * 
     * @param event
     *            the key event to handle.
     */
    public void handleKeyEvent(KeyEvent event) {
        if (!gameOver) {
            try {
                String binding = settings.getKeyBindings().getKey(event.getCode());
                switch (binding) {
                    case "ROTATE_RIGHT":
                        movementHandler.checkRotateRight(tetromino, grid);
                        break;
                    case "MOVE_LEFT":
                        movementHandler.checkMoveLeft(tetromino, grid);
                        break;
                    case "MOVE_RIGHT":
                        movementHandler.checkMoveRight(tetromino, grid);
                        break;
                    case "ROTATE_LEFT":
                        movementHandler.checkRotateLeft(tetromino, grid);
                        break;
                    case "SOFT_DROP":
                        movementHandler.lowerTetromino(tetromino, grid);
                        break;
                    case "HARD_DROP":
                        movementHandler.hardDrop(tetromino, grid);
                        break;
                    default:
                        break;
                }
                redraw();
            } catch (IllegalArgumentException e) {
                e.getMessage();
                Logger.warning(this, "Key binding not set.");
            }
        }
    }

    /**
     * drops a new tetromino and makes sure that it is drawn on the canvas.
     */
    public void dropNewTetromino() {
        score.add(grid.clearLines());

        grid.clearLines();
        Coordinate position = new Coordinate(grid.width() / 2, grid.height());

        Coordinate position2 = new Coordinate(0, 0);

        tetromino = TetrominoFactory.createRandom(position);
        tetromino2 = TetrominoFactory.getLast(position2);

        PreviewAdapter adapter = new PreviewAdapter(tetromino2);
        this.leftOffSet = adapter.getLeftOffSet();
        this.bottomOffSet = adapter.getBottomOffSet();
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
        stopGame();
        if (score.getScore() > 0 && scoreBoard.isHighscore(score.getScore())) {
            Platform.runLater(() -> ui.gotoPromptNameScreen());
        } else {
            ui.gotoHighscoreScreen();
        }
    }

    public void registerHighScore(CharSequence playerName) {
        scoreBoard.add(new GameEntry(playerName.toString(), score.getScore()));
        ui.gotoHighscoreScreen();
    }

    private void stopGame() {
        timer.pause();
        gameOver = true;
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
        timer.unpause();
        ui.resetFocus();
        Logger.log(this, Logger.LogType.INFO, "game started");
    }

    public void openSettings() {
        ui.gotoSettingsScreen();
    }

    public void openMainScreen() {
        stopGame();
        ui.gotoMainScreen();
    }

    public void openPromptName() {
        ui.gotoPromptNameScreen();
    }

    public void viewHighscores() {
        ui.gotoHighscoreScreen();
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

    /**
     * drawRectanglePreview draws one cube on the preview grid.
     *
     * @param color
     *            specifies the color pair to draw in (color pairs provided by
     *            setColor)
     * @param coordinate
     *            the cube in the grid that is to be drawn.
     */
    private void drawRectanglePreview(int color, Coordinate coordinate) {
        if (color > 0) {
            settings.getPreview().setFill(settings.getColor(color));
            settings.getPreview().fillRoundRect(
                coordinate.getX() * settings.blockSize() + this.leftOffSet,
                (5 - 1 - coordinate.getY()) * settings.blockSize() - this.bottomOffSet,
                settings.blockSize(), settings.blockSize(), settings.corner(), settings.corner());
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

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
