package tetris;

import java.util.Observable;

import org.junit.Assert;
import org.junit.Test;

public class TickTest extends Thread {
    private class Counter {
        private int counter = 0;

        public void increaseCounter() {
            counter++;
        }

        public int get() {
            return counter;
        }

        public void set(int newCounter) {
            counter = newCounter;
        }
    }

    @Test
    public void test_fire_ontick_event_paused() throws InterruptedException {
        Counter counter = new Counter();
        Tick tick = new Tick(event -> counter.increaseCounter());
        tick.setTime(100);
        tick.pause();
        tick.start();
        sleep(1550);
        Assert.assertFalse(counter.get() > 0);
        tick.requestStop();
    }

    @Test
    public void test_fire_ontick_event_unpaused() throws InterruptedException {
        Counter counter = new Counter();
        Tick tick = new Tick(event -> counter.increaseCounter());
        tick.setTime(100);
        tick.unpause();
        tick.start();
        sleep(1550);
        Assert.assertTrue(counter.get() > 0);
        tick.requestStop();

    }

    @Test
    public void test_fire_ontick_event_stopped() throws InterruptedException {
        Counter counter = new Counter();
        Tick tick = new Tick(event -> counter.increaseCounter());
        tick.setTime(100);
        tick.start();
        tick.requestStop();
        sleep(1550);
        counter.set(0);
        sleep(1550);
        Assert.assertFalse(counter.get() > 0);
    }

    @Test
    public void test_setters_getters() {
        Counter counter = new Counter();
        Tick tick = new Tick(event -> counter.increaseCounter());
        tick.setTime(500);
        Assert.assertEquals(tick.getTime(), 500);
    }

    @Test
    public void test_update_from_score() {
        Counter counter = new Counter();
        Tick tick = new Tick(event -> counter.increaseCounter());
        int arg = 40;
        tick.update(new Score(), arg);
        Assert.assertEquals(tick.getTime(), (long) Math.max(1, 200 * Math.exp(-.0002 * (int) arg)));
    }

    @Test
    public void test_update_from_other() {
        Counter counter = new Counter();
        Tick tick = new Tick(event -> counter.increaseCounter());
        int arg = 40;
        tick.update(new Observable(), arg);
        Assert.assertNotEquals(tick.getTime(),
            (long) Math.max(1, 200 * Math.exp(-.0002 * (int) arg)));
    }
}