package tetris.ui;

import javafx.scene.layout.VBox;

public class MenuSub extends VBox {

    /**
     * Creates a wrapper for MenuItem objects
     *
     * @param menuItems
     */
    public MenuSub(MenuItem...menuItems) {
        setSpacing(20);
        for(MenuItem item : menuItems){
            getChildren().addAll(item);
        }
    }
}
