package tetris;

import org.junit.Assert;
import org.junit.Test;

public class TickTest extends Thread {
    boolean trueReported = false;

    @Test
    public void test_fire_ontick_event_paused() throws InterruptedException {
        trueReported = false;
        Tick tick = new Tick(event -> reportTrue());
        tick.setTime(100);
        tick.pause();
        tick.start();
        sleep(1550);
        Assert.assertFalse(trueReported);
        tick.requestStop();
    }

    @Test
    public void test_fire_ontick_event_unpaused() throws InterruptedException {
        trueReported = false;
        Tick tick = new Tick(event -> reportTrue());
        tick.setTime(100);
        tick.unpause();
        tick.start();
        sleep(1550);
        Assert.assertTrue(trueReported);
        tick.requestStop();

    }

    @Test
    public void test_fire_ontick_event_stoped() throws InterruptedException {
        Tick tick = new Tick(event -> reportTrue());
        tick.setTime(100);
        tick.start();
        tick.unpause();
        tick.requestStop();
        sleep(1550);
        trueReported = false;
        sleep(1550);
        Assert.assertFalse(trueReported);
    }

    private void reportTrue() {
        trueReported = true;
    }

    @Test
    public void test_setters_getters() {
        Tick tick = new Tick(event -> reportTrue());
        tick.setTime(500);
        Assert.assertEquals(tick.getTime(), 500);
    }
}