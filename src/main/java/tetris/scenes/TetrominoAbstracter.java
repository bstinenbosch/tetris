package tetris.scenes;

import tetris.Coordinate;
import tetris.tetromino.AbstractTetromino;

public class TetrominoAbstracter {

    private static int leftSidePane = 0;
    private static int rightSidePane = 6;
    private static int topPane = 6;
    private static int bottomPane = 0;
    int minimumGap = 0;

    /**
     * This method abstracts the top, bottom, left, right of the tetromino to an
     * offset
     * 
     * @param tetromino
     */

    public static Coordinate abstractTetromino(AbstractTetromino tetromino) {

        int left = tetromino.left();
        int right = tetromino.right();
        int top = tetromino.top();
        int bottom = tetromino.bottom();

        int minimumGapHorizontal = 6;

        int rightGap = rightSidePane - right;
        int leftGap = left - leftSidePane;

        if (Math.abs(rightGap - leftGap) < minimumGapHorizontal) {
            minimumGapHorizontal = (rightSidePane - right + left) / 2;
        }

        int Xposition = (minimumGapHorizontal);

        //
        // int minimumGapVertical = 6;
        // for (int i = 0; i < topPane; i++) {
        // int topGap = topPane - top;
        // int bottomGap = bottom - bottomPane;
        //
        // if (Math.abs(topGap - bottomGap) < minimumGapVertical) {
        // minimumGapVertical = Math.abs(topGap - bottomGap) / 2;
        // }
        // }
        //
        // int Yposition = (bottomPane + minimumGapVertical);

        Coordinate secondposition2 = new Coordinate(Xposition, 2);
        System.out.println("MG= " + minimumGapHorizontal + "X= " + Xposition);

        return secondposition2;
    }

}
