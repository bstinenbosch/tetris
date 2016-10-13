package tetris.scenes;

import highscore.GameEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import tetris.Controller;

public class HighscoreScreen extends Group implements IScreen {
    private Button backButton;

    private final ObservableList<HighScoreEntry> highScores =
            FXCollections.observableArrayList(
                    new HighScoreEntry("1", "Bas", "1200"),
                    new HighScoreEntry("2", "Sebastiaan", "800"),
                    new HighScoreEntry("3", "Robert", "1500"),
                    new HighScoreEntry("4", "Karel", "400"),
                    new HighScoreEntry("5", "Pascal", "112000"));

    public HighscoreScreen() {
        Label titleLabel = new Label("HIGHSCORES");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");

        backButton = new Button("Back to main menu");

        Pane highScoreTable = GetHighScoreTable();

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        BorderPane.setAlignment(backButton, Pos.CENTER);
        root.setBottom(backButton);

        BorderPane.setMargin(highScoreTable, new Insets(12,12,12,12));
        BorderPane.setAlignment(highScoreTable, Pos.CENTER);
        root.setCenter(highScoreTable);

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setStyle("-fx-background-color: black");
        getChildren().add(root);
    }

    public VBox GetHighScoreTable() {
        TableView table = new TableView();

        TableColumn rankCol = new TableColumn("Rank");
        rankCol.setPrefWidth(50);
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);

        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setPrefWidth(100);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(rankCol, nameCol, scoreCol);

        table.setItems(highScores);

        final VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        return vbox;
    }

    @Override
    public void hookEvents(Controller controller) {
        backButton.setOnAction(event -> controller.openMainScreen());
    }

    // Dummy class to show how an object is link to the highscore table
    public static class HighScoreEntry {

        private final SimpleStringProperty rank;
        private final SimpleStringProperty name;
        private final SimpleStringProperty score;

        private HighScoreEntry(String rank, String name, String score) {
            this.rank = new SimpleStringProperty(rank);
            this.name = new SimpleStringProperty(name);
            this.score = new SimpleStringProperty(score);
        }

        // Getter is needed for the table to read
        public String getRank() {
            return rank.get();
        }

        // Getter is needed for the table to read
        public String getName() {
            return name.get();
        }

        // Getter is needed for the table to read
        public String getScore() {
            return score.get();
        }
    }
}
