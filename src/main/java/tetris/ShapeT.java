package tetris;

public class ShapeT extends AbstractShape {

	private static final Coordinate[] minos = new Coordinate[]{
			new Coordinate(-1, 0),
			new Coordinate(0, 0),
			new Coordinate(0, 1),
			new Coordinate(1, 0)
	};

	/**
	 * ShapeT is the figure that is dropped on the gameboard.
	 * @param   position        Position of shape in grid
	 */
	public ShapeT(Coordinate position) {
		super(position, minos);
	}
}
