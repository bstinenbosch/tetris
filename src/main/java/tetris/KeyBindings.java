package tetris;

import java.util.Collection;
import java.util.HashMap;

import javafx.scene.input.KeyCode;

public final class KeyBindings {
    private HashMap<KeyCode, String> bindings = new HashMap<KeyCode, String>();

    /**
     * Keybindings provide a fast-access way of evaluating keys to their mapped
     * meanings, while it is also possible to adjust the mapping at runtime.
     */
    public KeyBindings() {
        bindings.put(KeyCode.LEFT, "MOVE LEFT");
        bindings.put(KeyCode.RIGHT, "MOVE RIGHT");
        bindings.put(KeyCode.A, "ROTATE LEFT");
        bindings.put(KeyCode.S, "ROTATE RIGHT");
        bindings.put(KeyCode.DOWN, "SOFT DROP");
        bindings.put(KeyCode.SPACE, "HARD DROP");
    }

    public Collection<String> values() {
        return bindings.values();
    }

    /**
     * put a new keybinding in place, removing possible conflicting old
     * bindings.
     * 
     * @param key
     * @param binding
     */
    public void put(String key, KeyCode binding) {
        KeyCode oldBinding = getBinding(key);
        bindings.remove(oldBinding);
        bindings.put(binding, key);
    }

    public String getKey(KeyCode binding) {
        return bindings.getOrDefault(binding, "no key assigned");
    }

    public KeyCode getBinding(String key) {
        for (KeyCode binding : bindings.keySet()) {
            if (key.equals(bindings.get(binding))) {
                return binding;
            }
        }
        throw new IllegalArgumentException("You requested the binding of a key that is not bound.");
    }
}
