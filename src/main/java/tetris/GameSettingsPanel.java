package tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameSettingsPanel extends Application {

    public static final int NUMBER_OF_COLORS = 7;
    private Scene previousScene;
    private Stage primaryStage;

    private Button backButton;

    private ColorPicker[] colorPickers = new ColorPicker[7];

    @Override
    public void start(Stage stage) {
        open(stage);
    }

    public void open(Stage stage) {
        this.previousScene = stage.getScene();
        this.primaryStage = stage;

        initializeColorPickers();

        stage.setTitle("ColorPicker");
        Scene scene = new Scene(new HBox(20), 400, 100);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5,5,5,5));

        for(ColorPicker colorPicker : this.colorPickers) {
            box.getChildren().add(colorPicker   );
        }

        backButton = new Button("Back");
        hookBackButtonEvent(backButton);

        box.getChildren().add(backButton);

        stage.setScene(scene);
        stage.show();
    }

    public void initializeColorPickers() {
        for(int i = 0; i < NUMBER_OF_COLORS; i++) {
            this.colorPickers[i] = new ColorPicker();
        }
    }

    public ColorPicker[] getColorPickers() {
        return this.colorPickers;
    }

    private void hookBackButtonEvent(Button backButton) {
        backButton.setOnAction(event -> this.close());
    }

    public void close() {
        this.primaryStage.setScene(this.previousScene);
        this.primaryStage.show();
    }
}
