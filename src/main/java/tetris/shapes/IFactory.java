package tetris.shapes;

public interface IFactory {
    AbstractShape create(ShapeType type);
    AbstractShape createRandom();
}
