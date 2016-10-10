package highscore;

public class GameEntry {

    private String name; // name of the player
    private int score; // score of the player

    public GameEntry(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public GameEntry(int score) {
        this.score = score;
        this.name = "";
    }

    /**
     * Set the name of the game entry.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the name of the game entry.
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the score of the game entry.
     * 
     * @return
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
}
