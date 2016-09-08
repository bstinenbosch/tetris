package tetris;

public class GridOutputter {

    private Grid grid;

    public GridOutputter(Grid grid) {
        this.grid = grid;
    }

    public void drawBoard() {
        int[][] board = grid.getBoard();

        String row;
        for (int[] boardRow : board) {
            row = "";
            for (int aBoardRow : boardRow) {
                row += Integer.toString(aBoardRow);
            }
            System.out.println(row);
        }
    }
}
