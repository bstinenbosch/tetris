package main.java;

public class ShapeO implements IShape {

    private static int[][] shape =
            {
                    {4, 4},
                    {4, 4}
            };


    public int[][] getShape(int rotation) {
        return shape;
    }

}
