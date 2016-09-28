package tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Settings {
    private static final int BLOCK_SIZE = 20;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CORNER = 3;
    private KeyBindings keybindings;
    private Color[] colors;
    private GraphicsContext board;

    public Settings() {
        keybindings = new KeyBindings();
        colors = new Color[7];
    }

    public int boardWidth() {
        return BOARD_WIDTH;
    }

    public int boardHeight() {
        return BOARD_HEIGHT;
    }

    public int blockSize() {
        return BLOCK_SIZE;
    }

    public int corner() {
        return CORNER;
    }

    public KeyBindings getKeyBindings() {
        return keybindings;
    }

    public void setBoard(GraphicsContext gc) {
        board = gc;
    }

    public GraphicsContext getBoard() {
        return board;
    }
}