package objects;

import javafx.scene.image.Image;

public class Tile {

	private Image sprite;
	private int id;
	private String name;
	
	public Tile(Image sprite, int id, String name) {
		this.sprite = sprite;
		this.id = id;
		this.name = name;
	}

	public Image getSprite() {
		return sprite;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
