package tetris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Callable;

import org.junit.Test;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.with;
import static com.jayway.awaitility.Duration.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import logging.Logger;

public class LoggerTest {

    @Test
    public void test_logcreate() throws Exception {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.clearLog();
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Logger.setDebugOff();

        with().pollDelay(ONE_HUNDRED_MILLISECONDS)
                .and().with().pollInterval(TWO_HUNDRED_MILLISECONDS)
                .and().with().timeout(ONE_MINUTE)
                .await("file creation")
                .until(fileIsCreatedOnDisk(testloc), equalTo(true));

        assertTrue(new File(testloc).exists());
    }

    @Test
    public void test_logdelete() throws Exception {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");

        with().pollDelay(ONE_HUNDRED_MILLISECONDS)
                .and().with().pollInterval(TWO_HUNDRED_MILLISECONDS)
                .and().with().timeout(ONE_MINUTE)
                .await("file creation")
                .until(fileIsCreatedOnDisk(testloc), equalTo(true));

        assertTrue(new File(testloc).exists());

        Logger.setDebugOff();
        Logger.clearLog();

        assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_debugMode() throws Exception {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOff();
        Logger.clearLog();
        Logger.log(this, Logger.LogType.ERROR, "test 1");

        assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_capLog() throws Exception {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setLogLength(10);
        Logger.clearLog();
        Logger.setDebugOn();
        for (int i = 0; i < 100; i++) {
            Logger.log(this, Logger.LogType.ERROR, "test 1");
            Logger.info(this, "test 1");
            Logger.warning(this, "test 1");
            Logger.error(this, "test 1");
        }
        Logger.setDebugOff();

        File testlocFile = new File(testloc);

        with().pollDelay(ONE_HUNDRED_MILLISECONDS)
                .and().with().pollInterval(TWO_HUNDRED_MILLISECONDS)
                .and().with().timeout(ONE_MINUTE)
                .await("file creation")
                .until(fileIsCreatedOnDisk(testloc), equalTo(true));

        assertTrue(testlocFile.exists());

        int count = 0;
        File file = new File(testloc);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while (reader.readLine() != null) {
            ++count;
        }
        reader.close();
        assertEquals(10, count);
    }

    @Test
    public void test_getters() throws ClassCastException {
        assertTrue(Logger.getLogDir() instanceof String);
        assertTrue(Logger.getLogLength() == Logger.getLogLength());
    }

    private Callable<Boolean> fileIsCreatedOnDisk(final String filename) {
        return () -> {
            File file = new File(filename);
            return file.exists();
        };
    }
}
