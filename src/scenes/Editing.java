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

	/*
	 * level grids.
	 */
	private int[][] lvl;
	/*
	 * selected tile.
	 */
	private Tile selectedTile;
	/*
	 * mouse positions
	 */
	private int mouseX, mouseY;
	/*
	 * a boolean which determine if we have to draw the selected.
	 */
	private boolean drawSelect;
	/*
	 * toolbar class object.
	 */
	private Toolbar toolbar;
	
	/*
	 * start point.
	 */
	private PathPoint start;
	/*
	 * end point.
	 */
	private PathPoint end;

	/*
	 * Initialize fields.
	 */
	public Editing(Game game) {
		super(game);
		loadDefaultLevel();
		toolbar = new Toolbar(0, 640, 640, 160, this);
	}

	/*
	 * load level.
	 */
	private void loadDefaultLevel() {
		lvl = LoadSave.getLevelData("new_level");
		
		ArrayList<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}
	
	@Override
	/*
	 * render the level.
	 */
	public void render(GraphicsContext gc) {
		updateTick();
		
		drawLevel(gc);
		toolbar.draw(gc);
		drawSelectedTile(gc);
		drawPathPoints(gc);
	}
	/*
	 * draw paths.
	 */
	private void drawPathPoints(GraphicsContext gc) {
		if(start != null) {
			gc.drawImage(toolbar.getStartPathImage(), 32 * start.getxIndex(), 32 * start.getyIndex(), 32, 32);
		}
		
		if(end != null) {
			gc.drawImage(toolbar.getEndPathImage(), 32 * end.getxIndex(), 32 * end.getyIndex(), 32, 32);
		}
	}
	/*
	 * draw level.
	 */
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

	/*
	 * draw the selected tile.
	 */
	private void drawSelectedTile(GraphicsContext gc) {
		if (selectedTile != null && drawSelect) {
			gc.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32);
		}

	}
	/*
	 * save the level to a file.
	 */
	public void saveLevel() {

		LoadSave.saveLevel("new_level", lvl, start, end	);
		game.getPlaying().setLevel(lvl);

	}
	/*
	 * setter for selectedTile.
	 */
	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		drawSelect = true;
	}
	/*
	 * change selected tile.
	 */
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
	/*
	 * handle mouse click.
	 */
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
	/*
	 * handle mouse move.
	 */
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
	/*
	 * handle moude press.
	 */
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	/*
	 * handle moude release/
	 */
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	/*
	 * handle mouse drag.
	 */
	public void mouseDragged(int x, int y) {
		if (y >= 640) {

		} 
		else {
			changeTile(x, y);
		}

	}

	@Override
	/*
	 * handle mouse right click.
	 */
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			
		}
		else {
			toolbar.rotateSprite();
		}
	}
}
