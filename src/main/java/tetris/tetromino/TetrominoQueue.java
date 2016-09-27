package tetris.tetromino;

import java.util.ArrayDeque;

public class TetrominoQueue {
    private static ArrayDeque<TetrominoType> TetrominoQ = new ArrayDeque<>();

    public static ArrayDeque<TetrominoType> getList() {
        return TetrominoQ;
    }

    public static void addToQueue() {
        if (TetrominoQ.isEmpty()) {
            TetrominoQ.add(TetrominoType.random());
            TetrominoQ.add(TetrominoType.random());
        } else {
            TetrominoQ.add(TetrominoType.random());
        }

    }

    public static TetrominoType removeFromQueue() {
        TetrominoType onScreen = TetrominoQ.getFirst();
        TetrominoQ.remove();
        return onScreen;
    }

    public static TetrominoType getLast() {
        TetrominoType previewTetromino = TetrominoQ.getLast();
        return previewTetromino;
    }

}
