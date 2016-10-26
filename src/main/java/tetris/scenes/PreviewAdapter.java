package tetris.scenes;

import tetris.tetromino.AbstractTetromino;

public class PreviewAdapter {

    private static final int blockSize = 20;
    private final int width;
    private final int height;
    private final int left;
    private final int top;
    private final int bottom;

    /**
     * Creates PreviewTetrominoPaneAdapter object.
     *
     * @param tetromino
     *            tetromino
     */
    public PreviewAdapter(AbstractTetromino tetromino) {
        this.left = tetromino.left();
        this.top = tetromino.top();
        this.bottom = tetromino.bottom();
        this.width = tetromino.right() - left + 1;
        this.height = tetromino.top() - tetromino.bottom() + 1;
    }

    /**
     * Calculates Left offset and returns it.
     *
     * @return left offset
     */
    public int getLeftOffSet() {
        int rightPane = 6;
        if (left >= 0) {
            return ((rightPane * blockSize - width * blockSize) / 2);
        }
        return ((rightPane * blockSize - width * blockSize) / 2) + blockSize;

    }

    /**
     * Calculates Bottom offset and returns it.
     *
     * @return BottomOffSet
     */
    public int getBottomOffSet() {
        int topPane = 6;
        if (bottom <= -1) {
            return (topPane * blockSize - height * blockSize - blockSize - (blockSize / 2));
        }
        return ((topPane * blockSize - height * blockSize - blockSize) / 2);
    }

}