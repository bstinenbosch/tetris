package tetris;

import java.util.Observer;

import tetris.shapes.AbstractShape;
import tetris.shapes.adapters.PreviewAdapter;
import tetris.shapes.decorators.MovableShape;
import tetris.shapes.original.TetrominoFactory;
import tetris.shapes.original.TetrominoType;
import tetris.sound.AudioStreaming;
import tetris.sound.SoundManager;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import highscore.GameEntry;
import highscore.IScoreBoard;
import highscore.OnlineScoreBoard;
import logging.Logger;

public class Controller {

    private final SoundManager soundManager;
    private Score score;
    private IScoreBoard scoreBoard;
    private View ui;
    private Grid grid;
    private TetrominoQueue queue = new TetrominoQueue();
    private MovableShape fallingTetromino;
    private AbstractShape nextTetromino;
    private TetrominoFactory factory = new TetrominoFactory();
    private int leftOffSet;
    private int bottomOffSet;
    public int changeInMusic = 1000;
    private boolean gameOver = false;
    public boolean normalTheme = false;
    public boolean remixTheme = false;

    private Tick timer = new Tick(event -> {
        Platform.runLater(() -> Action.SOFT_DROP.attempt(fallingTetromino, grid));
        Platform.runLater(() -> redraw());
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
        this.soundManager = new SoundManager(2);
        setSounds();
        Logger.setDebugOn();
        score = new Score();
        scoreBoard = new OnlineScoreBoard();// XMLScoreBoard("src/main/resources/highscores.xml");
        score.addObserver(timer);
        timer.start();
    }

    private void setSounds() {
        soundManager.load("move", getClass().getClassLoader().getResource("sound/sfx/move.wav"));
    }

    /**
     * handler for key events.
     * 
     * @param event
     *            the key event to handle.
     */
    public void handleKeyEvent(KeyEvent event) {
        Action action = settings.getKeyBindings().getAction(event.getCode());
        if (action.attempt(fallingTetromino, grid)) {
            soundManager.play("move");
        }
        redraw();
    }

    /**
     * drops a new fallingTetromino and makes sure that it is drawn on the
     * canvas.
     */
    public void dropNewTetromino() {
        score.add(grid.clearLines());

        grid.clearLines();

        Coordinate spawnPosition = new Coordinate(grid.width() / 2, grid.height());
        fallingTetromino = new MovableShape(factory.create(queue.pop()), spawnPosition);

        TetrominoType next = queue.peek();
        nextTetromino = factory.create(next);
        PreviewAdapter adapter = new PreviewAdapter(nextTetromino);
        this.leftOffSet = adapter.getLeftOffSet();
        this.bottomOffSet = adapter.getBottomOffSet();
        Logger.log(this, Logger.LogType.INFO, "dropped a new fallingTetromino");
    }

    /**
     * redraw empties the canvas and redraws the gameboard and the current
     * active fallingTetromino.
     */
    private void redraw() {

        clearBoard();
        clearPreview();
        drawGrid();
        drawTetromino();
        drawTetrominoPreview();
        changingMusic();
    }

    /**
     * gameOver handles the end of the game.
     */
    public void gameOver() {
        stopGame();
        if (score.getScore() > 0 && scoreBoard.isHighscore(score.getScore())) {
            ui.gotoPromptNameScreen();
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
        soundManager.stopAll();
    }

    /**
     * stop handles asynchronous threads when the application is closed.
     */
    public void stop() {
        timer.requestStop();
        ui.stop();
        soundManager.stopAll();
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
        grid = new Grid(this, settings.boardWidth(), settings.boardHeight());
        dropNewTetromino();
        timer.unpause();
        ui.resetFocus();
        Logger.log(this, Logger.LogType.INFO, "game started");
        redraw();
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
     * drawTetromino employs the structure of a fallingTetromino to draw it on a
     * gameboard.
     */
    private void drawTetromino() {
        for (int i = 0; i < 4; i++) {
            drawRectangle(fallingTetromino.getColor(), fallingTetromino.get(i));
        }
    }

    private void drawTetrominoPreview() {
        for (int i = 0; i < 4; i++) {
            drawRectanglePreview(nextTetromino.getColor(), nextTetromino.get(i));
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

    public IScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public MovableShape getFallingTetromino() {
        return fallingTetromino;
    }

    public Grid getGrid() {
        return grid;
    }

    public void unpause(Button button) {
        timer.unpause();
        ui.resetFocus();
        gameOver = false;
        button.setOnAction((event) -> pause(button));
    }

    public void pause(Button button) {
        timer.pause();
        gameOver = true;
        button.setOnAction((event) -> unpause(button));
    }

    public void changingMusic() {
        if (score.getScore() < changeInMusic) {

            if (normalTheme == false) {
                AudioStreaming.playTheme();
                normalTheme = true;
            }
        }

        if (score.getScore() > changeInMusic) {
            if (remixTheme == false) {
                AudioStreaming.playRemix();
                remixTheme = true;
            }
        }

    }
}
