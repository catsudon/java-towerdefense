package scenes;

import help.LevelBuilder;
import javafx.scene.canvas.GraphicsContext;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private TileManager tileManager;
	private BottomBar bottomBar;
	private Tile selectedTile;
	
	private int mouseX, mouseY;
	
	private boolean drawSelected;
	
	public Playing(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		lvl = LevelBuilder.getLevelData();
		tileManager = new TileManager();
		bottomBar = new BottomBar(0, 640, 640, 100, this);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(int yIndex = 0; yIndex < lvl.length; yIndex++) {
			for(int xIndex = 0; xIndex < lvl[yIndex].length; xIndex++) {
				gc.drawImage(tileManager.getSprite(lvl[yIndex][xIndex]), 32 * xIndex, 32 * yIndex);
			}
		}
		bottomBar.draw(gc);
		drawSelectedTile(gc);
	}
	
	private void drawSelectedTile(GraphicsContext gc) {
		if(selectedTile != null && drawSelected) {
			gc.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32); 
		}
	}
	
	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
	}

	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			bottomBar.mouseClicked(x, y);
		}
		else if(selectedTile != null) {
			changeTile(x, y);
		}
	}
	
	private void changeTile(int x, int y) {
		if(y >= 640) {
			return ;
		}
		
		int tileX = x / 32;
		int tileY = y / 32;
		
		if(lvl[tileY][tileX] == selectedTile.getId()) {
			return ;
		}
		
		lvl[tileY][tileX] = selectedTile.getId();
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			drawSelected = false;
			bottomBar.mouseMoved(x, y);
		}
		else {
			drawSelected = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			bottomBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			bottomBar.mouseReleased(x, y);
		}
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			bottomBar.mouseDragged(x, y);
		}
		else {
			changeTile(x, y);
		}
	}

	public TileManager getTileManager() {
		return tileManager;
	}
}
