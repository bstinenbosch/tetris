package tetris;

public class Grid {
	private static int[][] board = new int[10][24];

	public void init(){
		for(int i=0; i< board[0].length; i++){
			for(int j=0; j< board.length;j++){
				System.out.print(".");
			}System.out.println("");
	}
}
