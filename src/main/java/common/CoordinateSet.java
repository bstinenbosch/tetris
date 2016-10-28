package common;

import java.util.List;

public class CoordinateSet {

    private List<Coordinate> coordinates;

    public CoordinateSet(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Coordinate> getCoordinates() {
        return this.coordinates;
    }

    public void guardAgainstEmptySet() {
        if (coordinates.isEmpty()) {
            throw new IllegalArgumentException("Can not create an empty set of coordinates!");
        }
    }

    public Coordinate getLeftmost() {
        guardAgainstEmptySet();

        Coordinate candidate = coordinates.get(0);

        for (Coordinate coordinate : coordinates) {
            if (coordinate.getX() < candidate.getX()) {
                candidate = coordinate;
            }
        }

        return candidate;
    }

    public Coordinate getRightmost() {
        guardAgainstEmptySet();

        Coordinate candidate = coordinates.get(0);

        for (Coordinate coordinate : coordinates) {
            if (coordinate.getX() > candidate.getX()) {
                candidate = coordinate;
            }
        }

        return candidate;
    }

    public Coordinate getTopmost() {
        guardAgainstEmptySet();

        Coordinate candidate = coordinates.get(0);

        for (Coordinate coordinate : coordinates) {
            if (coordinate.getY() > candidate.getY()) {
                candidate = coordinate;
            }
        }

        return candidate;
    }

    public Coordinate getBottommost() {
        guardAgainstEmptySet();

        Coordinate candidate = coordinates.get(0);

        for (Coordinate coordinate : coordinates) {
            if (coordinate.getY() < candidate.getY()) {
                candidate = coordinate;
            }
        }

        return candidate;
    }

    public void translateX(int dx) {
        for (Coordinate coordinate : coordinates) {
            coordinate.translateX(dx);
        }
    }

    public void translateY(int dy) {
        for (Coordinate coordinate : coordinates) {
            coordinate.translateY(dy);
        }
    }

    public void rotateClockwise(int nQuadrants) {
        for (Coordinate coordinate : coordinates) {
            coordinate.rotateClockwise(nQuadrants);
        }
    }

    public void rotateCounterClockwise(int nQuadrants) {
        for (Coordinate coordinate : coordinates) {
            coordinate.rotateCounterClockwise(nQuadrants);
        }
    }

    public boolean contains(Coordinate otherCoordinate) {
        for (Coordinate coordinate : coordinates) {
            if(otherCoordinate.equals(coordinate)) {
                return true;
            }
        }

        return false;
    }

    public boolean equals(Object other) {
        if (!(other instanceof CoordinateSet)) {
            return false;
        }

        CoordinateSet otherSet = (CoordinateSet) other;

        List<Coordinate> otherCoordinates = otherSet.getCoordinates();
        for (int i = 0; i < otherCoordinates.size(); i++) {
            if (!otherCoordinates.get(i).equals(this.coordinates.get(i))) {
                return false;
            }
        }

        return true;
    }
}
