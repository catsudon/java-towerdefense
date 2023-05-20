package scenes;

import javafx.scene.canvas.GraphicsContext;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.Toolbar;
import utilities.LoadSave;

import static utilities.Constants.Tiles.*;

import java.util.ArrayList;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private boolean drawSelect;
	private Toolbar toolbar;
	
	private PathPoint start;
	private PathPoint end;

	public Editing(Game game) {
		super(game);
		loadDefaultLevel();
		toolbar = new Toolbar(0, 640, 640, 160, this);
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
		
		ArrayList<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		updateTick();
		
		drawLevel(gc);
		toolbar.draw(gc);
		drawSelectedTile(gc);
		drawPathPoints(gc);
	}

	private void drawPathPoints(GraphicsContext gc) {
		if(start != null) {
			gc.drawImage(toolbar.getStartPathImage(), 32 * start.getxIndex(), 32 * start.getyIndex(), 32, 32);
		}
		
		if(end != null) {
			gc.drawImage(toolbar.getEndPathImage(), 32 * end.getxIndex(), 32 * end.getyIndex(), 32, 32);
		}
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

		LoadSave.SaveLevel("new_level", lvl, start, end	);
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
			if(selectedTile.getId() >= 0) {
	
				if (selectedTile.getId() == lvl[tileY][tileX])
					return ;
	
				lvl[tileY][tileX] = selectedTile.getId();
			}
			else {
				int id = lvl[tileY][tileX];
				if(game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
					if(selectedTile.getId() == -1) {
						start = new PathPoint(tileX, tileY);
					}
					else if(selectedTile.getId() == -2) {
						end = new PathPoint(tileX, tileY);
					}
				}
			}
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
