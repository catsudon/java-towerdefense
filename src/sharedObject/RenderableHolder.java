package sharedObject;

import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	public static Image mapSprite;
	
	static {
		loadResource();
	}
	
	public RenderableHolder() {
		
	}
	
	private static void loadResource() {
		// Project > Create New Source Folder
		mapSprite = new Image(ClassLoader.getSystemResource("images/spriteatlas.png").toString());
	}
	
	public void update() {
		
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}
}
