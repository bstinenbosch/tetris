package robot;

import tetris.Settings;

public class RobotController implements Runnable {

    private Settings settings;
    private static volatile boolean playing = false;
    private static volatile RobotController robot;

    private RobotController(Settings settings) {
        this.settings = settings;
    }

    public static synchronized RobotController getRobot(Settings settings) {
        if (robot == null) {
            robot = new RobotController(settings);
        }
        return robot;
    }

    public static synchronized void stopPlaying() {
        playing = false;
    }

    @Override
    public void run() {
        playing = true;
        // load settings
        while (playing) {
            // play game
        }
        // save settings
        robot = null;
    }

}
