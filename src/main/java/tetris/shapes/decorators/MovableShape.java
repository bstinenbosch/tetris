package tetris.shapes.decorators;

import java.util.ArrayList;
import java.util.List;

import tetris.shapes.AbstractShape;
import tetris.shapes.IRotatable;
import tetris.shapes.ITranslatable;

import common.Coordinate;
import common.CoordinateSet;

public class MovableShape extends ShapeDecorator implements ITranslatable, IRotatable {

    /**
     * Rotation index (0 = spawning position). Index increases when rotating
     * clockwise and vice versa.
     */
    protected int rotation = 0;

    /**
     * Coordinate in grid relative to pivot point of Tetromino.
     */
    protected Coordinate position;

    public MovableShape(AbstractShape shape, Coordinate position) {
        super(shape);
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

    @Override
    public CoordinateSet getMinos() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate mino : super.getMinos().getCoordinates()) {
            coordinates.add(new Coordinate(mino.getX(), mino.getY()));
        }

        CoordinateSet set = new CoordinateSet(coordinates);

        set.rotateClockwise(rotation);
        set.translateX(position.getX());
        set.translateY(position.getY());

        return set;
    }

    /**
     * Determines vertical position of topmost Mino.
     *
     * @return the y-position of the highest mino of the shape
     */
    public Coordinate top() {
        return this.getMinos().getTopmost();
    }

    /**
     * Determines vertical position of bottommost Mino.
     *
     * @return the y-position of the lowest mino of the shape
     */
    public Coordinate bottom() {
        return this.getMinos().getBottommost();
    }

    /**
     * Determines horizontal position of leftmost Mino.
     *
     * @return the x-position of the leftmost mino of the shape
     */
    public Coordinate left() {
        return this.getMinos().getLeftmost();
    }

    /**
     * Determines horizontal position of rightmost Mino.
     *
     * @return the x-position of the rightmost mino of the shape
     */
    public Coordinate right() {
        return this.getMinos().getRightmost();
    }

    /**
     * Get the rotation.
     * 
     * @return the rotation: 0,1,2 or 3.
     */
    public int rotation() {
        return Math.floorMod(rotation, 4);
    }
}
