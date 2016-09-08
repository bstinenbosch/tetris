package test.java;

import org.junit.Test;
import main.java.Grid;

import static org.junit.Assert.assertTrue;

public class GridTest {


    @Test
    public void testIfGridIsEmptyAtInitialization() {
        Grid grid = new Grid();
        grid.init();

        int[][] board = grid.getBoard();

        assertTrue(isEmpty(board));
    }

    private Boolean isEmpty(int[][] board) {
        int outerCount, innerCount;
        for (outerCount = 0; outerCount < board.length; outerCount++) {
            for (innerCount = 1; innerCount < board[outerCount].length; innerCount++) {
                if (board[outerCount][innerCount] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

}
