package tetris.shapes.custom;

import tetris.shapes.ShapeType;

import java.util.Random;

public enum TrominoType implements ShapeType {

    I, L, J;

    private static final TrominoType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static TrominoType random() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

}
