package tetris;

import org.junit.Assert;
import org.junit.Test;

public class TickTest extends Thread {
    boolean trueReported = false;

    @Test
    public void test_logcreate() throws InterruptedException {
        Tick tick = new Tick(event -> reportTrue());
        tick.setTime(500);
        Assert.assertEquals(tick.getTime(), 500);

        tick.pause();
        tick.start();
        sleep(550);
        Assert.assertFalse(trueReported);
        tick.unpause();
        sleep(550);
        Assert.assertTrue(trueReported);
        tick.requestStop();
        trueReported = false;
        sleep(550);
        Assert.assertFalse(trueReported);
    }

    private void reportTrue() {
        trueReported = true;
    }
}