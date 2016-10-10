package tetris;

import tetris.tetromino.AbstractTetromino;

import logging.Logger;

public class TetrominoMovementHandler {

    private Controller controller;

    public TetrominoMovementHandler(Controller controller) {
        this.controller = controller;
    }

    public void checkRotateRight(AbstractTetromino tetromino, Grid grid) {
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

    public void checkRotateLeft(AbstractTetromino tetromino, Grid grid) {
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

    public void checkMoveLeft(AbstractTetromino tetromino, Grid grid) {
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

    public void checkMoveRight(AbstractTetromino tetromino, Grid grid) {
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
    public boolean checkMoveDown(AbstractTetromino tetromino, Grid grid) {
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
     */
    public void hardDrop(AbstractTetromino tetromino, Grid grid) {
        while (checkMoveDown(tetromino, grid)) {
        }
        lowerTetromino(tetromino, grid);
    }

    /**
     * tries to lower a tetromino. If this is not possible, a new tetromino is
     * launched or the game is over.
     */
    public void lowerTetromino(AbstractTetromino tetromino, Grid grid) {
        if (checkMoveDown(tetromino, grid)) {
        } else if (tetromino.top() >= grid.height() - 1) {
            controller.gameOver();
        } else {
            grid.registerTetromino(tetromino);
            controller.dropNewTetromino();
        }
    }
}
