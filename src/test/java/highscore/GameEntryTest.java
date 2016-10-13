package highscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameEntryTest {

    @Test
    public void toStringConstructorTest() {
        GameEntry entry = new GameEntry("Bas", 500);
        assertEquals("(Bas, 500)", entry.toString());
    }

    @Test
    public void toStringSingleConstructorTest() {
        GameEntry entry = new GameEntry(500);
        assertEquals("(, 500)", entry.toString());
    }

    @Test
    public void setNameTest() {
        GameEntry entry = new GameEntry(500);
        assertEquals("(, 500)", entry.toString());
        entry.setName("Bas");
        assertEquals("(Bas, 500)", entry.toString());
    }

    @Test
    public void getNameTest() {
        GameEntry entry = new GameEntry("Bas", 500);
        assertEquals("Bas", entry.getName());
    }

    @Test
    public void getScoreTest() {
        GameEntry entry = new GameEntry("Bas", 500);
        assertEquals(500, entry.getScore());
    }

}
