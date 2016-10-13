package highscore;

public class ScoreBoard {

    private GameEntry[] board = new GameEntry[10];
    private int numEntries = 0;

    public ScoreBoard() {
        loadScores();
    }

    private void loadScores() {
        // laad scores uit het xml bestand
    }

    public void SaveScores() {
        // save scores naar een xml bestand
    }

    public GameEntry[] getScores() {
        return board;
    }

    public void add(GameEntry entry) {
        int newScore = entry.getScore();
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length)
                numEntries++;

            int i = numEntries - 1;
            while (i > 0 && board[i - 1].getScore() < newScore) {
                board[i] = board[i - 1];
                i--;
            }
            board[i] = entry;
        }
    }

    public boolean isHighscore(int score) {
        if (numEntries < board.length || score > board[numEntries - 1].getScore())
            return true;
        return false;
    }

    public String toString() {
        String result = "{";
        for (int i = 0; i < numEntries; i++) {
            if (i > 0)
                result += ", ";
            result += board[i].toString();
        }
        result += "}";
        return result;
    }
}
