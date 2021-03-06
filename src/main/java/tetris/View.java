package tetris;

import tetris.scenes.GameScreen;
import tetris.scenes.HighscoreScreen;
import tetris.scenes.MainScreen;
import tetris.scenes.PromptNameScreen;
import tetris.scenes.SettingsScreen;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import highscore.GameEntry;

/**
 * MainScreen is the class containing all the GUI-related stuff. Here we draw up
 * the different screens and hook the key events.
 */
public class View extends Application {
    protected Settings settings;
    private Controller controller;
    private Stage primaryStage;
    private double initialX;
    private double initialY;

    /**
     * start inits the application and displays a loading screen
     * 
     * @param primaryStage
     *            is the root object containing the application.
     */
    @Override
    public void start(Stage primaryStage) {
        settings = new Settings("src/main/resources/settings.xml");
        controller = new Controller(this, settings);
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        gotoMainScreen();
        primaryStage.show();
    }

    /**
     * gotoGameScreen inits and shows the game screen, hooks the key events and
     * fires up the game controller.
     */
    public void gotoGameScreen(Tick tick) {
        GameScreen gameview = new GameScreen(settings);
        makeDraggable(gameview);
        primaryStage.setScene(new Scene(gameview));
        gameview.hookEvents(controller, tick);
        gameview.requestFocus();
    }

    /**
     * gotoGameScreen inits and shows the settings screen, hooks the key events
     * and fires up the game controller.
     */
    public void gotoSettingsScreen() {
        SettingsScreen settingsview = new SettingsScreen(settings);
        makeDraggable(settingsview);
        settingsview.hookEvents(controller);
        primaryStage.setScene(new Scene(settingsview));
    }

    /**
     * gotoGameScreen inits and shows the main screen, hooks the key events and
     * fires up the game controller.
     */
    public void gotoMainScreen() {
        MainScreen mainview = new MainScreen();
        makeDraggable(mainview);
        mainview.hookEvents(controller);
        primaryStage.setScene(new Scene(mainview));
    }

    /**
     * opens to dialog for asking the player's name.
     */
    public void gotoPromptNameScreen() {
        PromptNameScreen nameScreen = new PromptNameScreen();
        nameScreen.hookEvents(controller);
        primaryStage.setScene(new Scene(nameScreen));
    }

    /**
     * Opens the highscore table.
     */
    public void gotoHighscoreScreen() {
        HighscoreScreen highscoreView = new HighscoreScreen();
        makeDraggable(highscoreView);
        highscoreView.hookEvents(controller);
        ObservableList<GameEntry> gameEntryObservableList = FXCollections
            .observableArrayList(controller.getScoreBoard().getScores());
        highscoreView.setHighscoreData(gameEntryObservableList);
        primaryStage.setScene(new Scene(highscoreView));
    }

    public void resetFocus() {
        primaryStage.getScene().getRoot().requestFocus();
    }

    /**
     * stop is called when the application terminates. Here any asynchronous
     * threads should be terminated.
     */
    public void stop() {
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * courtesy of http://stackoverflow.com/a/12961943
     * 
     * @param group
     *            the group you want draggable
     */
    private void makeDraggable(Group group) {
        group.setOnMousePressed((mouseEvent) -> {
            initialX = mouseEvent.getSceneX();
            initialY = mouseEvent.getSceneY();
        });
        group.setOnMouseDragged((mouseEvent) -> {
            group.getScene().getWindow().setX(mouseEvent.getScreenX() - initialX);
            group.getScene().getWindow().setY(mouseEvent.getScreenY() - initialY);
        });
    }
}
