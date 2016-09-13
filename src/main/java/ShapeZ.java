public class ShapeZ implements IShape {

    private static int[][] shape0 =
            {
                    {7, 7, 0},
                    {0, 7, 7},
                    {0, 0, 0}
            };


    private static int[][] shape1 =
            {
                    {0, 0, 7},
                    {0, 7, 7},
                    {0, 7, 0}
            };


    private static int[][] shape2 =
            {
                    {0, 0, 0},
                    {7, 7, 0},
                    {0, 7, 7}
            };


    private static int[][] shape3 =
            {
                    {0, 7, 0},
                    {7, 7, 0},
                    {7, 0, 0}
            };

    public int[][] getShape(int rotation) {
        int[][] shape;

        switch (rotation) {
            case 0:
                shape = ShapeZ.shape0;
                break;
            case 1:
                shape = ShapeZ.shape1;
                break;
            case 2:
                shape = ShapeZ.shape2;
                break;
            case 3:
                shape = ShapeZ.shape3;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return shape;
    }

}
