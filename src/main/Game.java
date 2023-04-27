package main;

import drawing.GameScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Game extends Application {
	
	private GameScreen gameScreen;
	
	private Image image;
	
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root, 640, 640);
		primaryStage.setTitle("Tower Defence Tutorial");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Canvas canvas = new Canvas(640, 640);
		root.getChildren().add(canvas);
		
		importImage();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gameScreen = new GameScreen(image);
		gameScreen.paintComponent(gc);
		
		//gameScreen.drawImage(gc, image);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void importImage() {
		// Project > Create New Source Folder
		this.image = new Image(ClassLoader.getSystemResource("images/spriteatlas.png").toString());
	}
}