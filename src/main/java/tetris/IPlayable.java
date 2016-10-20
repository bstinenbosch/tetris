package tetris;

public interface IPlayable {
    void play(String id);
    void play(String id, boolean loop);
    void stop();
}
