package tetris.scenes;

import tetris.Controller;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class MainScreen extends Group implements IScreen {

    private Button startNewGameButton;
    private Button settingsButton;
    private Button highscoreButton;
    private Button exitButton;
    private Button roboButton;

    /**
     * the main screen is the central entry point for the game and the root of
     * the navigation tree.
     */
    public MainScreen() {
        Label titleLabel = new Label("TETRIS");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        startNewGameButton = new Button("Start new game");
        startNewGameButton.setStyle("-fx-background-color: red;-fx-text-fill:white");

        settingsButton = new Button("Settings");
        settingsButton.setStyle("-fx-background-color: green;-fx-text-fill:white");

        highscoreButton = new Button("Highscores");
        highscoreButton.setStyle("-fx-background-color: blue;-fx-text-fill:white");

        exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: orange;-fx-text-fill:white");

        roboButton = new Button("Robo mode");
        roboButton.setStyle("-fx-background-color: blue;-fx-text-fill:white");

        TilePane root = new TilePane();
        root.setOrientation(Orientation.VERTICAL);
        root.setTileAlignment(Pos.CENTER);
        root.setPadding(new Insets(0, 20, 10, 20));
        root.getChildren().addAll(titleLabel, startNewGameButton, settingsButton, highscoreButton,
            exitButton, roboButton);
        root.setStyle("-fx-background-color: black");
        getChildren().add(root);
    }

    @Override
    public void hookEvents(Controller controller) {
        startNewGameButton.setOnAction(event -> controller.startGame());
        settingsButton.setOnAction(event -> controller.openSettings());
        highscoreButton.setOnAction(event -> controller.viewHighscores());
        exitButton.setOnAction(event -> controller.stop());
        roboButton.setOnAction(event -> controller.startRoboMode());
    }
}
