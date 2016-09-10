package main.java;

import tetris.Tetromino;

public class Grid {

    private int[][] board;
    
    public Grid(int height, int width){
    	board = new int[height][width];
    }
    
    public int height(){
    	return board.length;
    }
    
    public int width(){
    	return board[0].length;
    }

    public boolean isFree(int[] coord){
    	return (board[coord[0]][coord[1]] == 0);
    }
    
    public void init() {
        for (int[] boardRow : board) {
            for (int i = 0; i < boardRow.length; i++) {
                boardRow[i] = 0;
            }
        }
    }
    
    public void registerTetromino(Tetromino tetromino){
    	for(int i=0; i<4; i++){
    		// TODO teken tetromino
    		int[] coords = tetromino.get(i);
    		board[coords[0]][coords[1]] = tetromino.getColor();
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
