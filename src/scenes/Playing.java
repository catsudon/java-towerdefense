package scenes;

import help.LevelBuilder;
import help.LoadSave;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private TileManager tileManager;
	private ActionBar bottomBar;
	private Tile selectedTile;
	
	@SuppressWarnings("unused")
	private int mouseX, mouseY;
	
	public Playing(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		loadDefaultLevel();
		
		lvl = LevelBuilder.getLevelData();
		tileManager = new TileManager();
		bottomBar = new ActionBar(0, 640, 640, 100, this);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		drawLevel(gc);
		bottomBar.draw(gc);
	}
	
	private void drawLevel(GraphicsContext gc) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				gc.drawImage(getSprite(id), x * 32, y * 32);
			}
		}
	}

	private Image getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
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
			bottomBar.mouseMoved(x, y);
		}
		else {
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
	}

	public TileManager getTileManager() {
		return tileManager;
	}
	
	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");

	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}
}
