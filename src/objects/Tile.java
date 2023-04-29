package objects;

import javafx.scene.image.Image;

public class Tile {

	private Image[] sprite;
	private int id;
	private int tileType;
	
	public Tile(Image sprite, int id, int tileType) {
		this.sprite = new Image[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
	}
	
	public Tile(Image[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}
	
	public Image getSprite(int animationIndex) {
		return sprite[animationIndex];
	}

	public Image getSprite() {
		return sprite[0];
	}
	
	public boolean isAnimation() {
		return sprite.length > 1;
	}
	
	public int getId() {
		return id;
	}

	public int getTileType() {
		return tileType;
	}
}
