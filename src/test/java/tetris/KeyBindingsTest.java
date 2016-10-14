package tetris;

import java.util.Collection;

import javafx.scene.input.KeyCode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class KeyBindingsTest {

    @Test
    public void get_binding_by_key() {
        KeyBindings keyBindings = new KeyBindings();

        assertThat("the correct key was returned for the binding",
                keyBindings.getKey("ROTATE LEFT"),
                equalTo("A"));
    }

    @Test
    public void get_key_by_binding() {
        KeyBindings keyBindings = new KeyBindings();

        assertThat("the correct binding was returned for the key",
                keyBindings.getBinding("A"),
                equalTo("ROTATE LEFT"));
    }

//    @Test
//    public void test() {
//        KeyBindings kb = new KeyBindings();
//        Collection<String> bindings = kb.values();
//        String str = (String) bindings.toArray()[0];
//        kb.put(str, KeyCode.getKeyCode(KeyCode.ACCEPT.toString()).toString());
//        assertEquals(kb.getKey(KeyCode.getKeyCode(KeyCode.ACCEPT.toString()).toString()), str);
//        assertEquals(kb.getKey(str), KeyCode.ACCEPT);
//    }
}
