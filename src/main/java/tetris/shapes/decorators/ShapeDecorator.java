package tetris.shapes.decorators;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.AbstractShape;

public abstract class ShapeDecorator {

    protected final AbstractShape shape;

    public ShapeDecorator(AbstractShape shape) {
        this.shape = shape;
    }

//    public Coordinate get(int index) {
//        return shape.get(index);
//    }

    public CoordinateSet getMinos() {
        return shape.getMinos();
    }

    public int getColor() {
        return shape.getColor();
    }
}
