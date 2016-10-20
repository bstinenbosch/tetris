package tetris.sound;

public interface IPlayable {
    /**
     * Plays a resource corresponding to the given identifier.
     *
     * @param id identifier of resource to play.
     */
    void play(String id);

    /**
     * Plays a resource corresponding to the given identifier
     * with the option to loop to resource indefinitely.
     *
     * @param id identifier of resource to play.
     */
    void play(String id, boolean loop);

    /**
     * Stops a playing resource corresponding to the given identifier.
     *
     * @param id identifier of resource to stop playing.
     */
    void stop(String id);

    /**
     * Set volume of resources when played.
     *
     * @param intensity volume intensity when playing a resource.
     */
    void setVolume(double intensity);
}
