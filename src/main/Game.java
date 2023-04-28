package main;

import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.GraphicsContext;

public class Game extends Application {
	
	private static final double FPS_SET = 1.0;
	private static final double UPS_SET = 60.0;
	
	private static final double timePerFrame = 1_000_000_000.0 / FPS_SET;
	private static final double updatesPerFrame = 1_000_000_000.0 / UPS_SET;
	
	private long lastFrameTime = 0;
	private long lastUpdateTime = 0;
	private long lastCheckedTime = 0;
	private int frames = 0;
	private int updates = 0;
	
	private GameScreen gameScreen;
	
	private Render render;
	
	private Menu menu;
	private Playing playing;
	private Settings settings;

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Tower Defence Tutorial");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		
		initClasses();
		
		root.getChildren().add(gameScreen);
		
		primaryStage.show();
		
		AnimationTimer animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				// Render
				if(now - lastFrameTime >= timePerFrame) {
					GraphicsContext gc = gameScreen.getGraphicsContext2D();
					gameScreen.paintComponent(gc);
					frames++;
					lastFrameTime = now;
				}
				
				// Update
				if(now - lastUpdateTime >= updatesPerFrame) {
					updateGame();
					updates++;
					lastUpdateTime = now;
				}
				
				if(now - lastCheckedTime >= 1_000_000_000) {
					displayFPSUPS();
					lastCheckedTime = now;
				}
				gameScreen.requestFocus();
			}
		};
		
		animationTimer.start();
		
		//gameScreen.drawImage(gc, image);
	}
	
	private void updateGame() {
		InputUtility.updateInputState();
	}
	
	private void initClasses() {
		gameScreen = new GameScreen(this, 640, 640);
		render = new Render(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
	}
	
	public static void main(String[] args) {
		launch(args);	
	}
	
	private void displayFPSUPS() {
		System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
		frames = 0;
		updates = 0;
	}
	
	public Render getRender() {
		return render;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}
}