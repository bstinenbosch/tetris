package tetris.scenes;

import tetris.Coordinate;
import tetris.Grid;
import tetris.Settings;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GridCanvas extends Canvas {

    private Settings settings;
    private int width;
    private int height;
    private Grid grid;
    private int leftOffSet;
    private int bottomOffSet;

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
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
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
        settings.getPreview().fillRect(0, 0, 6 * settings.blockSize(), 5 * settings.blockSize());
    }

}
