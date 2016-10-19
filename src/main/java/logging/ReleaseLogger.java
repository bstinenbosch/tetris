package logging;

public class ReleaseLogger extends Logger implements ILogger {

    @Override
    public void doLog(Object sender, LogType logtype, String message) {
        // do nothing because I'm the off mode.
    }

    @Override
    public void run() {
        // do nothing because I'm the off mode.
    }

    @Override
    public void setDebugMode() {
        logger = new DebugLogger();
        logger.start();
    }

    @Override
    public void setReleaseMode() {
        // do nothing.

    }

}
