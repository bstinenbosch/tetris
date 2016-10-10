package tetris;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    private int observedscore;

    @Test
    public void testScore() {
        Score score = new Score();
        observedscore = 0;
        score.addObserver((arg0, arg1) -> increaseScore((int) arg1));// 40, 50,
                                                                     // 100, 300
        score.add(1);
        assertEquals(observedscore, 40);
        score.reset();
        score.add(2);
        assertEquals(observedscore, 50 * 2);
        score.reset();
        score.add(3);
        assertEquals(observedscore, 100 * 3);
        score.reset();
        score.add(4);
        assertEquals(observedscore, 300 * 4);
        score.reset();
        int level = 0;
        for (int i = 1; i < 10; i++) {
            level += i % 5;
            score.add(i % 5);
            assertEquals(score.getLevel(), level / 10);
        }
    }

    private void increaseScore(int score) {
        observedscore = score;
    }

}
