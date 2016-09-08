package main.java;

public class ShapeI implements IShape {

    private static int[][] shape0 =
            {
                    {0, 1, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            };


    private static int[][] shape1 =
            {
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0}
            };


    private static int[][] shape2 =
            {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0}
            };


    private static int[][] shape3 =
            {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            };

    public int[][] getShape(int rotation) {
        int[][] shape;

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
