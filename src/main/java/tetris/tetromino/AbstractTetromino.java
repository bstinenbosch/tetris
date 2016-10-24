package tetris.tetromino;

import tetris.Coordinate;

public abstract class AbstractTetromino {

    /**
     * Coordinates of every Mino in a Tetromino.
     */
    private Coordinate[] minos;

    /**
     * Rotation index (0 = spawning position). Index increases when rotating
     * clockwise and vice versa.
     */
    private int rotation = 0;

    /**
     * Coordinate in grid relative to pivot point of Tetromino.
     */
    private Coordinate position;

    /**
     * Color ID.
     */
    private int color;

    /**
     * Default constructor to set Tetromino structure and position.
     *
     * @param position
     *            Position in grid relative to pivot point
     * @param minos
     *            Coordinates of each Mino
     */
    public AbstractTetromino(final Coordinate position, final Coordinate[] minos, int color) {
        this.position = position;
        this.minos = minos;
        this.color = color;
    }

    /**
     * Returns the color ID of the tetromino.
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
                "you are trying to access a block in a tetromino that doesn't exist.");
        }

        double minoX = minos[index].getX() - .5;
        double minoY = minos[index].getY() - .5;
        int positionX = position.getX();
        int positionY = position.getY();

        switch (rotation) {
            case 0:
                return new Coordinate(positionX + (int) (.5 + minoX),
                    positionY + (int) (.5 + minoY));
            case 1:
                return new Coordinate(positionX + (int) (.5 + minoY),
                    positionY + (int) (.5 - minoX));
            case 2:
                return new Coordinate(positionX + (int) (.5 - minoX),
                    positionY + (int) (.5 - minoY));
            case 3:
                return new Coordinate(positionX + (int) (.5 - minoY),
                    positionY + (int) (.5 + minoX));
            default:
                throw new IndexOutOfBoundsException("This exception should be unreachable.");
        }
    }

    /**
     * Determines vertical position of topmost Mino.
     *
     * @return the y-position of the highest mino of the tetromino
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
     * @return the y-position of the lowest mino of the tetromino
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
     * @return the x-position of the leftmost mino of the tetromino
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
     * Determines the most left coordinate of a tetromino
     * 
     * @return the most left coordinate of a tetromino
     */
    public Coordinate leftCoor() {
        Coordinate left = this.get(0);
        for (int i = 0; i < 4; i++) {
            if (this.get(i).getX() < left.getX()) {
                left = this.get(i);
            }
        }
        return left;
    }

    /**
     * Determines the most left coordinate of a tetromino
     * 
     * @return the most left coordinate of a tetromino
     */
    public Coordinate rightCoor() {
        Coordinate right = this.get(0);
        for (int i = 0; i < 4; i++) {
            if (this.get(i).getX() > right.getX()) {
                right = this.get(i);
            }
        }
        return right;
    }

    public Coordinate getleft() {
        return this.position;
    }

    /**
     * Determines horizontal position of rightmost Mino.
     *
     * @return the x-position of the rightmost mino of the tetromino
     */
    public int right() {
        int right = 0;
        for (int i = 0; i < 4; i++) {
            Coordinate block = get(i);
            right = Math.max(right, block.getX());
        }
        return right;
    }

    /**
     * Moves the tetromino down one row in the grid.
     */
    public void moveDown() {
        position.translateY(-1);
    }

    /**
     * Moves the tetromino up one row in the grid.
     */
    public void moveUp() {
        position.translateY(1);
    }

    /**
     * Moves the tetromino left one column in the grid.
     */
    public void moveLeft() {
        position.translateX(-1);
    }

    /**
     * Moves the tetromino right one column in the grid.
     */
    public void moveRight() {
        position.translateX(1);
    }

    /**
     * Rotates the tetromino clockwise in the grid.
     */
    public void rotateRight() {
        rotation = Math.floorMod(++rotation, 4);
    }

    /**
     * Rotates the tetromino clockwise in the grid.
     */
    public void rotateLeft() {
        rotation = Math.floorMod(--rotation, 4);
    }

    public int getRotation() {
        return rotation;
    }
}
