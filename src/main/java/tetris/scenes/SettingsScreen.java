package tetris.scenes;

import tetris.Controller;
import tetris.Settings;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
        VBox root = new VBox(20);
        root.setPadding(new Insets(5, 5, 5, 5));
        backButton = new Button("Back");
        root.getChildren().addAll(colorPickers);
        root.getChildren().add(backButton);
        root.getChildren().add(generateKeySetter(settings));
        getChildren().add(root);
    }

    private GridPane generateKeySetter(Settings settings) {
        ComboBox<String> actionCombobox = new ComboBox<String>();
        TextField keyResultTextField = new TextField();
        keyResultTextField.setEditable(false);
        actionCombobox.getItems().addAll(settings.getKeyBindings().values());
        actionCombobox.setOnAction(event -> keyResultTextField
            .setText(settings.getKeyBindings().getBinding(actionCombobox.getValue()).toString()));
        keyResultTextField.setOnKeyPressed(
            event -> keyResultTextFieldOnKeyPressed(keyResultTextField, actionCombobox, event));

        GridPane.setConstraints(actionCombobox, 0, 0);
        GridPane.setConstraints(keyResultTextField, 1, 0);
        GridPane keysetter = new GridPane();
        keysetter.getChildren().addAll(actionCombobox, keyResultTextField);
        return keysetter;
    }

    private void keyResultTextFieldOnKeyPressed(TextField keyResultTextField,
        ComboBox<String> actionCombobox, KeyEvent event) {
        KeyCode keycode = ((KeyEvent) event).getCode();
        settings.getKeyBindings().put(actionCombobox.getValue(), keycode);
        keyResultTextField.setText(keycode.toString());
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
        backButton.setOnAction(event -> controller.openMainScreen());
        colorPickers[0].setOnAction(event -> settings.setColor(1, colorPickers[0].getValue()));
        colorPickers[1].setOnAction(event -> settings.setColor(2, colorPickers[1].getValue()));
        colorPickers[2].setOnAction(event -> settings.setColor(3, colorPickers[2].getValue()));
        colorPickers[3].setOnAction(event -> settings.setColor(4, colorPickers[3].getValue()));
        colorPickers[4].setOnAction(event -> settings.setColor(5, colorPickers[4].getValue()));
        colorPickers[5].setOnAction(event -> settings.setColor(6, colorPickers[5].getValue()));
        colorPickers[6].setOnAction(event -> settings.setColor(7, colorPickers[6].getValue()));
    }

}
