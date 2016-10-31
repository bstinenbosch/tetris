package tetris.sound;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.media.AudioClip;

public class SoundManager implements IPlayable, ILoadable {
    private static final double DEFAULT_VOLUME = 0; // max volume
    private static final int MAX_NUMBER_OF_THREADS = 2;

    private double volume;

    private ExecutorService soundService;
    private Map<String, AudioClip> sounds = new HashMap<>();

    /**
     * Creates a SoundManager running on a given number of Threads. The number
     * of thread can not be smaller than 0 or larger than
     * {@value package.class#MAX_NUMBER_OF_THREADS}
     *
     * @param numberOfThreads
     *            number of threads to run on
     */
    public SoundManager(int numberOfThreads) {
        if (numberOfThreads > 0 && numberOfThreads <= MAX_NUMBER_OF_THREADS) {
            this.soundService = Executors.newFixedThreadPool(numberOfThreads);
            this.volume = DEFAULT_VOLUME;
        }
    }

    /**
     * Load a sound into the sound manager to play whenever wanted. Whenever a
     * sound is loaded with an identifier that already exists the loaded sound
     * will be replaced with the new sound.
     *
     * @param id
     *            identifier of sound clip.
     * @param url
     *            url of sound clip to load.
     */
    @Override
    public void load(String id, URL url) {
        if (url == null) {
            throw new IllegalArgumentException("URL does not point to a audio clip");
        }

        load(id, new AudioClip(url.toExternalForm()));
    }

    /**
     * Load a sound into the sound manager to play whenever wanted. Whenever a
     * sound is loaded with an identifier that already exists the loaded sound
     * will be replaced with the new sound.
     *
     * @param id
     *            identifier of sound clip.
     * @param clip
     *            sound clip to load.
     */
    @Override
    public void load(String id, AudioClip clip) {
        sounds.put(id, clip);
    }

    /**
     * Plays a sound loaded into the sound manager without looping.
     *
     * @param id
     *            identifier of sound clip used to load into sound manager.
     */
    @Override
    public void play(final String id) {
        play(id, false);
    }

    /**
     * Play a sound loaded into the sound manager with option to loop the sound
     * until stopped.
     *
     * @param id
     *            identifier of sound clip.
     * @param loop
     *            whether the sound clip should be looped until stopped.
     */
    @Override
    public void play(final String id, boolean loop) {
        AudioClip clip = getAudioClip(id);

        // enqueue clip to play
        Runnable soundPlay = () -> {
            if (loop) {
                clip.setCycleCount(AudioClip.INDEFINITE);
            }
            clip.setVolume(volume);
            clip.play();
        };
        soundService.execute(soundPlay);
    }

    /**
     * Returns the sound clip corresponding to the given identifier that was
     * loaded into SoundManager.
     *
     * @param id
     *            identifier of the sound clip.
     * @return AudioClip corresponding to given identifier.
     */
    private AudioClip getAudioClip(String id) {
        AudioClip clip = sounds.get(id);

        if (clip == null) {
            throw new IllegalArgumentException("Clip identifier does not point to a loaded sound.");
        }

        return clip;
    }

    /**
     * Stop all sounds loaded into sound manager.
     */
    public void stopAll() {
        sounds.keySet().forEach(this::stop);
    }

    /**
     * Stop the sound that was loaded and is playing corresponding to the given
     * identifier.
     */
    @Override
    public void stop(String id) {
        getAudioClip(id).stop();
    }

    /**
     * Set the volume for all loaded sound clips.
     *
     * @param intensity
     *            volume intensity from 0.0 (muted) to 1.0 (full volume)
     */
    @Override
    public void setVolume(double intensity) {
        if (intensity < 0.0 || intensity > 1.0) {
            throw new IllegalArgumentException("Volume intensity must be between 0.0 and 1.0.");
        }

        this.volume = intensity;
    }

    /**
     * Shutdown all threads used by sound manager. This will automatically stop
     * all playing sounds.
     */
    public void shutdown() {
        soundService.shutdown();
    }

}