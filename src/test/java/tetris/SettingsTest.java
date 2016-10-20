package tetris;

import java.io.File;

import javafx.scene.paint.Color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void getKeyBindingsTest() {
        Settings settings = new Settings("src/test/resources/settings.xml");
        KeyBindings keyBindings = new KeyBindings();
        assertTrue(settings.getKeyBindings().equals(keyBindings));
    }

    @Test
    public void ChangeColorTest() {
        Settings settings = new Settings();
        settings.setColor(1, Color.BLACK);
        assertEquals(Color.BLACK, settings.getColor(1));
    }

    @Test
    public void SaveSettingsTest() {
        Settings settings = new Settings("TestSettings.xml");
        File file = new File("TestSettings.xml");
        try {
            file.delete();
        } finally {
        }
        settings.setColor(1, Color.BLACK);
        settings.saveSettings();
        Settings settings2 = new Settings("TestSettings.xml");
        assertEquals(Color.BLACK, settings2.getColor(1));
        try {
            file.delete();
        } finally {
        }
    }

}
