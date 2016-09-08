package tetris;

public class ShapeJ implements IShape {

    private static int[][] shape0 =
            {
                    {2, 0, 0},
                    {2, 2, 2},
                    {0, 0, 0}
            };


    private static int[][] shape1 =
            {
                    {0, 2, 2},
                    {0, 2, 0},
                    {0, 2, 0}
            };


    private static int[][] shape2 =
            {
                    {0, 0, 0},
                    {2, 2, 2},
                    {0, 0, 2}
            };


    private static int[][] shape3 =
            {
                    {0, 2, 0},
                    {0, 2, 0},
                    {2, 2, 0}
            };

    public int[][] getShape(int rotation) {
        int[][] shape;

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
