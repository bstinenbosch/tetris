package tetris;

import highscore.ScoreBoard;
import tetris.sound.SoundManager;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

public class DummyController extends Controller {
    public boolean newTetrominoDropped = false;
    public boolean isGameOverBoolean = false;
    private AbstractTetromino tetromino = new DummyShapeO(new Coordinate(100, 100));
    private Grid grid = new Grid(this, 200, 200);

    public DummyController(View ui, Settings settings, TetrominoFactory factory, SoundManager soundManager, Score score, ScoreBoard scoreBoard) {
        super(ui, settings, factory, soundManager, score, scoreBoard);
    }

    public DummyController() {
        super(null, null, null, new SoundManager(2), new Score(), new ScoreBoard("src/main/resources/highscores.xml"));
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