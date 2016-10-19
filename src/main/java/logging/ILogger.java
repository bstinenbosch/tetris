package logging;

import logging.Logger.LogType;

public interface ILogger {

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
    public void doLog(Object sender, LogType logtype, String message);

    /**
     * This function is called to switch the logger to debug mode, if the logger
     * is not already in debug mode.
     */
    public void setDebugMode();

    /**
     * This function is called to switch the logger to release mode, if the
     * logger is not already in release mode.
     */
    public void setReleaseMode();

}
