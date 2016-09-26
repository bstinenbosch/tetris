package tetris.tetromino;

import java.util.LinkedList;

public class TetrominoQueue {
    public static LinkedList list;

    // Create Queue
    public void Queue() {
        list = new LinkedList();
    }

    // Add Random tetromino to queue
    public static void enqueue(TetrominoType type) {
        list.add(type);
    }

    // remove top tetromino from queue
    public static TetrominoType dequeue(int queuePlace) {
        TetrominoType blokje = (TetrominoType) list.get(queuePlace);
        list.remove(0);
        return blokje;
    }

    public static LinkedList giveList() {
        return list;
    }

}
