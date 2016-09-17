package tetris;

public abstract class AbstractShape {

    private Coordinate[] minos;
    private int rotation = 0;
    private Coordinate position;
    private int color = 1+(int)(Math.random()*6);

    public AbstractShape(Coordinate position, Coordinate[] minos) {
        this.position = position;
        this.minos = minos;
    }

    /**
     * getColor returns the color ID of the tetromino
     *
     * @return color ID
     */
    public int getColor() {
        return this.color;
    }

    /**
     * get returns the i-th block coordinate of the tetromino
     *
     * @param i block index (0<=i<=3)
     * @return an array with the coordinates
     */
    public int[] get(int i) {
        if (i < 0 || i >= minos.length)
            throw new IndexOutOfBoundsException("you are trying to access a block in a tetromino that doesn't exist.");

        int minoX = minos[i].getX();
        int minoY = minos[i].getY();
        int positionX = position.getX();
        int positionY = position.getY();

        switch (Math.floorMod(rotation, 4)) {
            case 0:
                return new int[]{positionX + minoX, positionY + minoY};
            case 1:
                return new int[]{positionX + minoY, positionY - minoX};
            case 2:
                return new int[]{positionX - minoX, positionY + minoY};
            case 3:
                return new int[]{positionX - minoY, positionY - minoX};
            default:
                throw new IndexOutOfBoundsException("This exception should be unreachable.");
        }
    }

    /**
     * @return the y-position of the highest block of the tetromino
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
     * @return the y-position of the lowest block of the tetromino
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
     * @return the x-position of the leftmost block of the tetromino
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
     * @return the x-position of the rightmost block of the tetromino
     */
    public int right() {
        int right = 0;
        for (int i = 0; i < 4; i++) {
            int[] block = get(i);
            right = Math.max(right, block[0]);
        }
        return right;
    }

    public void moveDown() {
        position.translateY(-1);
    }

    public void moveUp() {
        position.translateY(1);
    }

    public void moveLeft() {
        position.translateX(-1);
    }

    public void moveRight() {
        position.translateX(1);
    }

    public void rotateRight() {
        rotation++;
    }

    public void rotateLeft() {
        rotation--;
    }
}
