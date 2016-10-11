package tetris;

import java.util.Collection;

import javafx.scene.input.KeyCode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyBindingsTest {
    @Test
    public void test() {
        KeyBindings kb = new KeyBindings();
        Collection<String> bindings = kb.values();
        String str = (String) bindings.toArray()[0];
        kb.put(str, KeyCode.ACCEPT);
        assertEquals(kb.getKey(KeyCode.ACCEPT), str);
        assertEquals(kb.getBinding(str), KeyCode.ACCEPT);
    }
}
