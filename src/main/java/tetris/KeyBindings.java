package tetris;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javafx.scene.input.KeyCode;

public final class KeyBindings implements Iterable<Entry<KeyCode, String>> {
    private HashMap<KeyCode, String> bindings = new HashMap<>();

    /**
     * Keybindings provide a fast-access way of evaluating keys to their mapped
     * meanings, while it is also possible to adjust the mapping at runtime.
     */
    public KeyBindings() {
        bindings.put(KeyCode.LEFT, "MOVE_LEFT");
        bindings.put(KeyCode.RIGHT, "MOVE_RIGHT");
        bindings.put(KeyCode.A, "ROTATE_LEFT");
        bindings.put(KeyCode.S, "ROTATE_RIGHT");
        bindings.put(KeyCode.DOWN, "SOFT_DROP");
        bindings.put(KeyCode.SPACE, "HARD_DROP");
    }

    /**
     * Return names of all bindings
     *
     * @return
     */
    public Collection<String> values() {
        return bindings.values();
    }

    /**
     * put a new keybinding in place, removing possible conflicting old
     * bindings.
     * 
     * @param key
     *            the key that is replaced
     * @param binding
     *            the new keycode that points to the key
     */
    public void put(String key, KeyCode binding) {
        KeyCode oldBinding = getBinding(key);
        bindings.remove(oldBinding);
        bindings.put(binding, key);
    }

    /**
     * Returns the keycode for a given binding event
     *
     * @param binding
     * @return
     */
    public String getKey(KeyCode binding) {
        return bindings.getOrDefault(binding, "no key assigned");
    }

    /**
     * get the keycode that is associated with key.
     * 
     * @param key
     *            the key to search for in the associations
     * @return the associated keycode.
     */
    public KeyCode getBinding(String key) {
        for (KeyCode binding : bindings.keySet()) {
            if (key.equals(bindings.get(binding))) {
                return binding;
            }
        }
        throw new IllegalArgumentException("You requested the binding of a key that is not bound.");
    }

    /**
     * Returns all key bindings as an iterable set
     * for the convenience of listing every key binding
     *
     * @return
     */
    @Override
    public Iterator<Entry<KeyCode, String>> iterator() {
        return bindings.entrySet().iterator();
    }
}
