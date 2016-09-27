package tetris;

import javafx.collections.ObservableList;
import logging.Logger;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class ScreenController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<>();

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public Boolean setScreen(String name) {
        Node screen = getScreen(name);
        if (screen == null) {
            Logger.log(this, Logger.LogType.ERROR, "Screen not loaded.");
            return false;
        }

        ObservableList<Node> children = getChildren();

        if (!children.isEmpty()) {
            children.remove(0);
        }

        children.add(screen);
        return true;
    }

}
