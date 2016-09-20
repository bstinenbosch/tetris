package tetris.tetromino;

import tetris.Coordinate;

public class TetrominoO extends AbstractTetromino {

	private static final Coordinate[] minos = new Coordinate[]{
			new Coordinate(0, 0),
			new Coordinate(0, 1),
			new Coordinate(1, 1),
			new Coordinate(1, 0)
	};

	/**
	 * ShapeO is the figure that is dropped on the gameboard.
	 * @param   position        Position of shape in grid
	 */
	public TetrominoO(Coordinate position) {
		super(position, minos);
	}

}