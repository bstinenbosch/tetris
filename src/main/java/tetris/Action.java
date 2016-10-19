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
        public void attempt(AbstractTetromino tetromino, Grid grid) {
            tetromino.rotateRight();

            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.leftCoor())) {
                    tetromino.moveRight();
                    Logger.log(this, Logger.LogType.INFO,
                        "rotated tetromino clockwise and moved to the right to make it possible");
                }
            }
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.rightCoor())) {
                    tetromino.moveLeft();
                    Logger.log(this, Logger.LogType.INFO,
                        "rotated tetromino clockwise and moved to the left to make it possible");
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
        public void attempt(AbstractTetromino tetromino, Grid grid) {
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
        public void attempt(AbstractTetromino tetromino, Grid grid) {
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
        public void attempt(AbstractTetromino tetromino, Grid grid) {
            tetromino.rotateRight();
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.leftCoor())) {
                    tetromino.moveRight();
                    Logger.log(this, Logger.LogType.INFO,
                        "rotated tetromino counter clockwise and moved to the right to make it possible");
                }
            }
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.rightCoor())) {
                    tetromino.moveLeft();
                    Logger.log(this, Logger.LogType.INFO,
                        "rotated tetromino counter clockwise and moved to the left to make it possible");
                }
            }
            Logger.log(this, Logger.LogType.INFO, "rotated tetromino counter clockwise");
        }
    },
    SOFT_DROP {
        @Override
        public String toString() {
            return "Soft drop";
        }

        @Override
        public void attempt(AbstractTetromino tetromino, Grid grid) {
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
        public void attempt(AbstractTetromino tetromino, Grid grid) {
            while (checkMoveDown(tetromino, grid)) {
            }
            grid.registerTetromino(tetromino);
        }
    },
    INVALID_ACTION {
        @Override
        public void attempt(AbstractTetromino tetromino, Grid grid) {
        }
    };

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
}
