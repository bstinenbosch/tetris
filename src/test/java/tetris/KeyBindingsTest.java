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
}
