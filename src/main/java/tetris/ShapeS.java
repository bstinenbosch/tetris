package main.java.tetris;

public class ShapeS implements IShape {

    private static int[][] shape0 =
            {
                    {0, 5, 5},
                    {5, 5, 0},
                    {0, 0, 0}
            };


    private static int[][] shape1 =
            {
                    {0, 5, 0},
                    {0, 5, 5},
                    {0, 0, 5}
            };


    private static int[][] shape2 =
            {
                    {0, 0, 0},
                    {0, 5, 5},
                    {5, 5, 0}
            };


    private static int[][] shape3 =
            {
                    {5, 0, 0},
                    {5, 5, 0},
                    {0, 5, 0}
            };

    public int[][] getShape(int rotation) {
        int[][] shape;

        switch (rotation) {
            case 0:
                shape = ShapeS.shape0;
                break;
            case 1:
                shape = ShapeS.shape1;
                break;
            case 2:
                shape = ShapeS.shape2;
                break;
            case 3:
                shape = ShapeS.shape3;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return shape;
    }

}
