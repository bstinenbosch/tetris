package tetris;

import java.util.Observer;

import tetris.scenes.GridCanvas;
import tetris.scenes.GridCanvasPrev;
import tetris.shapes.adapters.PreviewAdapter;
import tetris.shapes.AbstractShape;
import tetris.shapes.decorators.MovableShape;
import tetris.shapes.original.TetrominoFactory;
import tetris.shapes.original.TetrominoType;
import tetris.sound.SoundManager;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

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
    private TetrominoFactory factory = new TetrominoFactory();
    private GridCanvas gridcanvas;
    private GridCanvasPrev gridcanvasprev;
    private boolean gameOver = false;
    private Tick timer = new Tick(event -> {
        Platform.runLater(() -> Action.SOFT_DROP.attempt(fallingTetromino, grid));
        Platform.runLater(() -> gridcanvas.redraw());
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
        this.gridcanvas = new GridCanvas(settings);
        this.gridcanvasprev = new GridCanvasPrev(settings);
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
        soundManager.load("theme", getClass().getClassLoader().getResource("sound/theme.mp3"));
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
     * redraws the GridCanvas and GridCanvasPreview
     *
     */
    public void redraw() {
        gridcanvas.redraw();
        gridcanvasprev.redraw();
    }

    /**
     * drops a new fallingTetromino and makes sure that it is drawn on the canvas.
     */
    public void dropNewTetromino() {
        score.add(grid.clearLines());

        grid.clearLines();

        Coordinate spawnPosition = new Coordinate(grid.width() / 2, grid.height());
        fallingTetromino = new MovableShape(factory.create(queue.pop()), spawnPosition);
        gridcanvas.setTetromino(fallingTetromino);

        TetrominoType next = queue.peek();
        AbstractShape nextTetromino = factory.create(next);
        gridcanvasprev.setTetrominoPrev(nextTetromino);

        PreviewAdapter adapter = new PreviewAdapter(nextTetromino);
        gridcanvasprev.setLeftOffSet(adapter.getLeftOffSet());
        gridcanvasprev.setBottomOffSet(adapter.getBottomOffSet());
        Logger.log(this, Logger.LogType.INFO, "dropped a new tetromino");
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
        gridcanvas.setGrid(grid);
        dropNewTetromino();
        soundManager.play("theme");
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

}
