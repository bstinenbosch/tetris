package tetris;

import java.util.Observable;
import java.util.Observer;

import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import logging.Logger;

public class Controller {

    private class Score extends Observable {
        private int score = 0;
        private int level = 0;
        private int[] pointsPerRow = { 0, 40, 50, 100, 300 };

        /**
         * resets the score to 0.
         */
        public void reset() {
            score = 0;
            level = 0;
        }

        /**
         * increases the score by add.
         * 
         * @param rowsCleared
         *            add add points to the score
         */
        public void add(int rowsCleared) {
            score += rowsCleared * pointsPerRow[rowsCleared];
            level += rowsCleared;
            setChanged();
            notifyObservers(score);
        }

        /**
         * @return the current level that has been reached.
         */
        public int getLevel() {
            return level / 10;
        }
    }

    private Score score;
    private View ui;
    private Grid grid;
    private AbstractTetromino tetromino;
    private AbstractTetromino tetromino2;
    private boolean gameOver = false;
    private EventHandler<ActionEvent> onTick = event -> lowerTetromino();
    private Tick timer = new Tick(onTick);

    public Settings settings;

    /**
     * the Controller class determines the game flow and does the actual event
     * handling.
     * 
     * @param ui
     *            the application in which the game is running
     */
    Controller(View ui, Settings settings) {
        this.settings = settings;
        this.ui = ui;
        Logger.setDebugOn();
        score = new Score();
    }

    /**
     * handler for key events.
     * 
     * @param event
     *            the key event to handle.
     */
    public void handleKeyEvent(KeyEvent event) {
        if (!gameOver) {
            switch (event.getCode()) {
                case DOWN:
                    checkRotateRight();
                    break;
                case LEFT:
                    checkMoveLeft();
                    break;
                case RIGHT:
                    checkMoveRight();
                    break;
                case UP:
                    checkRotateLeft();
                    break;
                case SPACE:
                    hardDrop();
                    break;
                default:
                    break;
            }
            redraw();
        }
    }

    /**
     * move a tetromino down until it hits the ground.
     */
    private void hardDrop() {
        while (checkMoveDown()) {
        }
        lowerTetromino();
    }

    /**
     * tries to lower a tetromino. If this is not possible, a new tetromino is
     * launched or the game is over.
     */
    private void lowerTetromino() {
        if (checkMoveDown()) {
            redraw();
        } else if (tetromino.top() >= grid.height() - 1) {
            gameOver();
        } else {
            grid.registerTetromino(tetromino);
            dropNewTetromino();
        }
    }

    /**
     * drops a new tetromino and makes sure that it is drawn on the canvas.
     */
    private void dropNewTetromino() {
        score.add(grid.clearLines());

        grid.clearLines();
        Coordinate position = new Coordinate(grid.width() / 2, grid.height());
        Coordinate position2 = new Coordinate(grid.width() / 5 + 2 / 5, grid.height() - 4);

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
        ui.clearBoard();
        ui.drawGrid(grid);
        ui.drawTetromino(tetromino);
        ui.drawTetrominoPreview(tetromino2);
    }

    /**
     * gameOver handles the end of the game.
     */
    private void gameOver() {
        timer.pause();
        gameOver = true;
        Logger.log(this, Logger.LogType.INFO, "game restarted");
        ui.gameOver();
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
        score.addObserver(timer);
        // score.addObserver(ui.getScoreLabel());
        score.reset();
        gameOver = false;
        grid = new Grid(settings.boardWidth(), settings.boardHeight());
        dropNewTetromino();
        timer.start();
        Logger.log(this, Logger.LogType.INFO, "game started");
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

    private void checkRotateRight() {
        tetromino.rotateRight();
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.rotateLeft();
                Logger.log(this, Logger.LogType.INFO,
                    "tried to rotate tetromino clockwise but failed");
                break;
            }
        }
        Logger.log(this, Logger.LogType.INFO, "rotated tetromino clockwise");
    }

    private void checkRotateLeft() {
        tetromino.rotateLeft();
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.rotateRight();
                Logger.log(this, Logger.LogType.INFO,
                    "tried to rotate tetromino counterclockwise but failed");
                break;
            }
        }
        Logger.log(this, Logger.LogType.INFO, "rotated tetromino counterclockwise");
    }

    private void checkMoveLeft() {
        if (tetromino.left() > 0) {
            tetromino.moveLeft();
        }
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.moveRight();
                Logger.log(this, Logger.LogType.INFO, "tried to move tetromino left but failed");
                break;
            }
        }
        Logger.log(this, Logger.LogType.INFO, "moved tetromino left");
    }

    private void checkMoveRight() {
        if (tetromino.right() < grid.width() - 1) {
            tetromino.moveRight();
        }
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.moveLeft();
                Logger.log(this, Logger.LogType.INFO, "tried to move tetromino right but failed");
                break;
            }
        }
        Logger.log(this, Logger.LogType.INFO, "moved tetromino right");
    }

    /**
     * checks the lowerability of the tetromino and if possible lowers it.
     * 
     * @return true on succes, false on failure to load.
     */
    private boolean checkMoveDown() {
        tetromino.moveDown();
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.moveUp();
                Logger.log(this, Logger.LogType.INFO, "tried to move tetromino down but failed");
                return false;
            }
        }
        Logger.log(this, Logger.LogType.INFO, "moved tetromino down");
        return true;
    }
}
