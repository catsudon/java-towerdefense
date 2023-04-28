package managers;

import java.util.ArrayList;

import javafx.scene.image.Image;
import objects.Tile;
import sharedObject.RenderableHolder;

import main.Render;

public class TileManager {
	
	private Tile GRASS, WATER, ROAD;
	private Image atlas;
	private ArrayList<Tile> tiles = new ArrayList<>();
	
	public TileManager() {
		atlas = RenderableHolder.mapSprite;
		createTiles();
	}
	
	private void createTiles() {
		tiles.add(GRASS = new Tile(getSprite(8, 1)));
		tiles.add(WATER = new Tile(getSprite(0, 6)));
		tiles.add(ROAD = new Tile(getSprite(9, 0)));
	}
	
	public Image getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	private Image getSprite(int xIndex, int yIndex) {
		return Render.getSubImage(atlas, 32 * xIndex, 32 * yIndex, 32, 32);
	}
}
