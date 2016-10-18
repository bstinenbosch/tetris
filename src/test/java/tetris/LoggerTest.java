package tetris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import logging.Logger;

public class LoggerTest {

    /**
     * By putting all tests sync'ed in one method the tests can't be executed in
     * different threads, since they are not thread-safe.
     * 
     * @throws Exception
     */

    public void init(String testloc) {
        File file = new File(testloc);
        if (file.exists()) {
            file.delete();
        }
    }

    public void cleanup(String testloc) {
        File file = new File(testloc);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public synchronized void test_logcreate() throws Exception {
        String testloc = "test1.log";
        init(testloc);
        Logger.setLogDir(testloc);
        Logger.clearLog();
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Logger.setDebugOff();
        waitForFileCreation(testloc);
        assertTrue(new File(testloc).exists());
        cleanup(testloc);
    }

    @Test
    public synchronized void test_logdelete() throws Exception {
        String testloc = "test2.log";
        init(testloc);
        Logger.setLogDir(testloc);
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Logger.setDebugOff();
        waitForFileCreation(testloc);
        assertTrue(new File(testloc).exists());
        Logger.clearLog();
        waitForFileRemoval(testloc);
        assertFalse(new File(testloc).exists());
        cleanup(testloc);
    }

    @Test
    public synchronized void test_debugMode() throws Exception {
        String testloc = "test3.log";
        init(testloc);
        Logger.setLogDir(testloc);
        Logger.setDebugOff();
        Logger.clearLog();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Logger.setDebugOff();
        waitForFileRemoval(testloc);
        assertFalse(new File(testloc).exists());
        cleanup(testloc);
    }

    @Test
    public synchronized void test_capLog() throws Exception {
        String testloc = "test4.log";
        init(testloc);
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
        waitForFileCreation(testloc);
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
        cleanup(testloc);
    }

    @Test
    public synchronized void test_getters() throws ClassCastException {
        assertTrue(Logger.getLogDir() instanceof String);
        Logger.setLogLength(10);
        assertEquals(Logger.getLogLength(), 10);
    }

    private void waitForFileCreation(String testloc) throws InterruptedException, TimeoutException {
        File file = new File(testloc);
        int counter = 0;
        while (!file.exists()) {
            Thread.sleep(1000);
            if (counter++ > 10) {
                throw new TimeoutException("file wasn't created within 1 minute.");
            }
        }
    }

    private void waitForFileRemoval(String testloc) throws InterruptedException, TimeoutException {
        File file = new File(testloc);
        int counter = 0;
        while (file.exists()) {
            Thread.sleep(1000);
            if (counter++ > 10) {
                throw new TimeoutException("file wasn't removed within 1 minute.");
            }
        }
    }

}
