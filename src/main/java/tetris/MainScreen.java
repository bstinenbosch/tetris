package tetris;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class MainScreen extends TilePane {

    private Button settingsButton;

    private Button startNewGameButton;

    public MainScreen() {
        Label titleLabel = new Label("TETRIS");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        Button startNewGameButton = new Button("Start new game");
        startNewGameButton.setStyle("-fx-background-color: red");
//        controller.hookLauncherEvents(startNewGameButton);

        settingsButton = new Button("Settings");
        startNewGameButton.setStyle("-fx-background-color: green");
//        hookSettingsEvents();

//        gameSettingsPanel.initializeColorPickers();

        setOrientation(Orientation.VERTICAL);
        setTileAlignment(Pos.CENTER);
        setPadding(new Insets(0, 20, 10, 20));
//        getChildren().addAll(titleLabel, startNewGameButton, settingsButton);
        getChildren().addAll(titleLabel, startNewGameButton);
        setStyle("-fx-background-color: black");
    }

    private Button getSettingsButton() {
        return this.settingsButton;
    }

    private Button getStartNewGameButton() {
        return this.startNewGameButton;
    }

}
