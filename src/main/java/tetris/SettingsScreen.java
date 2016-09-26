package tetris;

import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SettingsScreen extends VBox {

    public SettingsScreen() {
        getChildren().addAll(new Button("Test1"), new Button("Test2"), new Button("Test3"));
    }

}
