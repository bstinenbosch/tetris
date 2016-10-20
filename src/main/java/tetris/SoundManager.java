package tetris;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SoundManager implements IPlayable, ILoadable {
    private ExecutorService soundService;
    private Map<String, AudioClip> sounds = new HashMap<>();

    /**
     * Creates a SoundManager
     * @param numberOfThreads
     */
    public SoundManager(int numberOfThreads) {
        soundService = Executors.newFixedThreadPool(numberOfThreads);
    }

    public SoundManager() {
        soundService = Executors.newFixedThreadPool(4);
    }

    public void load(String id, URL url) {
        AudioClip sound = new AudioClip(url.toExternalForm());
        sounds.put(id, sound);
    }

    public void play(final String id) {
        Runnable soundPlay = () -> sounds.get(id).play();
        soundService.execute(soundPlay);
    }

    public void play(final String id, boolean loop) {
        Runnable soundPlay = () -> {
            AudioClip clip = sounds.get(id);
            if(loop) {
                clip.setCycleCount(AudioClip.INDEFINITE);
            }
            clip.play();
        };
        soundService.execute(soundPlay);
    }

    public void stop() {
        for(Map.Entry<String, AudioClip> sound : sounds.entrySet()) {
            sound.getValue().stop();
        }
    }

    public void shutdown() {
        soundService.shutdown();
    }

}