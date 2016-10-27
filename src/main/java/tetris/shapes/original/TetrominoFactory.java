package tetris.shapes.original;

import tetris.shapes.AbstractShape;

public class TetrominoFactory {

    /**
     * This creates one of the shapes defined in TetrominoType.
     *
     * @param   type             Type of Tetromino to create
     * @return  AbstractShape    Tetromino
     */
    public AbstractShape create(TetrominoType type) {
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
                throw new IllegalArgumentException(String.format("Unknown how to create shape: %s.", type.toString()));
        }
    }

}
