package highscore;

public class GameEntry implements Comparable<GameEntry> {

    private String name; // name of the player
    private int score; // score of the player

    public GameEntry(String name, int score) {
        this.score = score;
        this.name = name;
    }

    /**
     * Return the name of the game entry.
     * 
     * @return name of game entry
     */
    public String getName() {
        return name;
    }

    /**
     * Return the score of the game entry.
     * 
     * @return score of game entry
     */
    public int getScore() {
        return score;
    }

    /**
     * Return the game entry as a readable String.
     */
    public String toString() {
        return ("(" + name + ", " + score + ")");
    }

    @Override
    public int compareTo(GameEntry other) {
        return getScore() - other.getScore();
    }
}
