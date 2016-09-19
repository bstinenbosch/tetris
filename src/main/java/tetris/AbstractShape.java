package tetris;

/**
 * This class shares common logic between distinct Tetromino shapes
 * and thus every Tetromino should inherit from this class.
 */
public abstract class AbstractShape {

    /**
     * Coordinates of every Mino in a Tetromino.
     */
    private Coordinate[] minos;

    /**
     * Rotation index (0 = spawning position).
     * Index increases when rotating clockwise and vice versa.
     */
    private int rotation = 0;

    /**
     * Coordinate in grid relative to pivot point of Tetromino.
     */
    private Coordinate position;

    /**
     * Color ID.
     */
    private int color = 1 + (int) (Math.random() * 6);

    /**
     * Default constructor to set Tetromino structure and position.
     *
     * @param position Position in grid relative to pivot point
     * @param minos Coordinates of each Mino
     */
    public AbstractShape(final Coordinate position, final Coordinate[] minos) {
        this.position = position;
        this.minos = minos;
    }

    /**
     * Returns the color ID of the tetromino.
     *
     * @return color ID
     */
    int getColor() {
        return this.color;
    }

    /**
     * @param index mino index (0<=i<=3).
     * @return array containing x- and y-coordinate of mino
     */
    Coordinate get(int index) {
        if (index < 0 || index >= minos.length) {
            throw new IndexOutOfBoundsException(
                    "you are trying to access a block in a tetromino that doesn't exist.");
        }

        int minoX = minos[index].getX();
        int minoY = minos[index].getY();
        int positionX = position.getX();
        int positionY = position.getY();

        switch (Math.floorMod(rotation, 4)) {
            case 0:
                return new Coordinate(positionX + minoX, positionY + minoY);
            case 1:
                return new Coordinate(positionX + minoY, positionY - minoX);
            case 2:
                return new Coordinate(positionX - minoX, positionY - minoY);
            case 3:
                return new Coordinate(positionX - minoY, positionY + minoX);
            default:
                throw new IndexOutOfBoundsException("This exception should be unreachable.");
        }
    }

    /**
     * Determines vertical position of topmost Mino.
     *
     * @return the y-position of the highest mino of the tetromino
     */
    int top() {
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
    int bottom() {
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
    int left() {
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
     * @return the x-position of the rightmost mino of the tetromino
     */
    int right() {
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
    void moveDown() {
        position.translateY(-1);
    }

    /**
     * Moves the tetromino up one row in the grid.
     */
    void moveUp() {
        position.translateY(1);
    }

    /**
     * Moves the tetromino left one column in the grid.
     */
    void moveLeft() {
        position.translateX(-1);
    }

    /**
     * Moves the tetromino right one column in the grid.
     */
    void moveRight() {
        position.translateX(1);
    }

    /**
     * Rotates the tetromino clockwise in the grid.
     */
    void rotateRight() {
        rotation++;
    }

    /**
     * Rotates the tetromino clockwise in the grid.
     */
    void rotateLeft() {
        rotation--;
    }
}
