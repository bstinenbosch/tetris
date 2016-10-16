package tetris;

import java.util.Collection;

import javafx.scene.input.KeyCode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyBindingsTest {
    KeyBindings kb = new KeyBindings();

    @Test
    public void testElementAcessors() {
        Collection<String> bindings = kb.values();
        String str = (String) bindings.toArray()[0];
        kb.put(str, KeyCode.ACCEPT);
        assertEquals(kb.getAction(KeyCode.ACCEPT), str);
        assertEquals(kb.getKeyCode(str), KeyCode.ACCEPT);
    }
}
