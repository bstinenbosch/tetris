package highscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreBoardTest {

    @Test
    public void toStringTest() {
        ScoreBoard board = new ScoreBoard();
        assertEquals("{}", board.toString());
    }

    @Test
    public void addEntryTest() {
        ScoreBoard board = new ScoreBoard();
        GameEntry entry = new GameEntry("Pascal", 500);
        board.add(entry);
        assertEquals("{(Pascal, 500)}", board.toString());
    }

    @Test
    public void addEntryHigherTest() {
        ScoreBoard board = new ScoreBoard();
        GameEntry entry = new GameEntry("Pascal", 500);
        board.add(entry);
        entry = new GameEntry("Bas", 700);
        board.add(entry);
        assertEquals("{(Bas, 700), (Pascal, 500)}", board.toString());
    }

    public void addEntryLowerTest() {
        ScoreBoard board = new ScoreBoard();
        GameEntry entry = new GameEntry("Pascal", 500);
        board.add(entry);
        entry = new GameEntry("Robbert", 300);
        board.add(entry);
        assertEquals("{(Pascal, 500), (Robbert, 300)}", board.toString());
    }

    public void addFullLowerTest() {
        ScoreBoard board = new ScoreBoard();
        board.add(new GameEntry("Player1", 1000));
        board.add(new GameEntry("Player2", 900));
        board.add(new GameEntry("Player3", 800));
        board.add(new GameEntry("Player4", 700));
        board.add(new GameEntry("Player5", 600));
        board.add(new GameEntry("Player6", 500));
        board.add(new GameEntry("Player7", 400));
        board.add(new GameEntry("Player8", 300));
        board.add(new GameEntry("Player9", 200));
        board.add(new GameEntry("Player10", 100));
        board.add(new GameEntry("Player11", 50));

        assertEquals(
            "{(Player1, 1000), (Player2, 900), (Player3, 800), (Player4, 700), (Player5, 600), (Player6, 500), (Player7, 400), (Player8, 300), (Player9, 200), (Player10, 100)}",
            board.toString());
    }

    public void addFullHigherTest() {
        ScoreBoard board = new ScoreBoard();
        board.add(new GameEntry("Player1", 1000));
        board.add(new GameEntry("Player2", 900));
        board.add(new GameEntry("Player3", 800));
        board.add(new GameEntry("Player4", 700));
        board.add(new GameEntry("Player5", 600));
        board.add(new GameEntry("Player6", 500));
        board.add(new GameEntry("Player7", 400));
        board.add(new GameEntry("Player8", 300));
        board.add(new GameEntry("Player9", 200));
        board.add(new GameEntry("Player10", 100));
        board.add(new GameEntry("Player11", 550));

        assertEquals(
            "{(Player1, 1000), (Player2, 900), (Player3, 800), (Player4, 700), (Player5, 600), (Player11, 550), (Player6, 500), (Player7, 400), (Player8, 300), (Player9, 200)}",
            board.toString());
    }

    public void isHighscoreFullYesTest() {
        ScoreBoard board = new ScoreBoard();
        board.add(new GameEntry("Player1", 1000));
        board.add(new GameEntry("Player2", 900));
        board.add(new GameEntry("Player3", 800));
        board.add(new GameEntry("Player4", 700));
        board.add(new GameEntry("Player5", 600));
        board.add(new GameEntry("Player6", 500));
        board.add(new GameEntry("Player7", 400));
        board.add(new GameEntry("Player8", 300));
        board.add(new GameEntry("Player9", 200));
        board.add(new GameEntry("Player10", 100));

        assertEquals(true, board.isHighscore(550));
    }

    public void isHighscoreFullNoTest() {
        ScoreBoard board = new ScoreBoard();
        board.add(new GameEntry("Player1", 1000));
        board.add(new GameEntry("Player2", 900));
        board.add(new GameEntry("Player3", 800));
        board.add(new GameEntry("Player4", 700));
        board.add(new GameEntry("Player5", 600));
        board.add(new GameEntry("Player6", 500));
        board.add(new GameEntry("Player7", 400));
        board.add(new GameEntry("Player8", 300));
        board.add(new GameEntry("Player9", 200));
        board.add(new GameEntry("Player10", 100));

        assertEquals(false, board.isHighscore(50));
    }

}
