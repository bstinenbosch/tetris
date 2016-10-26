package tetris.scenes;

import tetris.Coordinate;
import tetris.Grid;
import tetris.Settings;
import tetris.tetromino.AbstractTetromino;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GridCanvas extends Canvas {

    private Settings settings;
    private Grid grid;
    private AbstractTetromino tetromino;

    public void redraw() {
        clearBoard();
        drawGrid();
        drawTetromino();
    }

    /**
     * Constructor
     * 
     * @param settings
     */
    public GridCanvas(Settings settings) {
        super(settings.blockSize() * settings.boardWidth(),
            settings.blockSize() * settings.boardHeight());
        settings.setBoard(this.getGraphicsContext2D());
        this.settings = settings;
    }

    /**
     * drawGrid draws the entire gameboard. As tetrominos reach their final
     * place, they are registered on the grid to be drawn by this function.
     */
    private void drawGrid() {
        for (int x = 0; x < settings.boardWidth(); x++) {
            for (int y = 0; y < settings.boardHeight(); y++) {
                drawRectangle(grid.get(x, y), new Coordinate(x, y));
            }
        }
    }

    /**
     * drawTetromino employs the structure of a tetromino to draw it on a
     * gameboard.
     */
    private void drawTetromino() {
        for (int i = 0; i < 4; i++) {
            drawRectangle(tetromino.getColor(), tetromino.get(i));
        }
    }

    /**
     * drawRectangle draws one cube on the game grid.
     * 
     * @param color
     *            specifies the color pair to draw in (color pairs provided by
     *            setColor)
     * @param coordinate
     *            the cube in the grid that is to be drawn.
     */
    private void drawRectangle(int color, Coordinate coordinate) {
        if (color > 0) {
            settings.getBoard().setFill(settings.getColor(color));
            settings.getBoard().fillRoundRect(coordinate.getX() * settings.blockSize(),
                (settings.boardHeight() - 1 - coordinate.getY()) * settings.blockSize(),
                settings.blockSize(), settings.blockSize(), settings.corner(), settings.corner());
        }
    }

    /**
     * clearBoard erases the current board so it can be redrawn.
     */
    private void clearBoard() {
        settings.getBoard().setFill(Color.BLACK);
        settings.getBoard().fillRect(0, 0, settings.boardWidth() * settings.blockSize(),
            settings.boardHeight() * settings.blockSize());
    }

    /**
     * sets the tetromino that is to be drawn, to be able to use this class in
     * the controller
     * 
     * @param tetromino
     */
    public void setTetromino(AbstractTetromino tetromino) {
        this.tetromino = tetromino;
    }

    /**
     * Sets the grid that is used in the controller in here
     * 
     * @param grid
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

}
