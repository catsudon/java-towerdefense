package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameScreen extends Pane {
	
	public GameScreen() {
		
	}
	
	public void paintComponent(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(50, 50, 100, 100);
	}
}
