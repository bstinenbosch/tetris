package tetris;

import tetris.shapes.AbstractShape;
import tetris.shapes.IFactory;

public class TetrominoQueue {

    private final IFactory factory;
    private AbstractShape next;
    private AbstractShape hold;

    public TetrominoQueue(IFactory factory) {
        this.factory = factory;
        this.next = factory.createRandom();
    }

    public AbstractShape pop() {
        AbstractShape popped = this.next;
        this.next = factory.createRandom();
        return popped;
    }

    public AbstractShape peek() {
        return this.next;
    }

    public AbstractShape hold(AbstractShape toHold) {
        AbstractShape held = this.hold;
        this.hold = toHold;
        return held;
    }

}
