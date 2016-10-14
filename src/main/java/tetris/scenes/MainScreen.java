package tetris.scenes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.Controller;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tetris.ui.MenuBox;
import tetris.ui.MenuItem;
import tetris.ui.MenuSub;

public class MainScreen extends Group implements IScreen {

    private Button startNewGameButton;
    private Button settingsButton;
    private Button highscoreButton;

    /**
     * the main screen is the central entry point for the game and the root of
     * the navigation tree.
     */
    public MainScreen() {
        Rectangle bg = new Rectangle(700, 400);
        bg.setFill(Color.BLACK);

        MenuItem newGame = new MenuItem("START NEW GAME");
        MenuItem settings = new MenuItem("SETTINGS");
        MenuItem highscores = new MenuItem("HIGHSCORES");
        MenuItem exit = new MenuItem("EXIT");
        MenuSub mainMenuSub = new MenuSub(
                newGame,
                settings,
                highscores,
                exit
        );

        MenuItem test1 = new MenuItem("test 1");
        MenuItem test2 = new MenuItem("test 2");
        MenuSub mainMenuSub2 = new MenuSub(
                test1,
                test2
        );

        MenuBox menuBox = new MenuBox(mainMenuSub);

        newGame.setOnMouseClicked(event -> menuBox.setContent(mainMenuSub2));

        Label titleLabel = new Label("TETRIS");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        startNewGameButton = new Button("Start new game");
        startNewGameButton.setStyle("-fx-background-color: red");

        settingsButton = new Button("Settings");
        settingsButton.setStyle("-fx-background-color: green");

        highscoreButton = new Button("Highscores");
        highscoreButton.setStyle("-fx-background-color: blue");

//        TilePane root = new TilePane();
//        root.setOrientation(Orientation.VERTICAL);
//        root.setTileAlignment(Pos.CENTER);
//        root.setPadding(new Insets(0, 20, 10, 20));
//        root.getChildren().addAll(titleLabel, startNewGameButton, settingsButton, highscoreButton);
//        root.setStyle("-fx-background-color: black");
//
//        getChildren().add(root);

        getChildren().addAll(bg, titleLabel, menuBox);
    }

    @Override
    public void hookEvents(Controller controller) {
        startNewGameButton.setOnAction(event -> controller.startGame());
        settingsButton.setOnAction(event -> controller.openSettings());
        highscoreButton.setOnAction(event -> controller.viewHighscores());
    }
}
