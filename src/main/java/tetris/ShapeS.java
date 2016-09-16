package tetris;

public class ShapeS extends AbstractShape {

	private static final int[] x = new int[] {-1, 0, 0, 1};
	private static final int[] y = new int[] {0, 0, 1, 1};

	/**
	 * ShapeS is the figure that is dropped on the gameboard.
	 * @param X the x coordinate of the tetromino
	 * @param Y the y coordinate of the tetromino
	 */
	public ShapeS(int X, int Y) {
		super(X, Y, x, y);
	}
}
