package objects;

import javafx.scene.image.Image;

public class Tile {

	private Image[] sprite;
	private int id;
	private String name;
	
	public Tile(Image sprite, int id, String name) {
		this.sprite = new Image[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.name = name;
	}
	
	public Tile(Image[] sprite, int id, String name) {
		this.sprite = sprite;
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}
}
