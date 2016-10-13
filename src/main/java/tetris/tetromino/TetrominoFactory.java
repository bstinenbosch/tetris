package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoFactory {
    public static TetrominoType[] queue = null;

    /**
     * Creates a Tetromino.
     *
     * @param type
     *            Type of Tetromino to create
     * @param position
     *            Position of shape in grid
     * @return AbstractShape Tetromino
     */
    public static AbstractTetromino create(TetrominoType type, Coordinate position) {
        switch (type) {
            case I:
                return new TetrominoI(position);
            case J:
                return new TetrominoJ(position);
            case L:
                return new TetrominoL(position);
            case O:
                return new TetrominoO(position);
            case S:
                return new TetrominoS(position);
            case T:
                return new TetrominoT(position);
            case Z:
                return new TetrominoZ(position);
            default:
                return new TetrominoI(position);
        }
    }

    /**
     * Creates a random Tetromino.
     *
     * @param position
     *            Position of shape in grid
     * @return AbstractShape Tetromino
     */

    public static AbstractTetromino createRandom(Coordinate position) {
        TetrominoQueue.addToQueue();
        TetrominoType onScreenTetromino = TetrominoQueue.removeFromQueue();
        return create(onScreenTetromino, position);
    }

    public static AbstractTetromino getLast(Coordinate position2) {
        return create(TetrominoQueue.getLast(), position2);
    }

}
