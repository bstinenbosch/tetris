package main.java;

public class ShapeT implements IShape {

    private static int[][] shape0 =
            {
                    {0, 6, 0},
                    {6, 6, 6},
                    {0, 0, 0}
            };


    private static int[][] shape1 =
            {
                    {0, 6, 0},
                    {0, 6, 6},
                    {0, 6, 0}
            };


    private static int[][] shape2 =
            {
                    {0, 0, 0},
                    {6, 6, 6},
                    {0, 6, 0}
            };


    private static int[][] shape3 =
            {
                    {0, 6, 0},
                    {6, 6, 0},
                    {0, 6, 0}
            };

    public int[][] getShape(int rotation) {
        int[][] shape;

        switch (rotation) {
            case 0:
                shape = ShapeT.shape0;
                break;
            case 1:
                shape = ShapeT.shape1;
                break;
            case 2:
                shape = ShapeT.shape2;
                break;
            case 3:
                shape = ShapeT.shape3;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return shape;
    }
}
