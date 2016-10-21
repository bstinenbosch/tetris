package Scenes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import tetris.Settings;
import tetris.scenes.GameScreen;

import javafx.embed.swing.JFXPanel;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameScreenTest {

    // This is to help the Toolkit not initialized error, to get the javaFX test
    // to work
    @BeforeClass
    public static void initToolkit() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });

        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    @Test
    public void testLeftPaneWidth() {
        Settings settings = new Settings();
        GameScreen gamescreen = new GameScreen(settings);
        assertTrue(gamescreen.getLeftPaneWidth() == 200);
    }

    @Test
    public void testGameScreen() {
        Settings settings = new Settings();
        GameScreen gamescreen = new GameScreen(settings);
        assertTrue(gamescreen.getLeftPaneHeight() == 400);
    }

    // @Test
    // public void

}
