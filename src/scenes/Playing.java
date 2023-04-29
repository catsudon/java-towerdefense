package scenes;

import help.LoadSave;
import javafx.scene.canvas.GraphicsContext;
import main.Game;
import managers.EnemyManager;
import managers.TileManager;
import objects.Tile;
import ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private TileManager tileManager;
	private ActionBar bottomBar;
	private Tile selectedTile;
	private EnemyManager enemyManager	;
	
	@SuppressWarnings("unused")
	private int mouseX, mouseY;
	
	public Playing(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		loadDefaultLevel();
		
		lvl = LoadSave.GetLevelData("new_level");
		tileManager = new TileManager();
		bottomBar = new ActionBar(0, 640, 640, 100, this);
		enemyManager = new EnemyManager(this);
	}
	
	public void update() {
		updateTick();
		enemyManager.update();
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		drawLevel(gc);
		bottomBar.draw(gc);
		enemyManager.draw(gc);
	}
	
	private void drawLevel(GraphicsContext gc) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if(isAnimation(id)) {
					gc.drawImage(getSprite(id, animationIndex), x * 32, y * 32);
				}
				else {
					gc.drawImage(getSprite(id), x * 32, y * 32);
				}
			}
		}
	}
	
	public int getTileType(int x, int y) {
		int xIndex = x / 32;
		int yIndex = y / 32;
		
		if(xIndex < 0 || xIndex > 20 - 1 || yIndex < 0 || yIndex > 20 - 1) {
			return 0;
		}
		
		int id = lvl[y / 32][x / 32];
		return game.getTileManager().getTile(id).getTileType();
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
		else {
			enemyManager.addEnemy(x / 32 * 32, y / 32 * 32);
		}
	}
	
	@SuppressWarnings("unused")
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

	@Override
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
