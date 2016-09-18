package tetris;

public class ShapeL extends AbstractShape {

	private static int color = 3;

	private static final Coordinate[] minos = new Coordinate[]{
			new Coordinate(-1, 0),
			new Coordinate(0, 0),
			new Coordinate(1, 0),
			new Coordinate(1, 1)
	};

	/**
	 * ShapeL is the figure that is dropped on the gameboard.
	 * @param   position        Position of shape in grid
	 */
	public ShapeL(Coordinate position) {
		super(position, minos, color);
	}
}
