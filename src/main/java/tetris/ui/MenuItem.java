package tetris.ui;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuItem extends StackPane {

    public static final int FONT_SIZE = 24;

    /**
     * Creates a (layered) MenuItem
     *
     * @param name
     */
    public MenuItem(String name) {
        Text text = createContent(name);

        Rectangle bg = createBackground(text);

        getChildren().addAll(bg, text);
    }

    /**
     * Creates the inner content of a menu item
     *
     * @param name
     * @return
     */
    private Text createContent(String name) {
        Text text = new Text(name);
        text.setFont(new Font("System", FONT_SIZE));
        return text;
    }

    /**
     * Creates the background of a menu item
     *
     * @param text
     * @return
     */
    private Rectangle createBackground(Text text) {
        Rectangle bg = new Rectangle(text.getLayoutBounds().getWidth(), FONT_SIZE);
        bg.setFill(Color.WHITE);
        return bg;
    }
}
