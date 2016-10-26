package tetris.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioStreaming {
    private static int themeLoop = MediaPlayer.INDEFINITE;
    public static MediaPlayer player;
    public static MediaPlayer player2;

    // "C:/Users/rober/Documents/Workspace/Tetris/src/main/resources/sound/theme.mp3"

    public static void playTheme() {
        Media l = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/"
            + "src/main/resources/sound/Q.mp3");
        player = new MediaPlayer(l);
        player.setCycleCount(themeLoop);
        player.play();
    }

    public static void playRemix() {
        player.stop();
        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/"
            + "src/main/resources/sound/TetrisHardstyle.mp3");
        player2 = new MediaPlayer(m);
        player2.setCycleCount(themeLoop);
        player2.play();
    }

}
