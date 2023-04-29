package scenes;

import javafx.scene.image.Image;
import main.Game;

public class GameScene {
	
	protected Game game;
	
	protected int animationIndex;
	protected int ANIMATION_SPEED = 9;
	protected int tick;
	
	public GameScene(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	protected void updateTick() {
		tick++;
		if(tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex = (animationIndex + 1) % 4;
		}
	}
	
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID);
	}
	
	protected Image getSprite(int spriteID, int animationIndex) {
		return game.getTileManager().getAnimationSprite(spriteID, animationIndex);
	}
	
	protected Image getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}
}
