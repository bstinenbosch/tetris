package tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSettingsPanel extends Application {

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
        Scene scene = new Scene(new VBox(10));
        VBox box = (VBox) scene.getRoot();
        box.setStyle("-fx-background-color: black");
        box.setPadding(new Insets(20, 20, 20, 20));

        for (int i = 0; i < colorPickers.length; i++) {
            ColorPicker colorPicker = colorPickers[i];
            Label label = new Label(String.format("Color %1$d", i + 1));
            label.setTextFill(Color.WHITE);
            box.getChildren().addAll(label, colorPicker);
        }

        backButton = new Button("Back to main menu");
        hookBackButtonEvent(backButton);

        box.getChildren().add(backButton);

        stage.setScene(scene);
        stage.show();
    }

    public void initializeColorPickers() {
        this.colorPickers[0] = new ColorPicker(Color.CYAN);
        this.colorPickers[1] = new ColorPicker(Color.BLUE);
        this.colorPickers[2] = new ColorPicker(Color.DARKORANGE);
        this.colorPickers[3] = new ColorPicker(Color.YELLOW);
        this.colorPickers[4] = new ColorPicker(Color.LIME);
        this.colorPickers[5] = new ColorPicker(Color.PURPLE);
        this.colorPickers[6] = new ColorPicker(Color.RED);
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
