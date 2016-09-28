package tetris;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameView extends Group {
    private Settings settings;
    private Button exitButton;
    private Button restartButton;

    private class ObservingLabel extends Label implements Observer {

        public ObservingLabel(String string) {
            super(string);
        }

        @Override
        public void update(Observable o, Object arg) {
            Platform.runLater(() -> this.setText(Integer.toString((int) arg)));
        }

    }

    private ObservingLabel scoreLabel;

    public GameView(Settings settings) {
        super();
        this.settings = settings;
        GridPane rightPane = setUpRightPaneGameScreen();
        Pane leftPane = setUpLeftPaneGameScreen();
        GridPane rootGameScreen = setUpRootPaneGameScreen(leftPane, rightPane);
        getChildren().add(rootGameScreen);
    }

    private Pane setUpLeftPaneGameScreen() {
        int boardWidthPixel = settings.blockSize() * settings.boardWidth();
        int boardHeightPixel = settings.blockSize() * settings.boardHeight();
        Canvas canvas = new Canvas(boardWidthPixel, boardHeightPixel);
        settings.setBoard(canvas.getGraphicsContext2D());
        Pane leftPane = new Pane();
        leftPane.getChildren().add(canvas);
        leftPane.setStyle("-fx-background-color: grey");
        return leftPane;
    }

    private GridPane setUpRootPaneGameScreen(Pane leftPane, GridPane rightPane) {
        GridPane rootGameScreen = new GridPane();
        rootGameScreen.setHgap(10);
        GridPane.setConstraints(leftPane, 0, 0);
        GridPane.setConstraints(rightPane, 1, 0);
        rootGameScreen.getChildren().addAll(leftPane, rightPane);
        return rootGameScreen;
    }

    private GridPane setUpRightPaneGameScreen() {
        exitButton = new Button("exit");
        restartButton = new Button("restart");
        scoreLabel = new ObservingLabel("0");
        scoreLabel
            .setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-text-alignment:center;"
                + "-fx-alignment:center;-fx-font-weight:bold;-fx-font-size:250%");
        scoreLabel.setMinSize(100, 50);

        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);
        GridPane.setConstraints(exitButton, 0, 0);
        GridPane.setConstraints(restartButton, 0, 1);
        GridPane.setConstraints(scoreLabel, 0, 2);
        rightPane.getChildren().addAll(exitButton, restartButton, scoreLabel);
        rightPane.setStyle("-fx-background-color: grey");
        return rightPane;
    }

    public void hookEvents(Controller controller) {
        exitButton.setOnAction(event -> controller.stop());
        restartButton.setOnAction(event -> controller.restartGame());
        controller.addScoreObserver(scoreLabel);
    }

}
