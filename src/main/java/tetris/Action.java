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
        public boolean attempt(MovableShape tetromino, Grid grid) {
            tetromino.rotateRight();
            checkRotate(tetromino, grid);
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.rotateLeft();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to rotate shape clockwise but failed");
                    return false;
                }
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
        public boolean attempt(MovableShape tetromino, Grid grid) {
            if (tetromino.left() > 0) {
                tetromino.moveLeft();
            }
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.moveRight();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to move shape left but failed");
                    return false;
                }
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
        public boolean attempt(MovableShape tetromino, Grid grid) {
            if (tetromino.right() < grid.width() - 1) {
                tetromino.moveRight();
            }
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.moveLeft();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to move shape right but failed");
                    return false;
                }
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
        public boolean attempt(MovableShape tetromino, Grid grid) {
            tetromino.rotateLeft();
            checkRotate(tetromino, grid);
            for (int i = 0; i < 4; i++) {
                if (!grid.isFree(tetromino.get(i))) {
                    tetromino.rotateRight();
                    Logger.log(this, Logger.LogType.INFO,
                        "tried to rotate shape counter clockwise but failed");
                    return false;
                }
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
        public boolean attempt(MovableShape tetromino, Grid grid) {
            if (!checkMoveDown(tetromino, grid)) {
                grid.registerTetromino(tetromino);
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
        public boolean attempt(MovableShape tetromino, Grid grid) {
            while (checkMoveDown(tetromino, grid)) {
                // checkMoveDown moves the shape down already,
                // so we don't need to do anything in here
            }
            grid.registerTetromino(tetromino);
            return true;
        }
    },

    INVALID_ACTION {
        @Override
        public boolean attempt(MovableShape tetromino, Grid grid) {
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
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.get(i))) {
                tetromino.moveUp();
                return false;
            }
        }
        return true;
    }

    private static void checkRotate(MovableShape tetromino, Grid grid) {
        for (int i = 0; i < 4; i++) {
            if (!grid.isFree(tetromino.leftCoor())) {
                tetromino.moveRight();
            } else if (!grid.isFree(tetromino.rightCoor())) {
                tetromino.moveLeft();
            }
        }
    }

    public static boolean testINVALIDACTION(MovableShape tetromino, Grid grid) {
        Action.INVALID_ACTION.attempt(tetromino, grid);
        return true;
    }
}
