package logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoggerTest {
    @Test
    public void test_logcreate() {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.clearLog();
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Logger.setDebugOff();
        assertTrue(new File(testloc).exists());
    }

    @Test
    public void test_logdelete() {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        assertTrue(new File(testloc).exists());
        Logger.setDebugOff();
        Logger.clearLog();
        assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_debugMode() {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOff();
        Logger.clearLog();
        Logger.log(this, Logger.LogType.ERROR, "test 1");

        assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_capLog() throws IOException, InterruptedException {
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
        Thread.sleep(1000);
        File testlocFile = new File(testloc);
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
        assertTrue((int) Logger.getLogLength() == Logger.getLogLength());
    }
}
