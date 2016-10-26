package tetris.tetromino;

/**
 * Interface for classes able to translate in a two-dimensional space.
 */
public interface ITranslatable {
    void moveDown();

    void moveUp();

    void moveLeft();

    void moveRight();
}
