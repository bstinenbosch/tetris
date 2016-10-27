package tetris.shapes;

import tetris.Coordinate;

public abstract class AbstractShape {

    /**
     * Coordinates of every Mino in a Tetromino.
     */
    private Coordinate[] minos;

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
    public AbstractShape(final Coordinate[] minos, int color) {
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

    /**
     * @param index
     *            mino index (0<=i<=3).
     * @return array containing x- and y-coordinate of mino
     */
    public Coordinate get(int index) {
        if (index < 0 || index >= minos.length) {
            throw new IndexOutOfBoundsException(
                "you are trying to access a block in a shape that doesn't exist.");
        }

        return new Coordinate(minos[index].getX(), minos[index].getY());
    }

    /**
     * Determines vertical position of topmost Mino.
     *
     * @return the y-position of the highest mino of the shape
     */
    public int top() {
        int top = 0;
        for (int i = 0; i < 4; i++) {
            Coordinate block = get(i);
            top = Math.max(top, block.getY());
        }
        return top;
    }

    /**
     * Determines vertical position of bottommost Mino.
     *
     * @return the y-position of the lowest mino of the shape
     */
    public int bottom() {
        int bottom = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            Coordinate block = get(i);
            bottom = Math.min(bottom, block.getY());
        }
        return bottom;
    }

    /**
     * Determines horizontal position of leftmost Mino.
     *
     * @return the x-position of the leftmost mino of the shape
     */
    public int left() {
        int left = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            Coordinate block = get(i);
            left = Math.min(left, block.getX());
        }

        return left;
    }

    /**
     * Determines horizontal position of rightmost Mino.
     *
     * @return the x-position of the rightmost mino of the shape
     */
    public int right() {
        int right = 0;
        for (int i = 0; i < 4; i++) {
            Coordinate block = get(i);
            right = Math.max(right, block.getX());
        }
        return right;
    }
}
