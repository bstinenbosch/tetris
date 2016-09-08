package tetris;

public class Coordinate {
	// TODO implement index
	private int x;
	private int y;
	public int[] get(){
		return new int[] {x,y};
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinate plus(Coordinate other){
		int[] a = other.get(); // TODO dit wat mooier
		x += a[0];
		y += a[1];
		return this;
	}
}
