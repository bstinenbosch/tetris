package tetris.shapes.original;

import tetris.shapes.ShapeType;

import java.util.Random;

/**
 * Defines a set of tetromino shapes, all consisting of four tetrominos.
 * This set can be consired as the original set of pieces from Tetris.
 */
public enum TetrominoType implements ShapeType {

    I, J, L, O, S, T, Z;

    private static final TetrominoType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static TetrominoType random() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

}
