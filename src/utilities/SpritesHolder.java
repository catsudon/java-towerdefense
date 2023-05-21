package utilities;

import javafx.scene.image.Image;

public class SpritesHolder {
	private static Image mapSprite = new Image(ClassLoader.getSystemResource("images/mapSprite.png").toString());

	public static Image getMapSprite() {
		return mapSprite;
	}
}