package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Render {
	private Game game;
	
	public Render(Game game) {
		this.game = game;
	}
	
	public void render(GraphicsContext gc) {
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().render(gc);
				break;
			case PLAYING:
				game.getPlaying().render(gc);
				break;
			case SETTINGS:
				game.getSettings().render(gc);
				break;
			case EDIT:
				game.getEditing().render(gc);
				break;
			case GAME_OVER:
				game.getGameOver().render(gc);
				break;
		}
	}
}
