package tetris.shapes.custom;

import tetris.shapes.AbstractShape;
import tetris.shapes.IFactory;
import tetris.shapes.ShapeType;

public class TrominoFactory implements IFactory {

    /**
     * This creates one of the shapes defined in TrominoType.
     *
     * @param   type             TetrominoType of Tetromino to create
     * @return  AbstractShape    Tetromino
     */
    public AbstractShape create(ShapeType type) {
        if(type == TrominoType.I) {
            return new TrominoI();
        }
        if(type == TrominoType.J) {
            return new TrominoJ();
        }
        if(type == TrominoType.L) {
            return new TrominoL();
        }

        throw new IllegalArgumentException(String.format("Unknown how to create shape: %s.", type.toString()));
    }

    @Override
    public AbstractShape createRandom() {
        return create(TrominoType.random());
    }
}
