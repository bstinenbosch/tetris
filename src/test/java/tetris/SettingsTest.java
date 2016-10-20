package tetris;

import org.junit.Test;

public class SettingsTest {

    @Test
    public void testSaveAndLoadSettings() {
        Settings settings = new Settings();
        settings.saveSettings();
    }

    @Test
    public void testSaveNewPath() {
        Settings settings = new Settings("src/main/resources/settingsfail.xml");
        Settings settings2 = new Settings("src/main/resources/settings.xml");
    }

}
