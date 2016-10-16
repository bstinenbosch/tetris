package tetris.scenes;

import tetris.tetromino.AbstractTetromino;

public class PreviewAdapter {

    private static final int blockSize = 20;
    private final int width;
    private final int height;

    /**
     * Creates PreviewTetrominoPaneAdapter object.
     *
     * @param tetromino tetromino
     */
    public PreviewAdapter(AbstractTetromino tetromino) {
        this.width = tetromino.right() - tetromino.left() + 1;
        this.height = tetromino.top() - tetromino.bottom() + 1;
    }

    /**
     * Calculates Left offset and returns it.
     *
     * @return left offset
     */
    public int getLeftOffSet() {
        int rightPane = 6;
        return ((rightPane * blockSize - width * blockSize) / 2)
                + blockSize;
    }

    /**
     * Calculates Bottom offset and returns it.
     *
     * @return BottomOffSet
     */
    public int getBottomOffSet() {
        int topPane = 6;
        return ((topPane * blockSize - height * blockSize
                - blockSize) / 2);
    }

}