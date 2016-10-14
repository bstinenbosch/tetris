package tetris.scenes;

import tetris.tetromino.AbstractTetromino;

public class PreviewAdapter {

    private static int RightPane = 6;
    private static int TopPane = 6;
    private static int BlockSize = 20;
    private int right;
    private int left;
    private int top;
    private int bottom;
    private int width;
    private int height;

    /**
     * Creates PreviewTetrominoPaneAdapter object
     * 
     * @param tetromino
     */

    public PreviewAdapter(AbstractTetromino tetromino) {

        this.left = tetromino.left();
        this.right = tetromino.right();
        this.top = tetromino.top();
        this.bottom = tetromino.bottom();
        this.width = tetromino.right() - tetromino.left() + 1;
        this.height = tetromino.top() - tetromino.bottom() + 1;

    }

    /**
     * Calculates Left offset and returns it.
     * 
     * @return
     */
    public int getLeftOffSet() {

        int LeftOffSet = ((this.RightPane * this.BlockSize - this.width * BlockSize) / 2)
            + 1 * BlockSize;
        return LeftOffSet;

    }

    /**
     * Calculates Bottom offset and returns it.
     * 
     * @return BottomOffSet
     */
    public int getBottomOffSet() {

        int BottomOffSet = ((this.TopPane * this.BlockSize - this.height * BlockSize
            - 1 * BlockSize) / 2);
        return BottomOffSet;

    }

}