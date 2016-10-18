package logging;

import java.io.File;

public abstract class Logger extends Thread implements ILogger {
    protected static volatile File file = new File("log.log");
    protected static volatile int logLength = 10000;
    protected static Logger logger;

    /**
     * supported logging types.
     *
     */
    public enum LogType {
        INFO, WARNING, ERROR
    }

    protected Logger() {
    } // unreachable because static

    public abstract void run();

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
        logger.doLog(sender, logtype, message);
    }

    public static void info(Object sender, String message) {
        logger.doLog(sender, LogType.INFO, message);
    }

    public static void error(Object sender, String message) {
        logger.doLog(sender, LogType.ERROR, message);
    }

    public static void warning(Object sender, String message) {
        logger.doLog(sender, LogType.WARNING, message);
    }

    /**
     * clearLog truncates the log, in case you accidentally logged a nude
     * picture or something.
     */
    public static void clearLog() {
        if (file.exists()) {
            file.delete();
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
    public static synchronized void setDebugOn() {
        ReleaseLogger.setDebugMode();
    }

    /**
     * switch debug off.
     */
    public static synchronized void setDebugOff() {
        DebugLogger.setReleaseMode();
    }
}
