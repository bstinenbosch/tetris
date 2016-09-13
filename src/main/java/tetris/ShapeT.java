package tetris;

public class ShapeT extends AbstractShape {

	private static final int[] x = new int[] {-1, 0, 1, 0};
	private static final int[] y = new int[] {0, 0, 0, 1};

	/**
	 * ShapeT is the figure that is dropped on the gameboard.
	 * @param X the x coordinate of the tetromino
	 * @param Y the y coordinate of the tetromino
	 */
	public ShapeT(int X, int Y) {
		super(X, Y, x, y);
	}
}
