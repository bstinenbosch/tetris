package tetris.scenes;

import java.util.Observable;
import java.util.Observer;

import tetris.Score;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ObservingLabel extends Label implements Observer {

    public ObservingLabel(String string) {
        super(string);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof Score) {
            Platform.runLater(() -> this.setText(Integer.toString((int) arg)));
        }
    }

}
