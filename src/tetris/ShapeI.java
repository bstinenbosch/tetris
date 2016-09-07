package tetris;

public class ShapeI implements IShape {

	public static int[][] shape0 = 
		{
			{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0}
		};
	

	public static int[][] shape1 = 
		{
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0}
		};
	

	public static int[][] shape2 = 
		{
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0}
		};
	

	public static int[][] shape3 = 
		{
			{0,0,0,0},
			{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0}
		};

	public int[][] getShape(int rotation) {
		int[][] shape = new int[4][4];
		
		switch (rotation) {
			case 0:
				shape = ShapeI.shape0;
				break;
			case 1:
				shape = ShapeI.shape1;
				break;
			case 2:
				shape = ShapeI.shape2;
				break;
			case 3:
				shape = ShapeI.shape3;
				break;
			default:
				throw new IllegalArgumentException();
		}
		
		return shape;
	}

}
