package tetris;

import java.util.Random;

enum ShapeType {

    I, J, L, O, S, T, Z;

    private static final ShapeType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static ShapeType random() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
