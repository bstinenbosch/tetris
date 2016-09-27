package tetris;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingsScreen extends VBox {

    private Button backToMainScreenButton;

    private ColorPicker colorPicker;

    public SettingsScreen() {
        setPadding(new Insets(5,5,5,5));

        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);

        backToMainScreenButton = new Button("Back");

        getChildren().addAll(colorPicker, backToMainScreenButton);
        setStyle("-fx-background-color: black");
    }

    public Button getBackToMainScreenButton() {
        return backToMainScreenButton;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

}
