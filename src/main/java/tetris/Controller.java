package tetris;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import logging.Logger;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

public class Controller {

    private class Score extends Observable {
        private int score = 0;

        /**
         * resets the score to 0.
         */
        public void reset() {
            score = 0;
        }

        /**
         * increases the score by add.
         * 
         * @param add
         *            add add points to the score
         */
        public void add(int add) {
            score += add;
            setChanged();
            notifyObservers(score);
        }
    }

    private Score score;
    private View ui;
    private Grid grid;
    private AbstractTetromino tetromino;
    private boolean gameOver = false;
    private EventHandler<ActionEvent> onTick = event -> lowerTetromino();
    private Tick timer = new Tick(onTick);

    /**
     * the Controller class determines the game flow and does the actual event
     * handling.
     * 
     * @param ui
     *            the application in which the game is running
     */
    Controller(View ui) {
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
                default:
                    break;
            }
            redraw();
        }
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
        Coordinate position = new Coordinate(grid.width() / 2, grid.height());
        tetromino = TetrominoFactory.createRandom(position);
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
    public void startGame(int width, int height) {
        ui.gotoGameScreen();
        score.addObserver(timer);
        score.addObserver(ui.getScoreLabel());
        score.reset();
        gameOver = false;
        grid = new Grid(width, height);
        dropNewTetromino();
        timer.start();
        Logger.log(this, Logger.LogType.INFO, "game started");
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
