package tetris.tetromino;

import tetris.Coordinate;

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
