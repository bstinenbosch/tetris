package tetris;

import tetris.tetromino.AbstractTetromino;

import logging.Logger;

public enum Action implements IActionItem {
    ROTATE_RIGHT {
        @Override
        public String toString() {
            return "Rotate right";
        }

        @Override
        public synchronized void individualAttempt(AbstractTetromino tetromino, Grid grid) {
            tetromino.rotateRight();
            // checkRotate(tetromino, grid);
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.rotateLeft();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to rotate tetromino clockwise but failed");
                }
            }
            Logger.log(this, Logger.LogType.INFO, "rotated tetromino clockwise");
        }
    },
    MOVE_LEFT {
        @Override
        public String toString() {
            return "Move left";
        }

        @Override
        public synchronized void individualAttempt(AbstractTetromino tetromino, Grid grid) {
            if (tetromino.left() > 0) {
                tetromino.moveLeft();
            }
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.moveRight();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to move tetromino left but failed");
                    break;
                }
            }
            Logger.log(this, Logger.LogType.INFO, "moved tetromino left");
        }
    },
    MOVE_RIGHT {
        @Override
        public String toString() {
            return "Move right";
        }

        @Override
        public synchronized void individualAttempt(AbstractTetromino tetromino, Grid grid) {
            if (tetromino.right() < grid.width() - 1) {
                tetromino.moveRight();
            }
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.moveLeft();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to move tetromino right but failed");
                    break;
                }
            }
            Logger.log(this, Logger.LogType.INFO, "moved tetromino right");
        }
    },
    ROTATE_LEFT {
        @Override
        public String toString() {
            return "Rotate left";
        }

        @Override
        public synchronized void individualAttempt(AbstractTetromino tetromino, Grid grid) {
            tetromino.rotateLeft();
            // checkRotate(tetromino, grid);
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.rotateRight();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to rotate tetromino counter clockwise but failed");
                }
            }
            Logger.log(this, Logger.LogType.INFO, "rotated counter tetromino clockwise");
        }
    },
    SOFT_DROP {
        @Override
        public String toString() {
            return "Soft drop";
        }

        @Override
        public void individualAttempt(AbstractTetromino tetromino, Grid grid) {
            if (!checkMoveDown(tetromino, grid)) {
                grid.registerTetromino(tetromino);
            }
        }
    },
    HARD_DROP {
        @Override
        public String toString() {
            return "Hard drop";
        }

        @Override
        public synchronized void individualAttempt(AbstractTetromino tetromino, Grid grid) {
            while (checkMoveDown(tetromino, grid)) {
            }
            grid.registerTetromino(tetromino);
        }
    },
    INVALID_ACTION {
        @Override
        public synchronized void individualAttempt(AbstractTetromino tetromino, Grid grid) {
        }
    };
    public synchronized void attempt(AbstractTetromino tetromino, Grid grid) {
        if (!locked) {
            locked = true;
            individualAttempt(tetromino, grid);
            locked = false;
        }
    }

    public abstract void individualAttempt(AbstractTetromino tetromino, Grid grid);

    private static volatile boolean locked;

    /**
     * checkmovedown attempts a move down and reports whether he had success.
     * 
     * @param tetromino
     *            the tetro to move down
     * @param grid
     *            the grid to move down on
     * @return whether the move succeeded
     */
    private static boolean checkMoveDown(AbstractTetromino tetromino, Grid grid) {
        tetromino.moveDown();
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.moveUp();
                return false;
            }
        }
        return true;
    }

    private static void checkRotate(AbstractTetromino tetromino, Grid grid) {
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.leftCoor())) {
                tetromino.moveRight();
            } else if (!grid.isFree(tetromino.rightCoor())) {
                tetromino.moveLeft();
            }
        }
    }

    public static boolean testINVALIDACTION(AbstractTetromino tetromino, Grid grid) {
        Action.INVALID_ACTION.attempt(tetromino, grid);
        return true;
    }
}
