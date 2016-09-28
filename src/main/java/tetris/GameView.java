package tetris;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameView extends Group {
    public GameView() {

        Pane leftPane = setUpLeftPaneGameScreen();
        GridPane rightPane = setUpRightPaneGameScreen();
        GridPane rootGameScreen = setUpRootPaneGameScreen(leftPane, rightPane);
    }

    private Pane setUpLeftPaneGameScreen() {
        Canvas canvas = new Canvas(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
        board = canvas.getGraphicsContext2D();
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
        Button exitButton = new Button("exit");
        Button restartButton = new Button("restart");
        hookGameScreenEvents(exitButton, restartButton);

        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);
        GridPane.setConstraints(exitButton, 0, 0);
        GridPane.setConstraints(restartButton, 0, 1);
        rightPane.getChildren().addAll(exitButton, restartButton);
        rightPane.setStyle("-fx-background-color: grey");
        return rightPane;
    }

    private void hookGameScreenEvents(Button exitButton, Button restartButton) {
        exitButton.setOnAction(event -> controller.stop());
        restartButton.setOnAction(event -> controller.restartGame());
    }

}
