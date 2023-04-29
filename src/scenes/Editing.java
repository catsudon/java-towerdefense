package scenes;

import help.LoadSave;
import javafx.scene.canvas.GraphicsContext;
import main.Game;
import objects.Tile;
import ui.Toolbar;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private boolean drawSelect;
	private Toolbar toolbar;

	public Editing(Game game) {
		super(game);
		loadDefaultLevel();
		toolbar = new Toolbar(0, 640, 640, 100, this);
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
	}
	
	@Override
	public void render(GraphicsContext gc) {
		updateTick();
		
		drawLevel(gc);
		toolbar.draw(gc);
		drawSelectedTile(gc);
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

	private void drawSelectedTile(GraphicsContext gc) {
		if (selectedTile != null && drawSelect) {
			gc.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32);
		}

	}

	public void saveLevel() {

		LoadSave.SaveLevel("new_level", lvl);
		game.getPlaying().setLevel(lvl);

	}

	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		drawSelect = true;
	}

	private void changeTile(int x, int y) {
		if (selectedTile != null) {
			int tileX = x / 32;
			int tileY = y / 32;

			if (selectedTile.getId() == lvl[tileY][tileX])
				return ;

			lvl[tileY][tileX] = selectedTile.getId();
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			toolbar.mouseClicked(x, y);
			drawSelect = false;
		}
		else {
			changeTile(mouseX, mouseY);
			drawSelect = true;
		}

	}

	@Override
	public void mouseMoved(int x, int y) {

		if (y >= 640) {
			toolbar.mouseMoved(x, y);
			drawSelect = false;
		} 
		else {
			drawSelect = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}

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
		if (y >= 640) {

		} 
		else {
			changeTile(x, y);
		}

	}

	@Override
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			
		}
		else {
			toolbar.rotateSprite();
		}
	}
}
