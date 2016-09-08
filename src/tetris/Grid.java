package tetris;

public class Grid {

    private int[][] board = new int[24][10];

    public void init() {
        for (int[] boardRow : board) {
            for (int i = 0; i < boardRow.length; i++) {
                boardRow[i] = 0;
            }
        }
    }

    public int clearLine() {
        int clearedLines = 0;
        for (int i = 23; i >= 0; i--) {
            int[] boardRow = board[i];
            Boolean full = true;
            for (int aBoardRow : boardRow) {
                if (aBoardRow == 0) {
                    full = false;
                }
            }
            if (full) { //move row's down
                clearedLines++;
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < 10; j++) {
                        board[k][j] = board[k - 1][j];
                    }
                }
                board[0] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //empty top row
                i++;
            }
        }
        return clearedLines;
    }

    public int[][] getBoard()
    {
        return this.board;
    }
}
