package tetris.scenes;

import tetris.shapes.AbstractTetromino;

public class PreviewAdapter {

    private static final int blockSize = 20;
    private final int width;
    private final int height;
    private final int left;

    /**
     * Creates PreviewTetrominoPaneAdapter object.
     *
     * @param tetromino
     *            tetromino
     */
    public PreviewAdapter(AbstractTetromino tetromino) {
        this.left = tetromino.left();
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
        return ((topPane * blockSize - height * blockSize - blockSize) / 2);
    }

}