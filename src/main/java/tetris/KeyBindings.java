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

public final class KeyBindings implements Iterable<Entry<KeyCode, Action>> {
    private HashMap<KeyCode, Action> bindings = new HashMap<>();

    /**
     * Keybindings provide a fast-access way of evaluating keys to their mapped
     * meanings, while it is also possible to adjust the mapping at runtime.
     */
    public KeyBindings() {
        bindings.put(KeyCode.LEFT, Action.MOVE_LEFT);
        bindings.put(KeyCode.RIGHT, Action.MOVE_RIGHT);
        bindings.put(KeyCode.A, Action.ROTATE_LEFT);
        bindings.put(KeyCode.S, Action.ROTATE_RIGHT);
        bindings.put(KeyCode.DOWN, Action.SOFT_DROP);
        bindings.put(KeyCode.SPACE, Action.HARD_DROP);
        bindings.put(KeyCode.CONTROL, Action.HOLD_TETROMINO);

    }

    public Collection<Action> values() {
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
    public void put(Action action, KeyCode keyCode) {
        if (!bindings.containsKey(keyCode)) {
            KeyCode oldBinding = getKeyCode(action);
            bindings.remove(oldBinding);
            bindings.put(keyCode, action);
        }
    }

    public Action getAction(KeyCode keyCode) {
        return bindings.getOrDefault(keyCode, Action.INVALID_ACTION);
    }

    /**
     * get the keycode that is associated with key.
     * 
     * @param action
     *            the key to search for in the associations
     * @return the associated keycode.
     */
    public KeyCode getKeyCode(Action action) {
        for (KeyCode keycode : bindings.keySet()) {
            if (action.equals(bindings.get(keycode))) {
                return keycode;
            }
        }
        throw new IllegalArgumentException("You requested the binding of a key that is not bound.");
    }

    @Override
    public Iterator<Entry<KeyCode, Action>> iterator() {
        return bindings.entrySet().iterator();
    }

    /**
     * courtesy of
     * http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java.
     * 
     * @return a value-sorted version of bindings.
     */
    public Set<Entry<KeyCode, Action>> getSortedEntrySet() {
        return bindings.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(/* Collections.reverseOrder() */)).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
            .entrySet();
    }

    public int size() {
        return bindings.size();
    }

    public boolean equals(KeyBindings other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof KeyBindings)) {
            return false;
        }
        boolean result = true;
        for (Action action : bindings.values()) {
            result &= getKeyCode(action).equals(other.getKeyCode(action));
        }
        return result;
    }
}
