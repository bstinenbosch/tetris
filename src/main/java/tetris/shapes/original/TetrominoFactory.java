package tetris.shapes.original;

import tetris.shapes.AbstractShape;
import tetris.shapes.IFactory;
import tetris.shapes.ShapeType;

public class TetrominoFactory implements IFactory {

    /**
     * This creates one of the shapes defined in TetrominoType.
     *
     * @param   type             TetrominoType of Tetromino to create
     * @return  AbstractShape    Tetromino
     */
    public AbstractShape create(ShapeType type) {
        if(type == TetrominoType.I) {
            return new TetrominoI();
        }
        if(type == TetrominoType.J) {
            return new TetrominoJ();
        }
        if(type == TetrominoType.L) {
            return new TetrominoL();
        }
        if(type == TetrominoType.O) {
            return new TetrominoO();
        }
        if(type == TetrominoType.S) {
            return new TetrominoS();
        }
        if(type == TetrominoType.T) {
            return new TetrominoT();
        }
        if(type == TetrominoType.Z) {
            return new TetrominoZ();
        }

        throw new IllegalArgumentException(String.format("Unknown how to create shape: %s.", type.toString()));
    }

    @Override
    public AbstractShape createRandom() {
        return create(TetrominoType.random());
    }

}
