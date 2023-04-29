package ui;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objects.Tile;
import scenes.Playing;
import scenes.SceneMethods;

public class BottomBar implements SceneMethods {

	private int x, y, width, height;
	
	private Playing playing;
	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	private Tile selectedTile;
	
	public BottomBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;
		
		initButtons();
	}
	
	private void initButtons() {

		int w = 50;
		int h = 50;
		
		int xStart = 100;
		int yStart = 665;
		int xOffset = (int) (w * 1.25f);
		
		int i = 0;
		for(Tile tile : playing.getTileManager().tiles) {
			tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
			i++;
		}
	}
	
	private void drawButtons(GraphicsContext gc) {
		drawTileButtons(gc);
		
		drawSelectedTile(gc);
	}
	
	private void drawSelectedTile(GraphicsContext gc) {
		
		if(selectedTile != null) {
			gc.drawImage(selectedTile.getSprite(), 550, 665, 50, 50);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(550, 665, 50, 50);
		}
	}
	
	private void drawTileButtons(GraphicsContext gc) {
		for(MyButton b : tileButtons) {
			gc.drawImage(getButtonImage(b.getId()), b.x, b.y, b.width, b.height);
		
			// MouseOver
			if(b.isMouseOver()) {
				gc.setStroke(Color.WHITE);
			}
			else {
				gc.setStroke(Color.BLACK);
			}
			
			// Borders
			gc.strokeRect(b.x, b.y, b.width, b.height);
			
			// MousePressed
			if(b.isMousePressed()) {
				gc.strokeRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
				gc.strokeRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
			}
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.rgb(220, 123, 15));
		gc.fillRect(x, y, width, height);
		
		drawButtons(gc);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		for(MyButton b : tileButtons) {
			if(b.getBounds().contains(x, y)) {
				selectedTile = playing.getTileManager().getTile(b.getId());
				playing.setSelectedTile(selectedTile);
				return ;
			}
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		for(MyButton b : tileButtons) {
			b.setMouseOver(false);
		}
		
		for(MyButton b : tileButtons) {
			if(b.getBounds().contains(x, y)) {
				b.setMouseOver(true);
				return ;
			}
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		for(MyButton b : tileButtons) {
			b.setMousePressed(false);
		}
		
		for(MyButton b : tileButtons) {
			if(b.getBounds().contains(x, y)) {
				b.setMousePressed(true);
				return ;
			}
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		for(MyButton b : tileButtons) {
			b.resetBooleans();
		}
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getButtonImage(int id) {
		return playing.getTileManager().getSprite(id);
	}
}
