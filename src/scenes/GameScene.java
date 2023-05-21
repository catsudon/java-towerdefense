package scenes;

import javafx.scene.image.Image;
import main.Game;

public class GameScene {
	/*
	 * Game class object.
	 */
	protected Game game;
	/*
	 * animation index.
	 */
	protected int animationIndex;
	/*
	 * animation playing speed.
	 */
	protected int ANIMATION_SPEED = 9;
	/*
	 * animetion tick count.
	 */
	protected int tick;
	
	/*
	 * initialize field.
	 */
	public GameScene(Game game) {
		this.game = game;
	}
	/*
	 * getter for Game object.
	 */
	public Game getGame() {
		return this.game;
	}
	/*
	 * update game tick.
	 */
	protected void updateTick() {
		tick++;
		if(tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex = (animationIndex + 1) % 4;
		}
	}
	/*
	 * return if the sprite has animation.
	 */
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID);
	}
	/*
	 * get sprite for animated sprite.
	 */
	protected Image getSprite(int spriteID, int animationIndex) {
		return game.getTileManager().getAnimationSprite(spriteID, animationIndex);
	}
	/*
	 * get sprite for normal sprite.
	 */
	protected Image getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}
}
