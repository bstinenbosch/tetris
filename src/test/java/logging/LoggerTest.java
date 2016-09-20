package logging;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		Logger.setDebugOff();
		Logger.clearLog();
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
		
		int count = 0;
	    BufferedReader reader = new BufferedReader(new FileReader(testloc));
	    while(reader.readLine() != null){
	    	++count;
	    }
	    reader.close();
		Assert.assertEquals(10, count);	
		
	}
}