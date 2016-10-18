package logging;

public class ReleaseLogger extends Logger implements ILogger {

    public static void setDebugMode() {
        if (!(logger instanceof DebugLogger)) {
            logger = new DebugLogger();
            logger.start();
        }
    }

    @Override
    public void doLog(Object sender, LogType logtype, String message) {
        // do nothing because I'm the off mode.
    }

    @Override
    public void run() {
        // do nothing because I'm the off mode.
    }

}
