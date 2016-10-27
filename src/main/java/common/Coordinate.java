package common;

public class Coordinate {

    private int x;
    private int y;

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

    /**
     * @param nQuadrants
     */
    public void rotateClockwise(int nQuadrants) {
        // rotating 5 quadrants should be the same as
        // rotating 1 quadrant.
        nQuadrants = nQuadrants % 4;

        // rotating 1 quadrant clockwise should be the same
        // as rotating 3 quadrants counterclockwise.
        rotateCounterClockwise(4 - nQuadrants);
    }

    /**
     * this.x = x * cos(theta) - y * sin(theta);
     * this.y = x * sin(theta) + y * cos(theta);
     *
     * @param nQuadrants
     */
    public void rotateCounterClockwise(int nQuadrants) {
        // rotating 5 quadrants should be the same as
        // rotating 1 quadrant.
        nQuadrants = nQuadrants % 4;

        int xNew = this.x;
        int yNew = this.y;

        switch(nQuadrants) {
            case 1: // rotate 90 degrees
                xNew = -this.y;
                yNew = this.x;
                break;
            case 2: // rotate 180 degrees
                xNew = -this.x;
                yNew = -this.y;
                break;
            case 3: // rotate 270 degrees
                xNew = this.y;
                yNew = -this.x;
                break;
            default: // rotate 0 or 360 degrees (i.e do nothing)
                break;
        }

        this.x = xNew;
        this.y = yNew;
    }

    public boolean equals(Object other) {
        if(!(other instanceof Coordinate)) {
            return false;
        }

        Coordinate toCompare = (Coordinate) other;

        return toCompare.getX() == x && toCompare.getY() == y;
    }
}
