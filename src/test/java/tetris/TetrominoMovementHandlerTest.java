package tetris;

import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;
import tetris.tetromino.TetrominoType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TetrominoMovementHandlerTest {

    private class FakeController extends Controller {
        public int newTetrominosDropped = 0;

        public FakeController(View ui, Settings settings) {
            super(ui, settings);
        }

        @Override
        public void dropNewTetromino() {
            newTetrominosDropped++;
        }

    }

    @Test
    public void checkMovements() {
        FakeController controller = new FakeController(new View(), new Settings());
        TetrominoMovementHandler handler = new TetrominoMovementHandler(controller);
        Grid grid = new Grid(10, 20);
        for (int i = 0; i < 10; i += 2) {
            AbstractTetromino tetromino = new DummyShapeO(new Coordinate(i, 20));
            handler.checkMoveLeft(tetromino, grid);
            if (i > 0) {
                handler.checkMoveRight(tetromino, grid);
            }
            handler.checkRotateLeft(tetromino, grid);
            handler.checkRotateRight(tetromino, grid);
            handler.hardDrop(tetromino, grid);
        }
        assertEquals(grid.clearLines(), 2);
        assertEquals(controller.newTetrominosDropped, 5);
        AbstractTetromino tetromino = new DummyShapeI(new Coordinate(10, 20));
        for (int i = 0; i < 20; i++) {
            handler.checkMoveLeft(tetromino, grid);
        }
        handler.checkRotateLeft(tetromino, grid);
        for (int i = 0; i < 5; i++) {
            handler.checkMoveLeft(tetromino, grid);
        }
        for (int i = 0; i < 20; i++) {
            handler.checkMoveRight(tetromino, grid);
        }
        handler.checkRotateRight(tetromino, grid);
        for (int i = 0; i < 5; i++) {
            handler.checkMoveRight(tetromino, grid);
        }
        handler.checkRotateRight(tetromino, grid);
    }

}
