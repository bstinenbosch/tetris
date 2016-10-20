package tetris;

import java.util.Collection;

import javafx.scene.input.KeyCode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyBindingsTest {
    KeyBindings kb = new KeyBindings();

    @Test
    public void testElementAcessors() {
        Collection<Action> bindings = kb.values();
        Action str = (Action) bindings.toArray()[0];
        kb.put(str, KeyCode.ACCEPT);
        assertEquals(kb.getAction(KeyCode.ACCEPT), str);
        assertEquals(kb.getKeyCode(str), KeyCode.ACCEPT);
    }

    @Test
    public void sizeTest() {
        KeyBindings bindings = new KeyBindings();
        assertEquals(6, bindings.size());
    }

    @Test
    public void equalsSameMemorieTest() {
        KeyBindings bindings = new KeyBindings();
        KeyBindings bindings2 = bindings;
        assertEquals(true, bindings.equals(bindings2));
    }

    @Test
    public void equalsDifferentMemorieTest() {
        KeyBindings bindings = new KeyBindings();
        KeyBindings bindings2 = new KeyBindings();
        assertEquals(true, bindings.equals(bindings2));
    }

    @Test
    public void equalsNotEqualTest() {
        KeyBindings bindings = new KeyBindings();
        KeyBindings bindings2 = new KeyBindings();
        bindings2.put(Action.HARD_DROP, KeyCode.T);
        assertEquals(false, bindings.equals(bindings2));
    }
}
