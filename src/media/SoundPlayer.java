package media;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundPlayer {

	private int soundCurrentlyPlaying = 0;
	
    public SoundPlayer() {};

    public void bgm() {
    	startLoop("res/sfx/bgm.mp3");
    }
    
    public void boom() {
        start("res/sfx/boom.m4a");
    }
    
    public void whoosh() {
    	start("res/sfx/whoosh.m4a");
    }
    
    public void shoot() {
    	startShoot("res/sfx/shoot.m4a");
    }
    
    public void gameStart() {
    	start("res/sfx/start.mp3");
    }
    
    public void lvlUp() {
    	start("res/sfx/lvlup.m4a");
    }
    
    public void sell() {
    	start("res/sfx/sell.m4a");
    }
    

    
    

    public void startShoot(String path) {
    	
    	if(soundCurrentlyPlaying > 5) return ;
    	
        String soundFile = path; // Replace with the path to your sound file
        Media sound = new Media(new File(soundFile).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.setOnEndOfMedia(() -> {soundCurrentlyPlaying--; System.out.println(soundCurrentlyPlaying);});
        mediaPlayer.setVolume(0.5);
        soundCurrentlyPlaying++;
        mediaPlayer.play();
    }
    
    public void start(String path) {
    	
        String soundFile = path; 
        Media sound = new Media(new File(soundFile).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
    
    public void startLoop(String path) {
    	String soundFile = path;
    	Media sound = new Media(new File(soundFile).toURI().toString());
    	
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.setOnEndOfMedia(() -> {mediaPlayer.seek(javafx.util.Duration.ZERO);});
    	mediaPlayer.setVolume(1);
    	mediaPlayer.play();
    	
    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
                
                System.exit(0);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

	public int getSoundCurrentlyPlaying() {
		return soundCurrentlyPlaying;
	}

}