package highscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XMLScoreBoardTest {

    @Test
    public void LoadScoresTest() {
        IScoreBoard board = new XMLScoreBoard("src/test/resources/highscores.xml");
        assertEquals("{(Pascal, 700), (Bas, 500)}", board.toString());
    }

    @Test
    public void toStringTest() {
        IScoreBoard board = new XMLScoreBoard("");
        assertEquals("{}", board.toString());
    }

    @Test
    public void addEntryTest() {
        IScoreBoard board = new XMLScoreBoard("");
        GameEntry entry = new GameEntry("Pascal", 500);
        board.add(entry);
        assertEquals("{(Pascal, 500)}", board.toString());
    }

    @Test
    public void addEntryHigherTest() {
        IScoreBoard board = new XMLScoreBoard("");
        GameEntry entry = new GameEntry("Pascal", 500);
        board.add(entry);
        entry = new GameEntry("Bas", 700);
        board.add(entry);
        assertEquals("{(Bas, 700), (Pascal, 500)}", board.toString());
    }

    @Test
    public void addEntryLowerTest() {
        IScoreBoard board = new XMLScoreBoard("");
        GameEntry entry = new GameEntry("Pascal", 500);
        board.add(entry);
        entry = new GameEntry("Robbert", 300);
        board.add(entry);
        assertEquals("{(Pascal, 500), (Robbert, 300)}", board.toString());
    }

    @Test
    public void addFullLowerTest() {
        IScoreBoard board = new XMLScoreBoard("");
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

    @Test
    public void addFullHigherTest() {
        IScoreBoard board = new XMLScoreBoard("");
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

    @Test
    public void isHighscoreFullYesTest() {
        IScoreBoard board = new XMLScoreBoard("");
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

    @Test
    public void isHighscoreFullNoTest() {
        IScoreBoard board = new XMLScoreBoard("");
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
