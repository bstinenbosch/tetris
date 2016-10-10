package tetris.scenes;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ObservingLabel extends Label implements Observer {

    public ObservingLabel(String string) {
        super(string);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> this.setText(Integer.toString((int) arg)));
    }

}
