package tetris;

import tetris.tetromino.AbstractTetromino;

public class DummyController extends Controller {
    public boolean newTetrominoDropped = false;
    public boolean isGameOverBoolean = false;
    private AbstractTetromino tetromino = new DummyShapeO(new Coordinate(100, 100));
    private Grid grid = new Grid(this, 200, 200);

    public DummyController(View ui, Settings settings) {
        super(ui, settings);
    }

    public DummyController() {
        super(null, null);
    }

    @Override
    public void dropNewTetromino() {
        newTetrominoDropped = true;
    }

    @Override
    public void gameOver() {
        isGameOverBoolean = true;
    }

    @Override
    public void startGame() {
        isGameOverBoolean = false;
    }

    @Override
    public boolean isGameOver() {
        return isGameOverBoolean;
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public AbstractTetromino getTetromino() {
        return tetromino;
    }
}