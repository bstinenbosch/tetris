package tetris.tetromino;

import java.util.LinkedList;

import tetris.Coordinate;

public class TetrominoFactory {
    public static LinkedList queue = null;

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
        TetrominoQueue.enqueue(TetrominoType.random());
        queue = TetrominoQueue.giveList();
        TetrominoType positie1 = (TetrominoType) queue.get(0);
        TetrominoQueue.enqueue(TetrominoType.random());
        return create(positie1, position);
    }

}
