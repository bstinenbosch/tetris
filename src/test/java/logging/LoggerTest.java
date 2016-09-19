package logging;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.Assert;
	
public class LoggerTest {
	@Test
	public void test_logcreate(){	
		String testloc = "test.log";
		Logger.setLogDir(testloc);
		Logger.clearLog();
		Logger.setDebugOn();
		Logger.Log(this, Logger.LogType.ERROR, "test 1");
		Logger.setDebugOff();
		
		Assert.assertTrue(new File(testloc).exists());
	}
	
	@Test
	public void test_logdelete(){
		String testloc = "test.log";
		Logger.setLogDir(testloc);
		Logger.setDebugOn();
		Logger.Log(this, Logger.LogType.ERROR, "test 1");
		Assert.assertTrue(new File(testloc).exists());
		Logger.setDebugOff();
		Logger.clearLog();		
		Assert.assertFalse(new File(testloc).exists());		
	}
	
	@Test
	public void test_debugMode(){
		String testloc = "test.log";
		Logger.setLogDir(testloc);
		Logger.clearLog();
		Logger.setDebugOff();
		Logger.Log(this, Logger.LogType.ERROR, "test 1");
		
		Assert.assertFalse(new File(testloc).exists());		
	}
	
	@Test
	public void test_capLog() throws IOException{
		String testloc = "test.log";
		Logger.setLogDir(testloc);
		Logger.setLogLength(10);
		Logger.clearLog();
		Logger.setDebugOn();
		for(int i=0; i<100; i++){
			Logger.Log(this, Logger.LogType.ERROR, "test 1");
		}
		Logger.setDebugOff();
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(testloc)));
		lnr.skip(Long.MAX_VALUE);
		Assert.assertEquals(10, lnr.getLineNumber()+1);
		lnr.close();
		
		Assert.assertFalse(new File(testloc).exists());		
		
	}
}
