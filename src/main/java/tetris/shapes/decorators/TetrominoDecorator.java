package tetris.shapes.decorators;

import tetris.Coordinate;
import tetris.shapes.AbstractTetromino;

public abstract class TetrominoDecorator {

    protected final AbstractTetromino tetromino;

    public TetrominoDecorator(AbstractTetromino tetromino) {
        this.tetromino = tetromino;
    }

    public Coordinate get(int index) {
        return tetromino.get(index);
    }

    public int getColor() {
        return tetromino.getColor();
    }
}
