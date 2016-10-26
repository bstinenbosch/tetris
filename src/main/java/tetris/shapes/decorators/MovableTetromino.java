package tetris.shapes.decorators;

import tetris.Coordinate;
import tetris.shapes.AbstractTetromino;
import tetris.shapes.IRotatable;
import tetris.shapes.ITranslatable;

public class MovableTetromino extends TetrominoDecorator implements ITranslatable, IRotatable {

    /**
     * Rotation index (0 = spawning position). Index increases when rotating
     * clockwise and vice versa.
     */
    protected int rotation = 0;

    /**
     * Coordinate in grid relative to pivot point of Tetromino.
     */
    protected Coordinate position;

    public MovableTetromino(AbstractTetromino tetromino, Coordinate position) {
        super(tetromino);
        this.position = position;
    }

    @Override
    public void moveDown() {
        position.translateY(-1);
    }

    @Override
    public void moveUp() {
        position.translateY(1);
    }

    @Override
    public void moveLeft() {
        position.translateX(-1);
    }

    @Override
    public void moveRight() {
        position.translateX(1);
    }

    @Override
    public void rotateRight() {
        rotation++;
    }

    @Override
    public void rotateLeft() {
        rotation--;
    }

    public Coordinate get(int index) {
        Coordinate mino = tetromino.get(index);

        double minoX = mino.getX() - .5;
        double minoY = mino.getY() - .5;
        int positionX = position.getX();
        int positionY = position.getY();

        switch (Math.floorMod(rotation, 4)) {
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
}
