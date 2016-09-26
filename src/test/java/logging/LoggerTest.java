package logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {
    @Test
    public void test_logcreate() {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.clearLog();
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Logger.setDebugOff();
        Assert.assertTrue(new File(testloc).exists());
    }

    @Test
    public void test_logdelete() {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOn();
        Logger.log(this, Logger.LogType.ERROR, "test 1");
        Assert.assertTrue(new File(testloc).exists());
        Logger.setDebugOff();
        Logger.clearLog();
        Assert.assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_debugMode() {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setDebugOff();
        Logger.clearLog();
        Logger.log(this, Logger.LogType.ERROR, "test 1");

        Assert.assertFalse(new File(testloc).exists());
    }

    @Test
    public void test_capLog() throws IOException {
        String testloc = "test.log";
        Logger.setLogDir(testloc);
        Logger.setLogLength(10);
        Logger.clearLog();
        Logger.setDebugOn();
        for (int i = 0; i < 100; i++) {
            Logger.log(this, Logger.LogType.ERROR, "test 1");
        }
        Logger.setDebugOff();
        Assert.assertTrue(new File(testloc).exists());

        int count = 0;
        File file = new File(testloc);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while (reader.readLine() != null) {
            ++count;
        }
        reader.close();
        Assert.assertEquals(10, count);

    }
}
