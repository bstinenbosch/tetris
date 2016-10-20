package tetris.sound;

import javafx.scene.media.AudioClip;

import java.net.URL;

public interface ILoadable {

    /**
     * Loads an AudioClip linked to an identifier in order
     * to be able to run the loaded AudioClip later by
     * just passing through the identifier.
     *
     * @param id   identifier of AudioClip.
     * @param clip AudioClip to load.
     */
    void load(String id, AudioClip clip);

    /**
     * Loads the resource where to url points to and
     * links it to an identifier to be able to run
     * the loaded resource later by just passing
     * through the identifier.
     *
     * @param id  identifier of AudioClip.
     * @param url AudioClip to load.
     */
    void load(String id, URL url);
}
