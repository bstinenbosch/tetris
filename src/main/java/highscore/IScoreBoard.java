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

    /**
     * return true if the highscore is deserving of a place on the leaderboard.
     * 
     * @param score
     *            the score to chekc against the leaderboard.
     * @return true or false.
     */
    boolean isHighscore(int score);

    /**
     * Returns an ScoreBoard object as a string.
     *
     * @return scoreboard object as single-line string
     */
    String toString();

}