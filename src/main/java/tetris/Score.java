package tetris;

import java.util.Observable;

public class Score extends Observable {
    private int score = 0;
    private int level = 0;
    private int[] pointsPerRow = { 0, 40, 50, 100, 300 };

    /**
     * resets the score to 0.
     */
    public void reset() {
        score = 0;
        level = 0;
        setChanged();
        notifyObservers(score);
    }

    /**
     * increases the score by add.
     * 
     * @param rowsCleared
     *            add add points to the score
     */
    public void add(int rowsCleared) {
        score += rowsCleared * pointsPerRow[rowsCleared];
        notifyObservers(score);
        setChanged();

    }

    public void addLevel(int rowsCleared) {
        level += rowsCleared;
        notifyObservers(level);
        setChanged();
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return (int) Math.floor(level / 10);
    }
}