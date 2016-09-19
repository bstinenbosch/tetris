package tetris;

class TetrominoFactory {

    /**
     * Creates a Tetromino.
     *
     * @param type     Type of Tetromino to create
     * @param position Position of shape in grid
     * @return AbstractShape   Tetromino
     */
    private static AbstractShape create(ShapeType type, Coordinate position) {
        switch (type) {
            case I:
                return new ShapeI(position);
            case J:
                return new ShapeJ(position);
            case L:
                return new ShapeL(position);
            case O:
                return new ShapeO(position);
            case S:
                return new ShapeS(position);
            case T:
                return new ShapeT(position);
            case Z:
                return new ShapeZ(position);
            default:
                return new ShapeI(position);
        }
    }

    /**
     * Creates a random Tetromino.
     *
     * @param position Position of shape in grid
     * @return AbstractShape   Tetromino
     */
    static AbstractShape createRandom(Coordinate position) {
        return create(ShapeType.random(), position);
    }
}
