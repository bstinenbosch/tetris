package tetris;

import java.util.Collection;
import java.util.HashMap;

import javafx.scene.input.KeyCode;

public final class KeyBindings {
    private HashMap<KeyCode, String> bindings = new HashMap<KeyCode, String>();

    public KeyBindings() {
        bindings.put(KeyCode.LEFT, "LEFT");
        bindings.put(KeyCode.RIGHT, "RIGHT");
    }

    public Collection<String> values() {
        return bindings.values();
    }

    public void put(String key, KeyCode binding) {
        if (bindings.containsValue(key)) {
            bindings.put(binding, key);
        } else {
            throw new IllegalArgumentException(
                "The key [" + key + "] is not bindable in this game.");
        }
    }

    public String get(KeyCode binding) {
        return bindings.get(binding);
    }
}
