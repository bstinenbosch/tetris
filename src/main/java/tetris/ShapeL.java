package main.java.tetris;

public class ShapeL implements IShape {

    private static int[][] shape0 =
            {
                    {0, 0, 3},
                    {3, 3, 3},
                    {0, 0, 0}
            };


    private static int[][] shape1 =
            {
                    {0, 3, 0},
                    {0, 3, 0},
                    {0, 3, 3}
            };


    private static int[][] shape2 =
            {
                    {0, 0, 0},
                    {3, 3, 3},
                    {3, 0, 0}
            };


    private static int[][] shape3 =
            {
                    {3, 3, 0},
                    {0, 3, 0},
                    {0, 3, 0}
            };

    public int[][] getShape(int rotation) {
        int[][] shape;

        switch (rotation) {
            case 0:
                shape = ShapeL.shape0;
                break;
            case 1:
                shape = ShapeL.shape1;
                break;
            case 2:
                shape = ShapeL.shape2;
                break;
            case 3:
                shape = ShapeL.shape3;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return shape;
    }

}
