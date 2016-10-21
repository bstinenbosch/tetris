package sound;

import javafx.scene.media.AudioClip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tetris.sound.SoundManager;

import java.net.URL;

public class SoundManagerTest {

    private URL clipPath;
    private SoundManager soundManager;

    @Before
    public void set_up() {
        this.soundManager = new SoundManager(2);
        this.clipPath = getClass().getClassLoader().getResource("sfx.wav");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_volume_intensity() {
        soundManager.setVolume(1.1);
    }

    @Test
    public void test_play_audio_clip_by_audio_clip_resource() {
        AudioClip audioClip = new AudioClip(clipPath.toExternalForm());
        soundManager.load("sfx1", audioClip);
        soundManager.play("sfx1");
    }

    @Test
    public void test_play_audio_clip_by_url() {
        soundManager.load("sfx1", clipPath);
        soundManager.play("sfx1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_play_audio_clip_by_unknown_id_failure() {
        soundManager.load("sfx1", clipPath);
        soundManager.play("sfx2");
    }

    @After
    public void tear_up() {
        soundManager.shutdown();
    }
}
