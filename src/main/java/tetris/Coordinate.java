package tetris;

public class Coordinate {

    private int x;
    private int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    void translateX(int dx) {
        this.x += dx;
    }

    void translateY(int dy) {
        this.y += dy;
    }

    void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
