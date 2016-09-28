package tetris;

import tetris.tetromino.AbstractTetromino;

public class Grid {

    private int[][] board;

    /**
     * Grid represents the gameboard.
     *
     * @param width
     *            the width of the gameboard
     * @param height
     *            the heigth of the gameboard
     */
    Grid(int width, int height) {
        board = new int[width][height + 4];
    }

    /**
     * Returns height of playable grid.
     *
     * @return int
     */
    public int height() {
        return board[0].length - 4;
    }

    /**
     * Returns width of playable grid.
     *
     * @return int
     */
    public int width() {
        return board.length;
    }

    /**
     * isFree returns true if the location on the board is free, otherwise
     * false.
     *
     * @param coord
     *            the location on the board to check
     * @return true if the location on the board is free, otherwise false.
     */
    boolean isFree(Coordinate coord) {
        return (coord.getX() >= 0 && coord.getY() >= 0 && coord.getX() < width()
            && coord.getY() < height() + 3 && board[coord.getX()][coord.getY()] == 0);
    }

    /**
     * registerTetromino adds a tetromino to the static part of the gameboard.
     *
     * @param tetromino
     *            the tetromino to add
     */
    public void registerTetromino(AbstractTetromino tetromino) {
        for (int i = 0; i < 4; i++) {
            Coordinate coords = tetromino.get(i);
            board[coords.getX()][coords.getY()] = tetromino.getColor();
        }
    }

    /**
     * clearLines removes any full lines from the game board.
     *
     * @return the score that has been earned with the removed lines
     */
    int clearLines() {
        int row = 0;
        int rowsCleared = 0;
        boolean skipRow;
        int[][] localBoard = new int[width()][height() + 4];
        for (int y = 0; y < localBoard[0].length; y++) {
            skipRow = false;
            for (int x = 0; x < localBoard.length; x++) {
                localBoard[x][row] = board[x][y];
                if (board[x][y] == 0) {
                    skipRow = true;
                }
            }
            if (skipRow) {
                row++;
            } else {
                rowsCleared ++;
            }
        }
        board = localBoard;
        return rowsCleared;
    }

    /**
     * get is an accessor method to retrieve the color ID stored in the
     * gameboard.
     *
     * @param x
     *            horizontal gridcell index
     * @param y
     *            vertical gridcell index
     * @return the color ID at location (x,y) on the board
     */
    int get(int x, int y) {
        return board[x][y];
    }

    void clearBoard() {
        board = new int[board.length][board[0].length];
    }
}
