package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends Pane {
	
	private Random random;
	
	private Image image;
	
	private ArrayList<Image> sprites;
	
	public GameScreen(Image image) {
		random = new Random();
		
		this.image = image;
		sprites = new ArrayList<>();
		loadSprites();
	}
	
	public void loadSprites() {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				sprites.add(getSubImage(image, 32 * x, 32 * y, 32, 32));
			}
		}
	}
	
	public void drawImage(GraphicsContext gc, Image image) {
		//gc.drawImage(image, 0, 0, null);
	
		//gc.drawImage(getSubImage(image, 32 * 9, 32 * 1, 32, 32), 0, 0);
	
		gc.drawImage(sprites.get(8), 0, 0);
	}
	
	public void paintComponent(GraphicsContext gc) {
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
	
	private Image getSubImage(Image image, int x, int y, int w, int h) {
		PixelReader pixelReader = image.getPixelReader();
		WritableImage writableImage = new WritableImage(pixelReader, x, y, w, h);

		return writableImage;
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
}
