package tetris.tetromino;

import java.util.ArrayDeque;

public class TetrominoQueue {
    /**
     * Array of TetrominoType.
     */
    private static ArrayDeque<TetrominoType> TetrominoQ = new ArrayDeque<>();
    private static ArrayDeque<TetrominoType> TetrominoHold = new ArrayDeque<>();

    public static ArrayDeque<TetrominoType> getList() {
        return TetrominoQ;
    }

    /**
     * Makes sure the TetrominoQueue is alwas filled with 2 Tetromino.
     */
    public static void addToQueue() {
        while (TetrominoQ.size() < 2) {
            TetrominoQ.add(TetrominoType.random());
        }

    }

    /**
     * Get the first Tetromino from the Queue to put in the GameGrid.
     * 
     * @return the removed tetromino
     */

    public static TetrominoType removeFromQueue() {
        return TetrominoQ.remove();
    }

    /**
     * Look at the last Tetromino in the Queue to visualise it in de
     * previewPane.
     * 
     * @return the last tetromino in the queue
     */

    public static TetrominoType getLast() {
        return TetrominoQ.getLast();
    }

    public static TetrominoType holdTetromino() {
        // POP Tetromino out of the Grid
        TetrominoQ.pop();
        // add another TetrominoBlock to the normal Queue
        addToQueue();
        // restart gamescreen

        return TetrominoHold.getFirst();
    }

}
