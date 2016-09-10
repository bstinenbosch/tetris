package tetris;


public class Tetromino{	
	private int[] x, y;
	private int rotation;
	private int X, Y;
	private int color;
	
	public Tetromino(int x, int y){
		// TODO random tetromino maken		
		rotation = 0;
		this.x = new int[] {0, 1, 2, 3};
		this.y = new int[] {0, 0, 0, 0};
		X = x;
		Y = y;
		
	}
	
	public int getColor(){
		return color;
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
	
	public int top(){
		int top = 0;
		for(int i=0; i<4; i++){
			int[] block = get(i);
			Math.max(top, Math.max(block[0], block[1]));
		}
		return top;
	}
	
	public int bottom(){
		int bottom = Integer.MAX_VALUE;
		for(int i=0; i<4; i++){
			int[] block = get(i);
			Math.min(bottom, Math.min(block[0], block[1]));
		}
		return bottom;
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
