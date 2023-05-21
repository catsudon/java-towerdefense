package utilities;

import javafx.scene.image.Image;

public class SpritesHolder {
	
	/*
	 * map sprite image.
	 */
	private static Image mapSprite = new Image(ClassLoader.getSystemResource("images/mapSprite.png").toString());

	/*
	 * a function for getting the map sprite.
	 */
	public static Image getMapSprite() {
		return mapSprite;
	}
}
