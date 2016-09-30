package tetris.tetromino;

import java.util.ArrayDeque;

public class TetrominoQueue {
    /**
     * Array of TetrominoType
     */
    private static ArrayDeque<TetrominoType> TetrominoQ = new ArrayDeque<>();

    public static ArrayDeque<TetrominoType> getList() {
        return TetrominoQ;
    }

    /**
     * Makes sure the TetrominoQueue is alwas filled with 2 Tetromino
     */
    public static void addToQueue() {
        while (TetrominoQ.size() < 2) {
            TetrominoQ.add(TetrominoType.random());
        }

    }

    /**
     * Get the first Tetromino from the Queue to put in the GameGrid
     * 
     * @return
     */

    public static TetrominoType removeFromQueue() {
        return TetrominoQ.remove();
    }

    /**
     * Look at the last Tetromino in the Queue to visualise it in de previewPane
     * 
     * @return
     */

    public static TetrominoType getLast() {
        return TetrominoQ.getLast();
    }

}
