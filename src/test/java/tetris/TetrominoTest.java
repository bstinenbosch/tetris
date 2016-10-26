package tetris;

import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;
import tetris.tetromino.TetrominoI;
import tetris.tetromino.TetrominoJ;
import tetris.tetromino.TetrominoL;
import tetris.tetromino.TetrominoO;
import tetris.tetromino.TetrominoS;
import tetris.tetromino.TetrominoT;
import tetris.tetromino.TetrominoType;
import tetris.tetromino.TetrominoZ;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TetrominoTest {

    private TetrominoFactory factory = new TetrominoFactory();

    @Test
    public void tests() {
        assertTrue(factory.create(TetrominoType.random()) != null);
        assertTrue(factory.create(TetrominoType.I) instanceof TetrominoI);
        assertTrue(factory.create(TetrominoType.J) instanceof TetrominoJ);
        assertTrue(factory.create(TetrominoType.L) instanceof TetrominoL);
        assertTrue(factory.create(TetrominoType.O) instanceof TetrominoO);
        assertTrue(factory.create(TetrominoType.S) instanceof TetrominoS);
        assertTrue(factory.create(TetrominoType.T) instanceof TetrominoT);
        assertTrue(factory.create(TetrominoType.Z) instanceof TetrominoZ);
    }
}
