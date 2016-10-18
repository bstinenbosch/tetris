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

}
