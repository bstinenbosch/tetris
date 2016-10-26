package tetris;

import tetris.shapes.adapters.PreviewAdapter;
import tetris.shapes.AbstractShape;
import tetris.shapes.original.TetrominoFactory;
import tetris.shapes.original.TetrominoType;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PreviewAdapterTest {

    private TetrominoType type;
    private AbstractShape Tetromino;
    private TetrominoFactory factory = new TetrominoFactory();

    @Test
    public void testGettingRightCoordinates() {
        type = TetrominoType.I;
        Tetromino = factory.create(type);
        assertTrue(Tetromino.left() == -1);
        assertTrue(Tetromino.right() == 2);
    }

    @Test
    public void testgetLeftOffSetTrue() {
        type = TetrominoType.I;
        Tetromino = factory.create(type);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        // this is 40, not 20, because it's first coordinate is -1
        assertTrue(adapter.getLeftOffSet() == 40);
    }

    @Test
    public void testgetLeftOffSetFalse() {
        type = TetrominoType.I;
        Tetromino = factory.create(type);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        // this is 40, not 20, because it's first coordinate is -1
        assertFalse(adapter.getLeftOffSet() == 50);
    }

    @Test
    public void testgetBottomOffSetTrue() {
        type = TetrominoType.I;
        Tetromino = factory.create(type);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        assertTrue(adapter.getBottomOffSet() == 40);
    }

    @Test
    public void testgetBottomOffSetFalse() {
        type = TetrominoType.I;
        Tetromino = factory.create(type);
        PreviewAdapter adapter = new PreviewAdapter(Tetromino);
        assertFalse(adapter.getBottomOffSet() == 50);
    }

}
