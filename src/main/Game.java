package main;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game extends Application {
	
	private GameScreen gameScreen;
	
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setTitle("Tower Defence Tutorial");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Canvas canvas = new Canvas(400, 400);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gameScreen = new GameScreen();
		gameScreen.paintComponent(gc);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}