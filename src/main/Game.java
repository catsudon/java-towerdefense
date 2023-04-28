package main;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Game extends Application {
	
	private static final double FPS_SET = 120.0;
	private static final double UPS_SET = 60.0;
	
	private static final double timePerFrame = 1_000_000_000.0 / FPS_SET;
	private static final double updatesPerFrame = 1_000_000_000.0 / UPS_SET;
	
	private long lastFrameTime = 0;
	private long lastUpdateTime = 0;
	private long lastCheckedTime = 0;
	private int frames = 0;
	private int updates = 0;
	
	private GameScreen gameScreen;
	private Image image;
	
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Tower Defence Tutorial");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		
		Canvas canvas = new Canvas(640, 640);
		root.getChildren().add(canvas);
		
		importImage();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gameScreen = new GameScreen(image);
		
		AnimationTimer animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				// render
				if(now - lastFrameTime >= timePerFrame) {
					gameScreen.paintComponent(gc);
					frames++;
					lastFrameTime = now;
				}
				
				if(now - lastUpdateTime >= updatesPerFrame) {
					updateGame();
					updates++;
					lastUpdateTime = now;
				}
				
				if(now - lastCheckedTime >= 1_000_000_000) {
					displayFPSUPS();
					lastCheckedTime = now;
				}
			}
		};
		
		animationTimer.start();
		
		primaryStage.show();
		
		//gameScreen.drawImage(gc, image);
	}
	
	private void updateGame() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	private void displayFPSUPS() {
		System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
		frames = 0;
		updates = 0;
	}
	
	private void importImage() {
		// Project > Create New Source Folder
		this.image = new Image(ClassLoader.getSystemResource("images/spriteatlas.png").toString());
	}
}