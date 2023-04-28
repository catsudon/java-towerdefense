package objects;

import javafx.scene.image.Image;

public class Tile {

	private Image sprite;
	
	public Tile(Image sprite) {
		this.sprite = sprite;
	}
	
	public Image getSprite() {
		return sprite;
	}
}
