package tetris;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class ScreenController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<String, Node>();

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public Boolean setScreen(String name) {
        Node screen = getScreen(name);
        if(screen != null) {
            getChildren().add(screen);
            return true;
        }
        return false;
    }

}
