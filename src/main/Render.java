package main;

import javafx.scene.canvas.GraphicsContext;

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
			case EDIT:
				game.getEditing().render(gc);
				break;
			case GAME_OVER:
				game.getGameOver().render(gc);
				break;
		}
	}
}
