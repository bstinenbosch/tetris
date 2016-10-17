package robot;

import tetris.Controller;
import tetris.Settings;

import javafx.scene.input.KeyEvent;

public class RobotController implements Runnable {

    private Settings settings;
    private KeyEvent[] actions;
    private Controller controller;
    private RandomRobot robot;
    private static volatile boolean playing = false;
    private static volatile RobotController robotController;

    private RobotController(Controller controller, Settings settings) {
        this.settings = settings;
        this.controller = controller;
        robot = new RandomRobot();
        this.controller.addScoreObserver(robot);
        setActions();
    }

    public static synchronized RobotController getRobotController(Controller controller,
        Settings settings) {
        if (robotController == null) {
            robotController = new RobotController(controller, settings);
        }
        return robotController;
    }

    public static synchronized void stopPlaying() {
        playing = false;
    }

    @Override
    public void run() {
        playing = true;
        int chosenAction;
        // load settings
        while (playing) {
            if (controller.isGameOver()) {
                robot.resetSession();
                controller.restartGame();
            }
            // play game
            // give grid and tetromino to ANN
            robot.setGameState(controller.getGrid(), controller.getTetromino());
            // ask for action
            chosenAction = robot.getNextAction();
            // handle action
            controller.handleKeyEvent(actions[chosenAction]);

        }
        // save settings
    }

    private void setActions() {
        actions = new KeyEvent[] {
            new KeyEvent(null, null, null, settings.getKeyBindings().getBinding("MOVE_LEFT"), false,
                false, false, false),
            new KeyEvent(null, null, null, settings.getKeyBindings().getBinding("MOVE_RIGHT"),
                false, false, false, false),
            new KeyEvent(null, null, null, settings.getKeyBindings().getBinding("ROTATE_LEFT"),
                false, false, false, false),
            new KeyEvent(null, null, null, settings.getKeyBindings().getBinding("ROTATE_RIGHT"),
                false, false, false, false),
            new KeyEvent(null, null, null, settings.getKeyBindings().getBinding("HARD_DROP"), false,
                false, false, false) };
    }

}
