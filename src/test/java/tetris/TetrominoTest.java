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

    @Test
    public void tests() {
        assertTrue(
            TetrominoFactory.createRandom(new Coordinate(0, 0)) instanceof AbstractTetromino);
        assertTrue(TetrominoFactory.getLast(new Coordinate(0, 0)) instanceof AbstractTetromino);
        assertTrue(
            TetrominoFactory.create(TetrominoType.I, new Coordinate(0, 0)) instanceof TetrominoI);
        assertTrue(
            TetrominoFactory.create(TetrominoType.J, new Coordinate(0, 0)) instanceof TetrominoJ);
        assertTrue(
            TetrominoFactory.create(TetrominoType.L, new Coordinate(0, 0)) instanceof TetrominoL);
        assertTrue(
            TetrominoFactory.create(TetrominoType.O, new Coordinate(0, 0)) instanceof TetrominoO);
        assertTrue(
            TetrominoFactory.create(TetrominoType.S, new Coordinate(0, 0)) instanceof TetrominoS);
        assertTrue(
            TetrominoFactory.create(TetrominoType.T, new Coordinate(0, 0)) instanceof TetrominoT);
        assertTrue(
            TetrominoFactory.create(TetrominoType.Z, new Coordinate(0, 0)) instanceof TetrominoZ);
    }
}
