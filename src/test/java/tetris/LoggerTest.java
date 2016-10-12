package tetris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
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

        await().atMost(15, SECONDS).until(() -> new File(testloc).exists());
        assertTrue(new File(testloc).exists());
    }

    @Test
    public void test_logdelete() throws Exception {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");

        await().atMost(15, SECONDS).until(() -> new File(testloc).exists());
        assertTrue(new File(testloc).exists());

        Logger.setDebugOff();
        Logger.clearLog();

        await().atMost(15, SECONDS).until(() -> !(new File(testloc).exists()));
        assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_debugMode() throws Exception {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOff();
        Logger.clearLog();
        Logger.log(this, Logger.LogType.ERROR, "test 1");

        await().atMost(15, SECONDS).until(() -> !(new File(testloc).exists()));
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
        await().atMost(15, SECONDS).until(() -> new File(testloc).exists());
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
}
