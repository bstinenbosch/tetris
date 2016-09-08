package tetris;

import java.util.Iterator;

public class Tetromino implements Iterable<Coordinate>{	

	private class CoordinateIterator implements Iterator<Coordinate>{
		private int index;
		private Coordinate[] coordinates;
		private int rotation;
		private Coordinate topLeft;
		
		public CoordinateIterator(Coordinate[] coordinates, int rotation, int x, int y){
			index = 0;
			this.coordinates = coordinates;
			this.rotation = rotation;
			topLeft = new Coordinate(x, y);
		}
		
		@Override
		public boolean hasNext() {
			// TODO is this off-by-one?
			return ( index < coordinates.length );
		}

		@Override
		public Coordinate next() {
			// TODO dit laten afhangen van de rotatie
			return coordinates[index++].plus(topLeft);
		}		
	}

	private Coordinate[] coordinates;
	private int rotation;
	
	public Tetromino (){
		// TODO random tetromino maken
		coordinates = new int[][] {{0,1,2,3},{0,0,0,0}};
		rotation = 0;
	}
	
	public Tetromino(String shape){
		this(0, 0, shape, 0);
	}
	
	public Tetromino(String shape, int rotation){
		this(0, 0, shape, rotation);
	}
	public Tetromino(int x, int y, String shape, int rotation) {
		// TODO create tetromino
	}
	
	@Override
	public Iterator<tetris.Coordinate> iterator() {
		return new CoordinateIterator(coordinates, rotation);
	}
}
