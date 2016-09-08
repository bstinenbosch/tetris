package tetris;


public class Tetromino{	
	private int[] x, y;
	private int rotation;
	public int X, Y;
	
	public Tetromino(){
		// TODO random tetromino maken		
		rotation = 0;
		x = new int[] {0, 1, 2, 3};
		y = new int[] {0, 0, 0, 0};
		X = 0;
		Y = 0;
	}
	
	public int[] get(int i){
		if( i<0 || i>=x.length)
			throw new IndexOutOfBoundsException("you are trying to access a block in a tetromino that doesn't exist.");
		switch(rotation % 4){
		case 0:
			return new int[] {X + x[i], Y + y[i]};
		case 1:
			return new int[] {X + y[i], Y - x[i]};
		case 2:
			return new int[] {X - x[i], Y + y[i]};
		case 3:
			return new int[] {X - y[i], Y - x[i]};
		default:
			throw new IllegalArgumentException("You broke Java, big time.");
		}
	}
}
