package tetris;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @param action
     *            the key that is replaced
     * @param keyCode
     *            the new keycode that points to the key
     */
    public void put(String action, KeyCode keyCode) {
        if (!bindings.containsKey(keyCode)) {
            KeyCode oldBinding = getKeyCode(action);
            bindings.remove(oldBinding);
        }
        bindings.put(keyCode, action);
    }

    /**
     * Returns the keycode for a given binding event.
     *
     * @param keyCode
     * @return
     */
    public String getAction(KeyCode keyCode) {
        return bindings.getOrDefault(keyCode, "no key assigned");
    }

    /**
     * get the keycode that is associated with key.
     * 
     * @param action
     *            the key to search for in the associations
     * @return the associated keycode.
     */
    public KeyCode getKeyCode(String action) {
        for (KeyCode keycode : bindings.keySet()) {
            if (action.equals(bindings.get(keycode))) {
                return keycode;
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

    /**
     * courtesy of
     * http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java.
     *
     * @return a value-sorted version of bindings.
     */
    public Set<Entry<KeyCode, String>> getSortedEntrySet() {
        return bindings.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(/* Collections.reverseOrder() */)).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
            .entrySet();
    }
}
