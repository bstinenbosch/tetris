package tetris.sound;

import java.net.URL;

import tetris.Score;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioStreaming {

    private URL theme = getClass().getClassLoader().getResource("sound/Q.mp3");

    // "C:/Users/rober/Documents/Workspace/Tetris/src/main/resources/sound/theme.mp3"

    public static void playTheme() {
        if (Score.getScore4Music() < 200) {

            Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/"
                + "src/main/resources/sound/Q.mp3");

            MediaPlayer player = new MediaPlayer(m);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        } else if (Score.getScore4Music() > 200) {

            Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/"
                + "src/main/resources/sound/theme.mp3");

            MediaPlayer player = new MediaPlayer(m);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        }

    }
}
