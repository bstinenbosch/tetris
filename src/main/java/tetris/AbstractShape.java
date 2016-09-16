package tetris;

public abstract class AbstractShape {

    protected int[] x, y;
    private int rotation = 0;
    protected int X, Y;
    private int color = 1+(int)(Math.random()*6);

    public AbstractShape(int X, int Y, int[] x, int[] y) {
        this.X = X;
        this.Y = Y;
        this.x = x;
        this.y = y;
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
     * @param i block index (0<=i<=3)
     * @return array containing x- and y-coordinate of mino
     */
    public int[] get(int i) {
        if (i < 0 || i >= x.length)
            throw new IndexOutOfBoundsException("you are trying to access a block in a tetromino that doesn't exist.");
        switch (Math.floorMod(rotation, 4)) {
            case 0:
                return new int[]{X + x[i], Y + y[i]};
            case 1:
                return new int[]{X + y[i], Y - x[i]};
            case 2:
                return new int[]{X - x[i], Y + y[i]};
            case 3:
                return new int[]{X - y[i], Y - x[i]};
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
            int[] block = get(i);
            top = Math.max(top, block[1]);
        }
        return top;
    }

    /**
     * @return the y-position of the lowest mino of the tetromino
     */
    public int bottom() {
        int bottom = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int[] block = get(i);
            bottom = Math.min(bottom, block[1]);
        }
        return bottom;
    }

    /**
     * @return the x-position of the leftmost mino of the tetromino
     */
    public int left() {
        int left = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int[] block = get(i);
            left = Math.min(left, block[0]);
        }
        return left;
    }

    /**
     * @return the x-position of the rightmost mino of the tetromino
     */
    public int right() {
        int right = 0;
        for (int i = 0; i < 4; i++) {
            int[] block = get(i);
            right = Math.max(right, block[0]);
        }
        return right;
    }

    /**
     * Moves the tetromino down one row in the grid
     */
    public void moveDown() {
        Y--;
    }

    /**
     * Moves the tetromino up one row down in the grid
     */
    public void moveUp() {
        Y++;
    }

    /**
     * Moves the tetromino left one column in the grid
     */
    public void moveLeft() {
        X--;
    }

    /**
     * Moves the tetromino right one column in the grid
     */
    public void moveRight() {
        X++;
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
