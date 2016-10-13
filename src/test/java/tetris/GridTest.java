package tetris;

import tetris.tetromino.TetrominoFactory;
import tetris.tetromino.TetrominoType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridTest {

    @Test
    public void testIfGridIsEmptyAtInitialization() {
        Grid grid = new Grid(10, 20);
        assertTrue(isEmpty(grid));
        Coordinate coord = new Coordinate(5, 5);
        grid.registerTetromino(TetrominoFactory.create(TetrominoType.O, coord));
        assertFalse(grid.isFree(coord));
        assertFalse(isEmpty(grid));
        grid.clearBoard();
        assertTrue(isEmpty(grid));
        assertTrue(grid.isFree(coord));

        grid.registerTetromino(TetrominoFactory.create(TetrominoType.O, new Coordinate(0, 0)));
        grid.registerTetromino(TetrominoFactory.create(TetrominoType.O, new Coordinate(2, 0)));
        grid.registerTetromino(TetrominoFactory.create(TetrominoType.O, new Coordinate(4, 0)));
        grid.registerTetromino(TetrominoFactory.create(TetrominoType.O, new Coordinate(6, 0)));
        grid.registerTetromino(TetrominoFactory.create(TetrominoType.O, new Coordinate(8, 0)));
        assertEquals(2, grid.clearLines());
        assertTrue(isEmpty(grid));
    }

    private boolean isEmpty(Grid grid) {
        for (int x = 0; x < grid.width(); x++) {
            for (int y = 1; y < grid.height(); y++) {
                if (grid.get(x, y) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
