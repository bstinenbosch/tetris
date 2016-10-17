package tetris;

import tetris.tetromino.AbstractTetromino;

import logging.Logger;

public class TetrominoMovementHandler {

    private Controller controller;

    public TetrominoMovementHandler(Controller controller) {
        this.controller = controller;
    }

    /**
     * handler for key events.
     * 
     * @param event
     *            the key event to handle.
     */
    public void handleKeyEvent(String event) {
        AbstractTetromino tetromino = controller.getTetromino();
        Grid grid = controller.getGrid();
        if (controller.isGameOver()) {
            switch (event) {
                case "ROTATE_RIGHT":
                    checkRotateRight(tetromino, grid);
                    break;
                case "MOVE_LEFT":
                    checkMoveLeft(tetromino, grid);
                    break;
                case "MOVE_RIGHT":
                    checkMoveRight(tetromino, grid);
                    break;
                case "ROTATE_LEFT":
                    checkRotateLeft(tetromino, grid);
                    break;
                case "SOFT_DROP":
                    lowerTetromino(tetromino, grid);
                    break;
                case "HARD_DROP":
                    hardDrop(tetromino, grid);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * attempt to rotate the tetromino to the right on the grid. do nothing upon
     * failure.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     */
    private void checkRotateRight(AbstractTetromino tetromino, Grid grid) {
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

    /**
     * attempt to rotate the tetromino to the left on the grid. do nothing upon
     * failure.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     */

    private void checkRotateLeft(AbstractTetromino tetromino, Grid grid) {
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

    /**
     * attempt to move the tetromino to the left on the grid. do nothing upon
     * failure.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     */
    private void checkMoveLeft(AbstractTetromino tetromino, Grid grid) {
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

    /**
     * attempt to move the tetromino to the right on the grid. do nothing upon
     * failure.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     */
    private void checkMoveRight(AbstractTetromino tetromino, Grid grid) {
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
     * attempt to move the tetromino down on the grid. return true on success,
     * or false upon failure.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     * 
     * @return success/failure
     */
    private boolean checkMoveDown(AbstractTetromino tetromino, Grid grid) {
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

    /**
     * move a tetromino down until it hits the ground.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     */
    private void hardDrop(AbstractTetromino tetromino, Grid grid) {
        while (checkMoveDown(tetromino, grid)) {
        }
        lowerTetromino(tetromino, grid);
    }

    /**
     * tries to lower a tetromino. If this is not possible, a new tetromino is
     * launched or the game is over.
     * 
     * @param tetromino
     *            that is attempted to be moved about
     * @param grid
     *            on which the tetromino is moved
     */
    private void lowerTetromino(AbstractTetromino tetromino, Grid grid) {
        if (!checkMoveDown(tetromino, grid)) {
            if (tetromino.top() >= grid.height() - 1) {
                controller.gameOver();
            } else {
                grid.registerTetromino(tetromino);
                controller.dropNewTetromino();
            }
        }
    }
}
