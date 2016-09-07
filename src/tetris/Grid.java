package tetris;

public class Grid {
	private static int[][] board = new int[24][10];
	
	public static void init(){
		for (int[] boardRow: board){
			for (int i = 0; i<boardRow.length; i++){
				boardRow[i] = 0;
			}
		}
	}
	
	public static void drawBoard() {
		String row = "";
		for (int[] boardRow: board){
			row = "";
			for (int i = 0; i<boardRow.length; i++){
				row += Integer.toString(boardRow[i]);
			}
			System.out.println(row);
		}
	}
	
	public static int clearLine(){
		int clearedLines = 0;
		Boolean full = true;
		for (int i = 23; i>=0; i--){
			int[] boardRow = board[i];
			full = true;
			for (int j = 0; j<boardRow.length; j++){
				if (boardRow[j] == 0){
					full = false;
				};
			}
			if (full == true){//move row's down
				clearedLines++;
				for (int k = i; k>0; k--){
					for (int j = 0; j<10; j++){
						board[k][j] = board[k-1][j];
					}
				}
				board[0] = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };//empty top row
				i++;
			}
		}
		return clearedLines;
	}
}
