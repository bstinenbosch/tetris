package tetris;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    private Score score;

    @Before
    public void set_up() {
        score = new Score();
    }

    @Test
    public void test_score_one_line() {
        score.add(1);
        assertEquals(score.getScore(), 40);
    }

    @Test
    public void test_score_two_lines() {
        score.add(2);
        assertEquals(score.getScore(), 50 * 2);
    }

    @Test
    public void test_score_three_lines() {
        score.add(3);
        assertEquals(score.getScore(), 100 * 3);
    }

    @Test
    public void test_score_four_lines() {
        score.add(4);
        assertEquals(score.getScore(), 300 * 4);
    }

    @Test
    public void test_reset_score() {
        score.add(4);
        score.reset();
        assertEquals(score.getScore(), 0);
    }

    @Test
    public void test_increment_level_one_line() {
        score.addLevel(1);
        assertEquals(score.getLevel(), 0);
    }

    @Test
    public void test_increment_level_ten_lines() {
        score.addLevel(10);
        assertEquals(score.getLevel(), 1);
    }

    @Test
    public void test_increment_level_fourty_lines() {
        score.addLevel(40);
        assertEquals(score.getLevel(), 4);
    }

}
