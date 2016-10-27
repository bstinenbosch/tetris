package tetris.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioStreaming {
    private static int themeLoop = MediaPlayer.INDEFINITE;
    public static MediaPlayer themePlayer;
    public static MediaPlayer remixPlayer;

    // "C:/Users/rober/Documents/Workspace/Tetris/src/main/resources/sound/theme.mp3"

    public static void playTheme() {
        Media original = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/')
            + "/" + "src/main/resources/sound/theme.mp3");
        themePlayer = new MediaPlayer(original);
        themePlayer.setCycleCount(themeLoop);
        themePlayer.play();
    }

    public static void playRemix() {
        themePlayer.stop();
        Media remix = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/"
            + "src/main/resources/sound/TetrisHardstyle.mp3");
        remixPlayer = new MediaPlayer(remix);
        remixPlayer.setCycleCount(themeLoop);
        remixPlayer.play();
    }

}
