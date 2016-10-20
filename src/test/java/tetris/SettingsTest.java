package tetris;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SettingsTest {

    @Test
    public void boardWidthTest() {
        Settings settings = new Settings("src/test/resources/settings.xml");
        assertEquals(10, settings.boardWidth());
    }

    @Test
    public void boardHeightTest() {
        Settings settings = new Settings("src/test/resources/settings.xml");
        assertEquals(20, settings.boardHeight());
    }

    @Test
    public void blockSizeTest() {
        Settings settings = new Settings("src/test/resources/settings.xml");
        assertEquals(20, settings.blockSize());
    }

    @Test
    public void cornerTest() {
        Settings settings = new Settings("src/test/resources/settings.xml");
        assertEquals(3, settings.corner());
    }

    @Test
    public void getKeyBindings() {
        Settings settings = new Settings("src/test/resources/settings.xml");
        KeyBindings keyBindings = new KeyBindings();
        assertEquals((KeyBindings) keyBindings, (KeyBindings) settings.getKeyBindings());
    }

}
