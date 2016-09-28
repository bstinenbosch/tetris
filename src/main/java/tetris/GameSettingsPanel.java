package tetris;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameSettingsPanel {

    public static final int NUMBER_OF_COLORS = 7;
    private Scene previousScene;
    private Stage primaryStage;

    private Button backButton;
    private Settings settings;

    private ColorPicker[] colorPickers = new ColorPicker[7];

    public GameSettingsPanel(Settings settings) {
        this.settings = settings;
        initializeColorPickers();

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 100);

        HBox box = new HBox(20);
        box.setPadding(new Insets(5, 5, 5, 5));
        for (ColorPicker colorPicker : this.colorPickers) {
            box.getChildren().add(colorPicker);
        }
        backButton = new Button("Back");
        hookBackButtonEvent(backButton);
        box.getChildren().add(backButton);

        GridPane keysetter = new GridPane();
        ComboBox<String> actionCombobox = new ComboBox<String>();
        TextField keyResultTextField = new TextField();
        keyResultTextField.setEditable(false);
        actionCombobox.getItems().addAll(settings.getKeyBindings().values());
        keyResultTextField.setOnKeyPressed(
            event -> keyResultTextField.setText(((KeyEvent) event).getCode().toString()));

        GridPane.setConstraints(box, 0, 0);
        GridPane.setConstraints(keysetter, 0, 1);
        GridPane.setConstraints(actionCombobox, 0, 0);
        GridPane.setConstraints(keyResultTextField, 1, 0);
        keysetter.getChildren().addAll(actionCombobox, keyResultTextField);
        root.getChildren().addAll(box, keysetter);
    }

    public void initializeColorPickers() {
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            this.colorPickers[i] = new ColorPicker();
        }
    }

    public ColorPicker[] getColorPickers() {
        return this.colorPickers;
    }

    private void hookBackButtonEvent(Button backButton) {
        // backButton.setOnAction(event -> this.close());
    }

}
