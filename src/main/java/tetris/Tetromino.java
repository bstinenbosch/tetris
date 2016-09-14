package main.java.tetris;

public class Tetromino{	
	private int[] x, y;
	private int rotation;
	private int X, Y;
	private int color;
	
	/**
	 * Tetromino is the figure that is dropped on the gameboard.
	 * @param x the x coordinate of the tetromino
	 * @param y the y coordinate of the tetromino
	 */
	public Tetromino(int x, int y){
		// TODO random tetromino maken		
		color = 1 + (int)Math.round(Math.random()*1);
		rotation = 0;
		this.x = new int[] {-1, 0, 1, 2};
		this.y = new int[] {0, 0, 0, 0};
		X = x;
		Y = y;		
	}
	
	/**
	 * getColor returns the color ID of the tetromino
	 * @return color ID
	 */
	public int getColor(){
		return color;
	}
	
	/**
	 * get returns the i-th block coordinate of the tetromino
	 * @param i block index (0<=i<=3)
	 * @return an array with the coordinates
	 */
	public int[] get(int i){
		if( i<0 || i>=x.length)
			throw new IndexOutOfBoundsException("you are trying to access a block in a tetromino that doesn't exist.");
		switch(Math.floorMod(rotation, 4)){
		case 0:
			return new int[] {X + x[i], Y + y[i]};
		case 1:
			return new int[] {X + y[i], Y - x[i]};
		case 2:
			return new int[] {X - x[i], Y + y[i]};
		case 3:
			return new int[] {X - y[i], Y - x[i]};
		default:
			throw new IndexOutOfBoundsException("This exception should be unreachable.");
		}
	}
	
	/**
	 * 
	 * @return the y-position of the highest block of the tetromino
	 */
	public int top(){
		int top = 0;
		for(int i=0; i<4; i++){
			int[] block = get(i);
			top = Math.max(top, block[1]);
		}
		return top;
	}

	/**
	 * 
	 * @return the y-position of the lowest block of the tetromino
	 */
	public int bottom(){
		int bottom = Integer.MAX_VALUE;
		for(int i=0; i<4; i++){
			int[] block = get(i);
			bottom = Math.min(bottom, block[1]);
		}
		return bottom;
	}
	
	/**
	 * 
	 * @return the x-position of the leftmost block of the tetromino
	 */
	public int left(){
		int left = Integer.MAX_VALUE;
		for(int i=0; i<4; i++){
			int[] block = get(i);
			left = Math.min(left, block[0]);
		}
		return left;
	}

	/**
	 * 
	 * @return the x-position of the rightmost block of the tetromino
	 */
	public int right(){
		int right = 0;
		for(int i=0; i<4; i++){
			int[] block = get(i);
			right = Math.max(right, block[0]);
		}
		return right;
	}
	
	public void moveDown(){
		Y--;
	}
	
	public void moveUp(){
		Y++;
	}
	
	public void moveLeft(){
		X--;
	}
	
	public void moveRight(){
		X++;
	}

	public void rotateRight(){
		rotation++;
	}
	
	public void rotateLeft(){
		rotation--;
	}
}