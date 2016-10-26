package tetris.shapes.original;

import java.util.Random;

public enum TetrominoType {

    I, J, L, O, S, T, Z;

    private static final TetrominoType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static TetrominoType random() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

}
