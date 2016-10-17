package tetris;

import tetris.scenes.PreviewAdapter;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;
import tetris.tetromino.TetrominoType;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PreviewAdapterTest {

    private TetrominoType type;
    private AbstractTetromino Tetromino;

    @Test
    public void testGettingRightCoordinates() {
        type = TetrominoType.I;
        Coordinate coordinate = new Coordinate(0, 0);
        Tetromino = TetrominoFactory.create(type, coordinate);
        assertTrue(Tetromino.left() == -1);
        assertTrue(Tetromino.right() == 2);
    }

    @Test
    public void testgetLeftOffSetTrue() {
        type = TetrominoType.I;
        Coordinate coordinate = new Coordinate(0, 0);
        Tetromino = TetrominoFactory.create(type, coordinate);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        // this is 40, not 20, because it's first coordinate is -1
        assertTrue(adapter.getLeftOffSet() == 40);
    }

    @Test
    public void testgetLeftOffSetFalse() {
        type = TetrominoType.I;
        Coordinate coordinate = new Coordinate(0, 0);
        Tetromino = TetrominoFactory.create(type, coordinate);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        // this is 40, not 20, because it's first coordinate is -1
        assertFalse(adapter.getLeftOffSet() == 50);
    }

    @Test
    public void testgetBottomOffSetTrue() {
        type = TetrominoType.I;
        Coordinate coordinate = new Coordinate(0, 0);
        Tetromino = TetrominoFactory.create(type, coordinate);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        assertTrue(adapter.getBottomOffSet() == 40);
    }

    @Test
    public void testgetBottomOffSetFalse() {
        type = TetrominoType.I;
        Coordinate coordinate = new Coordinate(0, 0);
        Tetromino = TetrominoFactory.create(type, coordinate);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        assertFalse(adapter.getBottomOffSet() == 50);
    }

}
