package highscore;

public interface IScoreBoard {

    GameEntry[] getScores();

    /**
     * Add game entry to scoreboard.
     *
     * @param entry
     *            game entry
     */
    void add(GameEntry entry);

    boolean isHighscore(int score);

    /**
     * Returns an ScoreBoard object as a string.
     *
     * @return scoreboard object as single-line string
     */
    String toString();

}