package tetris;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class MainScreen extends TilePane {

    private Button startNewGameButton;
    private Button settingsButton;

    public MainScreen() {
        Label titleLabel = new Label("TETRIS");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        startNewGameButton = new Button("Start new game");
        startNewGameButton.setStyle("-fx-background-color: red");

        settingsButton = new Button("Settings");
        settingsButton.setStyle("-fx-background-color: green");

        setOrientation(Orientation.VERTICAL);
        setTileAlignment(Pos.CENTER);
        setPadding(new Insets(0, 20, 10, 20));
        getChildren().addAll(titleLabel, startNewGameButton, settingsButton);
        setStyle("-fx-background-color: black");
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getStartNewGameButton() {
        return startNewGameButton;
    }

}
