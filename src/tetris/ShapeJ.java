package tetris;

public class ShapeJ implements IShape {

	public static int[][] shape0 = 
		{
			{1,0,0},
			{1,1,1},
			{0,0,0}
		};
	

	public static int[][] shape1 = 
		{
			{0,1,1},
			{0,1,0},
			{0,1,0}
		};
	

	public static int[][] shape2 = 
		{
			{0,0,0},
			{1,1,1},
			{0,0,1}
		};
	

	public static int[][] shape3 = 
		{
			{0,1,0},
			{0,1,0},
			{1,1,0}
		};

	public int[][] getShape(int rotation) {
		int[][] shape = new int[3][3];
		
		switch (rotation) {
			case 0:
				shape = ShapeJ.shape0;
				break;
			case 1:
				shape = ShapeJ.shape1;
				break;
			case 2:
				shape = ShapeJ.shape2;
				break;
			case 3:
				shape = ShapeJ.shape3;
				break;
			default:
				throw new IllegalArgumentException();
		}
		
		return shape;
	}

}
