package tetris;

import common.Coordinate;
import common.CoordinateSet;
import tetris.shapes.decorators.MovableShape;

public class Grid {

    public static final int INVISIBLE_GRID_ROWS = 4;
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
        board = new int[width][height + INVISIBLE_GRID_ROWS];
    }

    /**
     * Returns height of playable grid.
     *
     * @return int
     */
    public int height() {
        return board[0].length - INVISIBLE_GRID_ROWS;
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
     */
    boolean isFree(CoordinateSet coordinates) {
        for(Coordinate coordinate : coordinates.getCoordinates()) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if(x < 0 || y < 0) {
                return false;
            }

            if(x >= width() || y >= height() + INVISIBLE_GRID_ROWS) {
                return false;
            }

            if(board[x][y] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * register adds a shape to the static part of the gameboard.
     *
     * @param tetromino
     *            the shape to add
     */
    public boolean register(MovableShape tetromino) {
        if(!isFree(tetromino.getMinos())) {
            return false;
        }

        CoordinateSet minos = tetromino.getMinos();
        for(Coordinate mino : minos.getCoordinates()) {
            board[mino.getX()][mino.getY()] = tetromino.getColor();
        }

        return true;
    }

    /**
     * clearLines removes any full lines from the game board.
     *
     * @return the score that has been earned with the removed lines
     */
    public int clearLines() {
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
                rowsCleared++;
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
    public int get(int x, int y) {
        return board[x][y];
    }

    public void clearBoard() {
        board = new int[board.length][board[0].length];
    }
}
