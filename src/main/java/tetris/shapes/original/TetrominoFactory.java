package tetris.shapes.original;

import tetris.shapes.AbstractTetromino;

public class TetrominoFactory {

    /**
     * Creates a Tetromino.
     *
     * @param type
     *            Type of Tetromino to create
     * @return AbstractShape Tetromino
     */
    public AbstractTetromino create(TetrominoType type) {
        switch (type) {
            case I:
                return new TetrominoI();
            case J:
                return new TetrominoJ();
            case L:
                return new TetrominoL();
            case O:
                return new TetrominoO();
            case S:
                return new TetrominoS();
            case T:
                return new TetrominoT();
            case Z:
                return new TetrominoZ();
            default:
                return new TetrominoI();
        }
    }

}
