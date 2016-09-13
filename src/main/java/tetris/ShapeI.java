package tetris;

public class ShapeI extends AbstractShape {

	private static final int[] x = new int[] {-1, 0, 1, 2};
	private static final int[] y = new int[] {0, 0, 0, 0};

	/**
	 * ShapeI is the figure that is dropped on the gameboard.
	 * @param X the x coordinate of the tetromino
	 * @param Y the y coordinate of the tetromino
	 */
	public ShapeI(int X, int Y) {
		super(X, Y, x, y);
	}
}
