package tetris;

import tetris.shapes.IFactory;
import tetris.shapes.original.*;
import tetris.shapes.original.TetrominoType;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TetrominoFactoryTest {

    private IFactory factory = new TetrominoFactory();

    @Test
    public void test_creation_of_random_tetromino() {
        assertTrue(factory.create(TetrominoType.random()) != null);
    }

    @Test
    public void test_creation_of_tetromino_I_shape() {
        assertTrue(factory.create(TetrominoType.I) instanceof TetrominoI);
    }

    @Test
    public void test_creation_of_tetromino_J_shape() {
        assertTrue(factory.create(TetrominoType.J) instanceof TetrominoJ);
    }

    @Test
    public void test_creation_of_tetromino_L_shape() {
        assertTrue(factory.create(TetrominoType.L) instanceof TetrominoL);
    }

    @Test
    public void test_creation_of_tetromino_O_shape() {
        assertTrue(factory.create(TetrominoType.O) instanceof TetrominoO);
    }

    @Test
    public void test_creation_of_tetromino_S_shape() {
        assertTrue(factory.create(TetrominoType.S) instanceof TetrominoS);
    }

    @Test
    public void test_creation_of_tetromino_T_shape() {
        assertTrue(factory.create(TetrominoType.T) instanceof TetrominoT);
    }

    @Test
    public void test_creation_of_tetromino_Z_shape() {
        assertTrue(factory.create(TetrominoType.Z) instanceof TetrominoZ);
    }
}
