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
    }

    /**
     * increases the score by add.
     * 
     * @param rowsCleared
     *            add add points to the score
     */
    public void add(int rowsCleared) {
        score += rowsCleared * pointsPerRow[rowsCleared];
        level += rowsCleared;
        setChanged();
        notifyObservers(score);
    }

    /**
     * @return the current level that has been reached.
     */
    public int getLevel() {
        return level / 10;
    }
}