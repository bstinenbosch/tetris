package tetris.scenes;

import java.util.Map.Entry;

import tetris.Controller;
import tetris.Settings;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsScreen extends Group implements IScreen {

    public static final int NUMBER_OF_COLORS = 7;

    private Button backButton;
    private Settings settings;

    private ColorPicker[] colorPickers = new ColorPicker[7];

    /**
     * The settings screen is the place where the user can set every game-wide
     * editable setting.
     * 
     * @param settings
     *            the game-wide settings
     */
    public SettingsScreen(Settings settings) {
        this.settings = settings;
        initializeColorPickers();
        VBox left = new VBox(20);
        left.setPadding(new Insets(5, 5, 5, 5));
        backButton = new Button("Back");
        left.getChildren().addAll(colorPickers);
        left.getChildren().add(backButton);
        HBox root = new HBox(20);
        root.getChildren().addAll(left, generateKeySetter(settings));
        getChildren().add(root);
        root.setStyle("-fx-background-color: black");
    }

    /**
     * generate the table with keybindings.
     * 
     * @param settings
     *            the game-wide settings.
     * @return the table.
     */
    @SuppressWarnings("unchecked")
    private TableView<Entry<KeyCode, String>> generateKeySetter(Settings settings) {
        TableColumn<Entry<KeyCode, String>, String> actionColumn = new TableColumn<Entry<KeyCode, String>, String>(
            "action");
        actionColumn.setPrefWidth(100);
        actionColumn
            .setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getValue()));

        TableColumn<Entry<KeyCode, String>, String> keyCodeColumn = new TableColumn<Entry<KeyCode, String>, String>(
            "assigned key");
        keyCodeColumn.setCellValueFactory(
            entry -> new SimpleStringProperty(entry.getValue().getKey().getName()));
        keyCodeColumn.setPrefWidth(100);

        TableView<Entry<KeyCode, String>> keybindingsTable = new TableView<Entry<KeyCode, String>>();
        keybindingsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        keybindingsTable.getColumns().setAll(actionColumn, keyCodeColumn);

        keybindingsTable.setItems(
            FXCollections.observableArrayList(settings.getKeyBindings().getSortedEntrySet()));
        keybindingsTable.setOnKeyPressed(event -> {
            String action = keybindingsTable.getSelectionModel().getSelectedItem().getValue();
            KeyCode keycode = ((KeyEvent) event).getCode();
            settings.getKeyBindings().put(action, keycode);
            keybindingsTable.setItems(
                FXCollections.observableArrayList(settings.getKeyBindings().getSortedEntrySet()));
        });
        return keybindingsTable;
    }

    private void initializeColorPickers() {
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            this.colorPickers[i] = new ColorPicker();
            colorPickers[i].setValue(settings.getColor(i + 1));
        }
    }

    public ColorPicker[] getColorPickers() {
        return this.colorPickers;
    }

    @Override
    public void hookEvents(Controller controller) {
        backButton.setOnAction(event -> goBack(controller));
        colorPickers[0].setOnAction(event -> settings.setColor(1, colorPickers[0].getValue()));
        colorPickers[1].setOnAction(event -> settings.setColor(2, colorPickers[1].getValue()));
        colorPickers[2].setOnAction(event -> settings.setColor(3, colorPickers[2].getValue()));
        colorPickers[3].setOnAction(event -> settings.setColor(4, colorPickers[3].getValue()));
        colorPickers[4].setOnAction(event -> settings.setColor(5, colorPickers[4].getValue()));
        colorPickers[5].setOnAction(event -> settings.setColor(6, colorPickers[5].getValue()));
        colorPickers[6].setOnAction(event -> settings.setColor(7, colorPickers[6].getValue()));
    }

    private void goBack(Controller controller) {
        settings.saveSettings();
        controller.openMainScreen();
    }

}
