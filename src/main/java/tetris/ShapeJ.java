package tetris;

public class ShapeJ extends AbstractShape {

	private static final Coordinate[] minos = new Coordinate[]{
			new Coordinate(-1, 1),
			new Coordinate(-1, 0),
			new Coordinate(0, 0),
			new Coordinate(1, 0)
	};

	/**
	 * ShapeJ is the figure that is dropped on the gameboard.
	 * @param   position        Position of shape in grid
	 */
	public ShapeJ(Coordinate position) {
		super(position, minos);
	}

}
