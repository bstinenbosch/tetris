package tetris;

import tetris.shapes.original.TetrominoType;

public class TetrominoQueue {

    private TetrominoType next;
    private TetrominoType hold;

    public TetrominoQueue() {
        this.next = TetrominoType.random();
    }

    public TetrominoType pop() {
        TetrominoType popped = this.next;
        this.next = TetrominoType.random();
        return popped;
    }

    public TetrominoType peek() {
        return this.next;
    }

    public TetrominoType hold(TetrominoType toHold) {
        TetrominoType held = this.hold;
        this.hold = toHold;
        return held;
    }

}
