package tetris;

import tetris.scenes.GameScreen;
import tetris.scenes.HighscoreScreen;
import tetris.scenes.MainScreen;
import tetris.scenes.PromptNameScreen;
import tetris.scenes.RoboScreen;
import tetris.scenes.SettingsScreen;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public void gotoGameScreen() {
        GameScreen gameview = new GameScreen(settings);
        primaryStage.setScene(new Scene(gameview));
        gameview.hookEvents(controller);
        gameview.requestFocus();
    }

    /**
     * gotoGameScreen inits and shows the settings screen, hooks the key events
     * and fires up the game controller.
     */
    public void gotoSettingsScreen() {
        SettingsScreen settingsview = new SettingsScreen(settings);
        settingsview.hookEvents(controller);
        primaryStage.setScene(new Scene(settingsview));
    }

    /**
     * gotoGameScreen inits and shows the main screen, hooks the key events and
     * fires up the game controller.
     */
    public void gotoMainScreen() {
        MainScreen mainview = new MainScreen();
        mainview.hookEvents(controller);
        primaryStage.setScene(new Scene(mainview));
    }

    public void gotoPromptNameScreen() {
        PromptNameScreen nameScreen = new PromptNameScreen();
        nameScreen.hookEvents(controller);
        primaryStage.setScene(new Scene(nameScreen));
    }

    public void gotoHighscoreScreen() {
        HighscoreScreen highscoreView = new HighscoreScreen();
        highscoreView.hookEvents(controller);
        ObservableList<GameEntry> gameEntryObservableList = FXCollections
            .observableArrayList(controller.getScoreBoard().getScores());
        highscoreView.setHighscoreData(gameEntryObservableList);
        primaryStage.setScene(new Scene(highscoreView));
    }

    public void gotoRoboScreen() {
        RoboScreen roboview = new RoboScreen(settings);
        roboview.hookEvents(controller);
        primaryStage.setScene(new Scene(roboview));
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
}
