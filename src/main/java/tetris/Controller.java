package tetris;

import java.util.Observer;

import tetris.scenes.GridCanvas;
import tetris.scenes.PreviewAdapter;
import tetris.sound.SoundManager;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

import highscore.GameEntry;
import highscore.ScoreBoard;
import logging.Logger;

public class Controller {

    private final SoundManager soundManager;
    private Score score;
    private ScoreBoard scoreBoard;
    private View ui;
    private Grid grid;
    private AbstractTetromino tetromino;
    private AbstractTetromino tetromino2;
    private boolean gameOver = false;
    private Tick timer = new Tick(event -> {
        Platform.runLater(() -> Action.SOFT_DROP.attempt(tetromino, grid));
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
        scoreBoard = new ScoreBoard("src/main/resources/highscores.xml");
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
        if (action.attempt(tetromino, grid)) {
            soundManager.play("move");
        }
        redraw();
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
        ((GridCanvas) settings.getBoard().getCanvas()).redraw();
        ((GridCanvas) settings.getPreview().getCanvas()).redraw();
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

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public AbstractTetromino getTetromino() {
        return tetromino;
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
