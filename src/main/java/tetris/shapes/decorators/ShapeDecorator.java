package tetris.shapes.decorators;

import tetris.Coordinate;
import tetris.shapes.AbstractShape;

public abstract class ShapeDecorator {

    protected final AbstractShape shape;

    public ShapeDecorator(AbstractShape shape) {
        this.shape = shape;
    }

    public Coordinate get(int index) {
        return shape.get(index);
    }

    public int getColor() {
        return shape.getColor();
    }
}
