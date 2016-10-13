package tetris.scenes;

import tetris.tetromino.AbstractTetromino;

public class PreviewTetrominoPaneAdapter {

    private static int RightPane = 6;
    private static int LeftPane = 0;
    private static int TopPane = 6;
    private static int BottomPane = 0;
    private static int BlockSize = 20;
    private int right;
    private int left;
    private int top;
    private int bottom;
    private int width;
    private int height;

    public PreviewTetrominoPaneAdapter(AbstractTetromino tetromino) {

        this.left = tetromino.left();
        this.right = tetromino.right();
        this.top = tetromino.top();
        this.bottom = tetromino.bottom();
        this.width = tetromino.right() - tetromino.left() + 1;
        this.height = tetromino.top() - tetromino.bottom() + 1;

    }

    public int getLeftOffSet() {

        int LeftOffSet = (this.RightPane * this.BlockSize - this.right * BlockSize) / 2;
        return LeftOffSet;

    }

    public int getTopOffSet() {

        int TopOffSet = (this.TopPane * this.BlockSize - this.height * BlockSize) / 2;
        return TopOffSet;

    }

}
