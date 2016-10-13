package tetris.scenes;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tetris.Controller;

public class PromptNameScreen extends Group implements IScreen {

    private Button submit;
    private final TextField playerName;

    public PromptNameScreen() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        playerName = new TextField();
        playerName.setPromptText("Enter your name");
        playerName.setPrefColumnCount(10);
        playerName.getText();
        GridPane.setConstraints(playerName, 0, 0);
        grid.getChildren().add(playerName);

        submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        getChildren().add(grid);
    }

    @Override
    public void hookEvents(Controller controller) {
        submit.setOnAction(event -> controller.registerHighScore(playerName.getCharacters()));
    }

}
