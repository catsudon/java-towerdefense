package scenes;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Game;
import main.Render;
import sharedObject.RenderableHolder;

public class Menu extends GameScene implements SceneMethods {
	
	private Random random;
	private ArrayList<Image> sprites = new ArrayList<>();

	public Menu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		this.random = new Random();
		loadSprites();
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(int yIndex = 0; yIndex < 32; yIndex++) {
			for(int xIndex = 0; xIndex < 32; xIndex++) {
				gc.setFill(getRandomColor());

				int x = 32 * xIndex;
				int y = 32 * yIndex;				

				gc.drawImage(sprites.get(getRandomInt()), x, y);
				
				/*
				gc.strokeRect(x, y, 32, 32);
				gc.fillRect(x, y, 32, 32);
				*/
			}
		}
	}
	
	public void loadSprites() {	
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				sprites.add(Render.getSubImage(RenderableHolder.mapSprite, 32 * x, 32 * y, 32, 32));
			}
		}
	}
	
	private int getRandomInt() {
		return random.nextInt(100);
	}
	
	private Color getRandomColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		
		return Color.rgb(r, g, b);
	}

	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
