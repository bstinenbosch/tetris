package tetris.shapes;

import common.Coordinate;
import common.CoordinateSet;

public abstract class AbstractShape {

    /**
     * Coordinates of every Mino in a Tetromino.
     */
    private final CoordinateSet minos;

    /**
     * Color ID.
     */
    private int color;

    /**
     * Default constructor to set Tetromino structure and position.
     *
     * @param minos
     *            Coordinates of each Mino
     */
    public AbstractShape(final CoordinateSet minos, int color) {
        this.minos = minos;
        this.color = color;
    }

    /**
     * Returns the color ID of the shape.
     *
     * @return color ID
     */
    public int getColor() {
        return this.color;
    }

    public CoordinateSet getMinos() {
        return this.minos;
    }

    /**
     * Determines vertical position of topmost Mino.
     *
     * @return the y-position of the highest mino of the shape
     */
    public Coordinate top() {
        return this.minos.getTopmost();
    }

    /**
     * Determines vertical position of bottommost Mino.
     *
     * @return the y-position of the lowest mino of the shape
     */
    public Coordinate bottom() {
        return this.minos.getBottommost();
    }

    /**
     * Determines horizontal position of leftmost Mino.
     *
     * @return the x-position of the leftmost mino of the shape
     */
    public Coordinate left() {
        return this.minos.getLeftmost();
    }

    /**
     * Determines horizontal position of rightmost Mino.
     *
     * @return the x-position of the rightmost mino of the shape
     */
    public Coordinate right() {
        return this.minos.getRightmost();
    }
}
