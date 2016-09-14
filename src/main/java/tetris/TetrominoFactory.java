package tetris;

public class TetrominoFactory {

    /**
     * Creates a Tetromino
     *
     * @param   type            Type of Tetromino to create
     * @param   x               X-coordinate of shape in grid
     * @param   y               Y-coordinate of shape in grid
     * @return  AbstractShape   Tetromino
     */
    public static AbstractShape create(ShapeType type, int x, int y) {
        switch(type) {
            case I:
                return new ShapeI(x, y);
            case J:
                return new ShapeJ(x, y);
            case L:
                return new ShapeL(x, y);
            case O:
                return new ShapeO(x, y);
            case S:
                return new ShapeS(x, y);
            case T:
                return new ShapeT(x, y);
            case Z:
                return new ShapeZ(x, y);
            default:
                return new ShapeI(x, y);
        }
    }

    /**
     * Creates a random Tetromino
     *
     * @param   x               X-coordinate of shape in grid
     * @param   y               Y-coordinate of shape in grid
     * @return  AbstractShape   Tetromino
     */
    public static AbstractShape createRandom(int x, int y) {
        return create(ShapeType.random(), x, y);
    }
}
