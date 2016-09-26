package logging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Calendar;

public final class Logger extends Thread {
    private static volatile boolean debug = false;
    private static volatile StringBuilder queue = new StringBuilder();
    private static File file = new File("log.log");
    private static int logLength = 10000;
    private static Logger logger;

    /**
     * supported logging types.
     *
     */
    public enum LogType {
        INFO, WARNING, ERROR
    };

    private Logger() {
    } // unreachable because static

    /**
     * The logger runs in his own thread to prevent concurrent writing
     * exceptions on the log file if multiple threads are logging.
     */
    public void run() {
        while (debug) {
            try {
                sleep(10000);
                if (queue.length() > Math.min(logLength, 100)) {
                    try {
                        writeToFile();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void writeToFile() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, true);
        writer.write(queue.toString());
        writer.close();
        capFileSize();
    }

    private void capFileSize() throws IOException {
        int fileLength = countLines();
        if (fileLength > logLength) {
            String line;
            File tempFile = File.createTempFile("TETRIS_LOG_", ".log");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                try {
                    skipLines(fileLength - logLength, reader);
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                } finally {
                    reader.close();
                }
            } finally {
                writer.close();
            }
            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void skipLines(int lines, BufferedReader file) throws IOException {
        for (int i = 0; i < lines; i++) {
            file.readLine();
        }
    }

    private int countLines() throws IOException {
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

    /**
     * Log lets you log a line in the log file, conditional on the debug mode
     * being on.
     * 
     * @param sender
     *            the object invoking the log statement
     * @param logtype
     *            the log type, for homogeneity constrained in the LogType enum
     * @param message
     *            the message that is logged
     */
    public static void log(Object sender, LogType logtype, String message) {
        if (debug) {
            String msg = String.format("[%s] message @[%s] from object %s: %s\r\n",
                logtype.toString(),
                new Timestamp(Calendar.getInstance().getTimeInMillis()).toString(),
                sender.toString(), message);
            queue.append(msg);
        }
    }

    /**
     * clearLog truncates the log, in case you accidentally logged a nude
     * picture or something.
     */
    public static void clearLog() {
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /* getters / setters */
    public static int getLogLength() {
        return logLength;
    }

    public static void setLogLength(int length) {
        logLength = length;
    }

    public static void setLogDir(String path) {
        file = new File(path);
    }

    public static String getLogDir() {
        return file.toString();
    }

    /**
     * switch debug on.
     */
    public static void setDebugOn() {
        debug = true;
        logger = new Logger();
        logger.start();
    }

    /**
     * switch debug off.
     */
    public static void setDebugOff() {
        debug = false;
        logger = null;
    }
}
