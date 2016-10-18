package tetris;

public class DummyController extends Controller {
    public boolean newTetrominoDropped = false;
    public boolean isGameOver = false;

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
        isGameOver = true;
    }

}