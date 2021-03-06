package tetris.scenes;

import common.Coordinate;
import tetris.Settings;
import tetris.shapes.AbstractShape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GridCanvasPrev extends Canvas {

    private Settings settings;
    private int leftOffSet;
    private int bottomOffSet;
    private AbstractShape tetromino;

    public void redraw() {
        clearPreview();
        drawTetrominoPreview();
    }

    /**
     * Constructor
     * 
     * @param settings
     */
    public GridCanvasPrev(Settings settings) {
        super(settings.blockSize() * settings.boardWidthPrev(),
            settings.blockSize() * settings.boardHeightPrev());
        settings.setPreview(this.getGraphicsContext2D());
        this.settings = settings;
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

    /**
     * Draws the preview tetromino
     *
     */

    private void drawTetrominoPreview() {
        for(Coordinate mino : tetromino.getMinos().getCoordinates()) {
            drawRectanglePreview(tetromino.getColor(), mino);
        }
    }

    /**
     * Clears the preview window
     * 
     */
    private void clearPreview() {
        settings.getPreview().setFill(Color.BLACK);
        settings.getPreview().fillRect(0, 0, settings.boardWidthPrev() * settings.blockSize(),
            settings.boardHeightPrev() * settings.blockSize());
    }

    /**
     * sets the tetromino that is to be drawn, to be able to use this class in
     * the controller
     * 
     * @param tetromino
     */
    public void setTetrominoPrev(AbstractShape tetromino) {
        this.tetromino = tetromino;
    }

    /**
     * Sets the leftOffSet that should be used, gets it from the controller
     * 
     * @param offset
     */
    public void setLeftOffSet(int offset) {
        this.leftOffSet = offset;
    }

    /**
     * Sets the bottomOffSet that should be used, gets it from the controller
     * 
     * @param offset
     */
    public void setBottomOffSet(int offset) {
        this.bottomOffSet = offset;
    }

}
