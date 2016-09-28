package tetris;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class MainScreen extends Group {

    private Button startNewGameButton;
    private Button settingsButton;

    public MainScreen() {
        Label titleLabel = new Label("TETRIS");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        startNewGameButton = new Button("Start new game");
        startNewGameButton.setStyle("-fx-background-color: red");

        settingsButton = new Button("Settings");
        settingsButton.setStyle("-fx-background-color: green");

        TilePane root = new TilePane();
        root.setOrientation(Orientation.VERTICAL);
        root.setTileAlignment(Pos.CENTER);
        root.setPadding(new Insets(0, 20, 10, 20));
        root.getChildren().addAll(titleLabel, startNewGameButton, settingsButton);
        root.setStyle("-fx-background-color: black");
        getChildren().add(root);
    }

    public void hookEvents(Controller controller) {
        startNewGameButton.setOnAction(event -> controller.startGame());
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getStartNewGameButton() {
        return startNewGameButton;
    }

}
