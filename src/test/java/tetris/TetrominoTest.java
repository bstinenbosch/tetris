package tetris;

import tetris.shapes.original.TetrominoFactory;
import tetris.shapes.original.TetrominoI;
import tetris.shapes.original.TetrominoJ;
import tetris.shapes.original.TetrominoL;
import tetris.shapes.original.TetrominoO;
import tetris.shapes.original.TetrominoS;
import tetris.shapes.original.TetrominoT;
import tetris.shapes.original.TetrominoType;
import tetris.shapes.original.TetrominoZ;

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
