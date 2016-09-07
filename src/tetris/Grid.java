package tetris;

public class Grid {
	private static int[][] board = new int[10][24];

	public static void init(){
		for (int[] boardRow: board){
			for (int i: boardRow){
				boardRow[i] = 0;
			}
		}
	}
	
	public static void drawBoard() {
		String row = "";
		for (int[] boardRow: board){
			
			for (int i: boardRow){
				boardRow[i] = 0;
			}
		}
	}
}
