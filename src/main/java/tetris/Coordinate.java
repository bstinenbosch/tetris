package tetris;

public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void translateX(int dx) {
        this.x += dx;
    }

    public void translateY(int dy) {
        this.y += dy;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
