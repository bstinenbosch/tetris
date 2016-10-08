package tetris;

import org.junit.Assert;
import org.junit.Test;

public class TickTest extends Thread {
    boolean trueReported = false;

    @Test
    public void test_logcreate() throws InterruptedException {
        Tick tick = new Tick(event -> reportTrue());
        tick.pause();
        tick.start();
        sleep(250);
        Assert.assertFalse(trueReported);
        tick.unpause();
        sleep(250);
        Assert.assertTrue(trueReported);
        tick.requestStop();
        trueReported = false;
        sleep(250);
        Assert.assertFalse(trueReported);
    }

    private void reportTrue() {
        trueReported = true;
    }
}