package tetris;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public final class KeyBindings {
    private HashMap<String, String> bindings = new HashMap<String, String>();

    /**
     * Keybindings provide a fast-access way of evaluating keys to their mapped
     * meanings, while it is also possible to adjust the mapping at runtime.
     */
    public KeyBindings() {
        resetBindings();
    }

    public KeyBindings(String left, String right, String rLeft, String rRight, String sDrop,
        String hDrop) {
        bindings = new HashMap<String, String>();
        bindings.put(left, "MOVE LEFT");
        bindings.put(right, "MOVE RIGHT");
        bindings.put(rLeft, "ROTATE LEFT");
        bindings.put(rRight, "ROTATE RIGHT");
        bindings.put(sDrop, "SOFT DROP");
        bindings.put(hDrop, "HARD DROP");
    }

    public void resetBindings() {
        bindings = new HashMap<String, String>();
        bindings.put("LEFT", "MOVE LEFT");
        bindings.put("RIGHT", "MOVE RIGHT");
        bindings.put("A", "ROTATE LEFT");
        bindings.put("S", "ROTATE RIGHT");
        bindings.put("DOWN", "SOFT DROP");
        bindings.put("SPACE", "HARD DROP");
    }

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
    public void put(String key, String binding) {
        String oldBinding = getBinding(key);
        bindings.remove(oldBinding);
        bindings.put(binding, key);
    }

    public String getKey(String binding) {
        return bindings.getOrDefault(binding, "no key assigned");
    }

    public Set<String> getKeys() {
        return bindings.keySet();
    }

    /**
     * get the keycode that is associated with key.
     * 
     * @param key
     *            the key to search for in the associations
     * @return the associated keycode.
     */
    public String getBinding(String key) {
        for (String binding : bindings.keySet()) {
            if (key.equals(bindings.get(binding))) {
                return binding;
            }
        }
        throw new IllegalArgumentException("You requested the binding of a key that is not bound.");
    }
}
