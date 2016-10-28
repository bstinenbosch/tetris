package tetris.scenes;

import tetris.Controller;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import highscore.GameEntry;

public class HighscoreScreen extends Group implements IScreen {

    private Button backButton;

    private TableView highscoreTable;

    /**
     * Creates a view for showing highscores.
     */
    public HighscoreScreen() {
        Label titleLabel = new Label("HIGHSCORES");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        backButton = new Button("Back to main menu");

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        BorderPane.setAlignment(backButton, Pos.CENTER);
        root.setBottom(backButton);

        Pane highScoreTable = getHighScoreTable();
        BorderPane.setMargin(highScoreTable, new Insets(12, 12, 12, 12));
        BorderPane.setAlignment(highScoreTable, Pos.CENTER);
        root.setCenter(highScoreTable);

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setStyle("-fx-background-color: black");
        getChildren().add(root);
    }

    /**
     * Creates highscore table component.
     *
     * @return highscore table
     */
    public VBox getHighScoreTable() {
        highscoreTable = new TableView();

        TableColumn rankCol = new TableColumn("Rank");
        rankCol.setPrefWidth(50);
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);

        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setPrefWidth(100);

        highscoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        highscoreTable.getColumns().addAll(rankCol, nameCol, scoreCol);

        final VBox vbox = new VBox();
        vbox.getChildren().addAll(highscoreTable);

        return vbox;
    }

    // @Override
    public void hookEvents(Controller controller) {
        backButton.setOnAction(event -> controller.openMainScreen());
    }

    public void setHighscoreData(ObservableList<GameEntry> gameEntries) {
        highscoreTable.setItems(gameEntries);
    }
}
