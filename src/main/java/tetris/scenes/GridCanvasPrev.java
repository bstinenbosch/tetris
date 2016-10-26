package tetris.scenes;

import tetris.Controller;
import tetris.Coordinate;
import tetris.Grid;
import tetris.Settings;
import tetris.tetromino.AbstractTetromino;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GridCanvasPrev extends Canvas {

    private Settings settings;
    private Grid grid;
    private int leftOffSet;
    private int bottomOffSet;
    private AbstractTetromino tetromino;
    private AbstractTetromino tetromino2;
    private Controller controller;

    public void redraw() {
        clearPreview();
        drawGrid();
        drawTetrominoPreview();
    }

    public GridCanvasPrev(Settings settings) {
        super(settings.blockSize() * 6, settings.blockSize() * 5);
        settings.setPreview(this.getGraphicsContext2D());
        this.settings = settings;
    }

    /**
     * drawGrid draws the entire gameboard. As tetrominos reach their final
     * place, they are registered on the grid to be drawn by this function.
     */
    private void drawGrid() {
        for (int x = 0; x < settings.boardWidthPrev(); x++) {
            for (int y = 0; y < settings.boardHeightPrev(); y++) {
                drawRectanglePreview(grid.get(x, y), new Coordinate(x, y));
            }
        }
    }

    /**
     * drawRectanglePreview draws one cube on the preview grid.
     *
     * @param color
     *            specifies the color pair to draw in (color pairs provided by
     *            setColor)
     * @param coordinate
     *            the cube in the grid that is to be drawn.
     */
    private void drawRectanglePreview(int color, Coordinate coordinate) {
        if (color > 0) {
            settings.getPreview().setFill(settings.getColor(color));
            settings.getPreview().fillRoundRect(
                coordinate.getX() * settings.blockSize() + this.leftOffSet,
                (5 - 1 - coordinate.getY()) * settings.blockSize() - this.bottomOffSet,
                settings.blockSize(), settings.blockSize(), settings.corner(), settings.corner());
        }
    }

    private void drawTetrominoPreview() {
        for (int i = 0; i < 4; i++) {
            drawRectanglePreview(tetromino2.getColor(), tetromino2.get(i));
        }
    }

    private void clearPreview() {
        settings.getPreview().setFill(Color.BLACK);
        settings.getPreview().fillRect(0, 0, settings.boardWidthPrev() * settings.blockSize(),
            settings.boardHeightPrev() * settings.blockSize());
    }

    public void setTetrominoPrev(AbstractTetromino tetromino) {
        this.tetromino2 = tetromino;
    }

    public void setLeftOffSet(int offset) {
        this.leftOffSet = offset;
    }

    public void setBottomOffSet(int offset) {
        this.bottomOffSet = offset;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

}
