package tetris;

public class ShapeO implements IShape {

	public static int[][] shape = 
		{
			{4,4},
			{4,4}
		};
	

	public int[][] getShape(int rotation) {		
		return shape;
	}

}
