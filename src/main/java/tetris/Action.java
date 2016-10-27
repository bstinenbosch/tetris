package tetris;

import logging.Logger;
import tetris.shapes.decorators.MovableShape;

public enum Action implements IActionItem {
    ROTATE_RIGHT {
        @Override
        public String toString() {
            return "Rotate right";
        }

        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
            tetromino.rotateRight();
//            checkRotate(tetromino, grid);
            if (!grid.isFree(tetromino.getMinos())) {
                tetromino.rotateLeft();
                Logger.log(this, Logger.LogType.INFO,
                    "tried to rotate shape clockwise but failed");
                return false;
            }
            Logger.log(this, Logger.LogType.INFO, "rotated shape clockwise");
            return true;
        }
    },
    MOVE_LEFT {
        @Override
        public String toString() {
            return "Move left";
        }

        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
//            if (tetromino.left() > 0) {
                tetromino.moveLeft();
//            }
            if (!grid.isFree(tetromino.getMinos())) {
                tetromino.moveRight();
                Logger.log(this, Logger.LogType.INFO,
                    "tried to move shape left but failed");
                return false;
            }

            Logger.log(this, Logger.LogType.INFO, "moved shape left");
            return true;
        }
    },
    MOVE_RIGHT {
        @Override
        public String toString() {
            return "Move right";
        }

        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
            tetromino.moveRight();
            if (!grid.isFree(tetromino.getMinos())) {
                tetromino.moveLeft();
                Logger.log(this, Logger.LogType.INFO,
                    "tried to move shape right but failed");
                return false;
            }
            Logger.log(this, Logger.LogType.INFO, "moved shape right");
            return true;
        }
    },
    ROTATE_LEFT {
        @Override
        public String toString() {
            return "Rotate left";
        }

        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
            tetromino.rotateLeft();
//            checkRotate(tetromino, grid);
            if (!grid.isFree(tetromino.getMinos())) {
                tetromino.rotateRight();
                Logger.log(this, Logger.LogType.INFO,
                    "tried to rotate shape counter clockwise but failed");
                return false;
            }
            Logger.log(this, Logger.LogType.INFO, "rotated counter shape clockwise");
            return true;
        }
    },
    SOFT_DROP {
        @Override
        public String toString() {
            return "Soft drop";
        }

        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
            if (!checkMoveDown(tetromino, grid)) {
                if(grid.register(tetromino)) {
                    controller.dropNewTetromino();
                } else {
                    controller.gameOver();
                }
            }
            return true;
        }
    },
    HARD_DROP {
        @Override
        public String toString() {
            return "Hard drop";
        }

        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
            while (checkMoveDown(tetromino, grid)) {
                // checkMoveDown moves the shape down already,
                // so we don't need to do anything in here
            }
            if(grid.register(tetromino)) {
                controller.dropNewTetromino();
            } else {
                controller.gameOver();
            }
            return true;
        }
    },

    INVALID_ACTION {
        @Override
        public boolean attempt(MovableShape tetromino, Grid grid, Controller controller) {
            // invalid action, do nothing
            return false;
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
    private static boolean checkMoveDown(MovableShape tetromino, Grid grid) {
        tetromino.moveDown();
        if (!grid.isFree(tetromino.getMinos())) {
            tetromino.moveUp();
            return false;
        }
        return true;
    }

    private static void checkRotate(MovableShape tetromino, Grid grid) {
        if (!grid.isFree(tetromino.getMinos())) {
            tetromino.moveRight();
        }
//        else if (!grid.isFree(tetromino.rightCoor())) {
//            tetromino.moveLeft();
//        }
    }
}
