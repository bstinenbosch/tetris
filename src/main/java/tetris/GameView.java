package tetris;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameView extends Group {
    private Settings settings;
    private Button exitButton;
    private Button restartButton;

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
        rootGameScreen.setOnKeyPressed(event -> controller.handleKeyEvent(event));
        return rootGameScreen;
    }

    private GridPane setUpRightPaneGameScreen() {
        exitButton = new Button("exit");
        restartButton = new Button("restart");

        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);
        GridPane.setConstraints(exitButton, 0, 0);
        GridPane.setConstraints(restartButton, 0, 1);
        rightPane.getChildren().addAll(exitButton, restartButton);
        rightPane.setStyle("-fx-background-color: grey");
        return rightPane;
    }

    public void hookEvents(Controller controller) {
        exitButton.setOnAction(event -> controller.stop());
        restartButton.setOnAction(event -> controller.restartGame());
    }

}
