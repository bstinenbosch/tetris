package tetris;

public abstract class AbstractShape {

    private Coordinate[] minos;
    private int rotation = 0;
    private Coordinate position;
    private int color;

    public AbstractShape(Coordinate position, Coordinate[] minos, int color) {
        this.position = position;
        this.minos = minos;
        this.color = color;
    }

    /**
     * Returns the color ID of the tetromino
     *
     * @return color ID
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @param i mino index (0<=i<=3)
     * @return array containing x- and y-coordinate of mino
     */
    public Coordinate get(int i) {
        if (i < 0 || i >= minos.length)
            throw new IndexOutOfBoundsException("you are trying to access a block in a tetromino that doesn't exist.");

        int minoX = minos[i].getX();
        int minoY = minos[i].getY();
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
     * Moves the tetromino down one row in the grid
     */
    public void moveDown() {
        position.translateY(-1);
    }

    /**
     * Moves the tetromino up one row in the grid
     */
    public void moveUp() {
        position.translateY(1);
    }

    /**
     * Moves the tetromino left one column in the grid
     */
    public void moveLeft() {
        position.translateX(-1);
    }

    /**
     * Moves the tetromino right one column in the grid
     */
    public void moveRight() {
        position.translateX(1);
    }

    /**
     * Rotates the tetromino clockwise in the grid
     */
    public void rotateRight() {
        rotation++;
    }

    /**
     * Rotates the tetromino clockwise in the grid
     */
    public void rotateLeft() {
        rotation--;
    }
}
