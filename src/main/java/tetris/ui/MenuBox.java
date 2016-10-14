package tetris.ui;

import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class MenuBox extends GridPane {

    static MenuSub menuSub;

    /**
     * Creates a MenuBox object
     *
     * @param menuSub
     */
    public MenuBox(MenuSub menuSub) {
        MenuBox.menuSub = menuSub;

        Rectangle bg = createBackground();

        getChildren().addAll(bg, menuSub);
    }

    /**
     * Set content of MenuBox
     *
     * @param menuSub
     */
    public void setContent(MenuSub menuSub) {
        getChildren().remove(MenuBox.menuSub);
        MenuBox.menuSub = menuSub;
        getChildren().add(MenuBox.menuSub);
    }

    /**
     * Creates background of a MenuBox
     *
     * @return
     */
    private Rectangle createBackground() {
        return new Rectangle(700, 400);
    }


}
