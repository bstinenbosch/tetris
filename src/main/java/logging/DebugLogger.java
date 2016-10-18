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

public class DebugLogger extends Logger implements ILogger {
    private static volatile StringBuilder queue = new StringBuilder();
    private static volatile boolean debug;

    /**
     * The logger runs in his own thread to prevent concurrent writing
     * exceptions on the log file if multiple threads are logging.
     */
    public void run() {
        debug = true;
        while (debug) {
            try {
                sleep(5000);
                if (queue.length() > 10) {
                    writeToFile();
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        writeToFile();
    }

    private void writeToFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write(queue.toString());
            writer.close();
            capFileSize();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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

    public void doLog(Object sender, LogType logtype, String message) {
        String msg = String.format("[%s] message @[%s] from object %s: %s\r\n", logtype.toString(),
            new Timestamp(Calendar.getInstance().getTimeInMillis()).toString(), sender.toString(),
            message);
        queue.append(msg);
    }

    public static void setReleaseMode() {
        if (logger instanceof DebugLogger) {
            debug = false;
            try {
                logger.join(6000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        if (!(logger instanceof ReleaseLogger)) {
            logger = new ReleaseLogger();
        }
    }
}
