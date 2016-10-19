package tetris;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.input.KeyCode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KeyBindingsTest {
    KeyBindings keyBindings = new KeyBindings();

    @Test
    public void test_default_key_for_moving_a_tetromino_to_the_left() {
        assertThat("the left arrow key is bound to 'moving left'-action by default",
                keyBindings.getAction(KeyCode.LEFT),
                equalTo("MOVE_LEFT"));
    }

    @Test
    public void test_default_key_for_moving_a_tetromino_to_the_right() {
        assertThat("the right arrow key is bound to 'moving right'-action by default",
                keyBindings.getAction(KeyCode.RIGHT),
                equalTo("MOVE_RIGHT"));
    }

    @Test
    public void test_default_key_for_rotating_a_tetromino_clockwise() {
        assertThat("the S key is bound to 'rotating clockwise'-action by default",
                keyBindings.getAction(KeyCode.S),
                equalTo("ROTATE_RIGHT"));
    }

    @Test
    public void test_default_key_for_rotating_a_tetromino_counter_clockwise() {
        assertThat("the A key is bound to 'rotating counterclockwise'-action by default",
                keyBindings.getAction(KeyCode.A),
                equalTo("ROTATE_LEFT"));
    }

    @Test
    public void test_default_key_for_soft_dropping_a_tetromino() {
        assertThat("the down arrow key is bound to 'soft drop'-action by default",
                keyBindings.getAction(KeyCode.DOWN),
                equalTo("SOFT_DROP"));
    }

    @Test
    public void test_default_key_for_hard_dropping_a_tetromino() {
        assertThat("the spacebar key is bound to 'hard drop'-action by default",
                keyBindings.getAction(KeyCode.SPACE),
                equalTo("HARD_DROP"));
    }

    @Test
    public void test_set_action_for_key_without_binding() {
        keyBindings.put("SOFT_DROP", KeyCode.CONTROL);
        assertThat("the A key is now bound to 'move left'-action",
                keyBindings.getAction(KeyCode.CONTROL),
                equalTo("SOFT_DROP"));
    }

    @Test
    public void test_override_key_with_existing_binding() {
        keyBindings.put("MOVE_LEFT", KeyCode.A);
        assertThat("the A key is now bound to 'move left'-action",
                keyBindings.getAction(KeyCode.A).equals("MOVE_LEFT"),
                equalTo(true));
    }

    @Test
    public void test_get_key_from_action() {
        keyBindings.put("ROTATE_LEFT", KeyCode.SHIFT);
        assertThat("the correct key is returned for this action",
                keyBindings.getKeyCode("ROTATE_LEFT"),
                equalTo(KeyCode.SHIFT));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_iteration_through_default_key_bindings() {
        keyBindings.getKeyCode("MOVE_UP");
    }

    @Test
    public void test_retrieval_of_key_without_binding() {
        for (Object keyBinding : keyBindings) {
            Map.Entry e = (Map.Entry) keyBinding;
            assertTrue(
                    (e.getValue().equals("MOVE_LEFT") && e.getKey().equals(KeyCode.LEFT)) ||
                            (e.getValue().equals("MOVE_RIGHT") && e.getKey().equals(KeyCode.RIGHT)) ||
                            (e.getValue().equals("ROTATE_LEFT") && e.getKey().equals(KeyCode.A)) ||
                            (e.getValue().equals("ROTATE_RIGHT") && e.getKey().equals(KeyCode.S)) ||
                            (e.getValue().equals("SOFT_DROP") && e.getKey().equals(KeyCode.DOWN)) ||
                            (e.getValue().equals("HARD_DROP") && e.getKey().equals(KeyCode.SPACE)));
        }
    }

    @Test
    public void test_element_accessors() {
        Collection<String> bindings = keyBindings.values();
        String str = (String) bindings.toArray()[0];
        keyBindings.put(str, KeyCode.ACCEPT);
        assertEquals(keyBindings.getAction(KeyCode.ACCEPT), str);
        assertEquals(keyBindings.getKeyCode(str), KeyCode.ACCEPT);
    }
}
