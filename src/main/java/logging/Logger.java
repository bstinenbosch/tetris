package logging;

import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public final class Logger {
	private static boolean debug = false;
	private static StringBuilder queue = new StringBuilder();
	private static File file = new File("./logging/log.txt");
	private static int logLength = 10000;
	
	public enum LogType {INFO, WARNING, ERROR};
	
	private Logger(){} //unreachable because static
	
	private static void writeToFile() throws IOException {
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file, true);
		writer.write(queue.toString());
		writer.close();
		capFileSize();
	}
	
	private static void capFileSize() throws IOException {
		int fileLength = countLines();
		if(fileLength>logLength){
			File tempFile = File.createTempFile("TETRIS_LOG", ".log");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				try {
					skipLines(fileLength - logLength, reader);
					char[] buf = new char[1024];
					while(reader.read(buf)>0){
						writer.write(buf);
					}
				} finally {
					reader.close();
				} 
			} finally {
				writer.close();
				file.delete();
				Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
	
	private static void skipLines(int lines, BufferedReader file) throws IOException{
		for(int i=0;i<lines;i++){
			file.readLine();
		}
	}
	
	private static int countLines() throws IOException {
        char[] buffer = new char[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    try {
	        while ((readChars = reader.read(buffer)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (buffer[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        reader.close();
	    }
	}
	
	public static void setDebugOn(){
		debug = true;
	}
	
	public static void setDebugOff(){
		try {
			writeToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		debug = false;
	}
	
	public static boolean Log(Object T, LogType logtype, String message){
		if(debug){
			String msg = String.format("[%s] message from object %s: %s\n", logtype.toString(), T.toString(), message);
			queue.append(msg);
			if(queue.length()>Math.min(logLength, 100)){
				try {
					writeToFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public static void setLogDir(String path){
		file = new File(path);
	}
	
	public static String getLogDir(){
		return file.toString();
	}
	
	public static void clearLog(){
		file.delete();
	}
	
	public static int getLogLength(){
		return logLength;
	}
	
	public static void setLogLength(int length){
		logLength = length;
	}
}
