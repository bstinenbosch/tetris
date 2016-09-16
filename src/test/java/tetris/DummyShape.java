package tetris;

public class DummyShape extends AbstractShape {

    private static final int[] x = new int[] {-1, 0, 0, 1};
    private static final int[] y = new int[] {0, 0, 1, 1};

    public DummyShape(int X, int Y) {
        super(X, Y, x, y);
    }
}
