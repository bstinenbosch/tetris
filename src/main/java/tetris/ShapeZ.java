package tetris;

public class ShapeZ extends AbstractShape {

	private static final int[] x = new int[] {-1, 0, 0, 1};
	private static final int[] y = new int[] {1, 1, 0, 0};

	/**
	 * ShapeZ is the figure that is dropped on the gameboard.
	 * @param X the x coordinate of the tetromino
	 * @param Y the y coordinate of the tetromino
	 */
	public ShapeZ(int X, int Y) {
		super(X, Y, x, y);
	}

}
