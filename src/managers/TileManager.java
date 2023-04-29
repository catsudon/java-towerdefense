package managers;

import java.util.ArrayList;

import javafx.scene.image.Image;
import objects.Tile;
import sharedObject.RenderableHolder;

import main.Render;

public class TileManager {
	
	private Tile GRASS, WATER, ROAD;
	private Image atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();
	
	public TileManager() {
		atlas = RenderableHolder.mapSprite;
		createTiles();
	}
	
	private void createTiles() {
		int id = 0;
		tiles.add(GRASS = new Tile(getSprite(8, 1), id++, "Grass"));
		tiles.add(WATER = new Tile(getSprite(0, 6), id++, "Water"));
		tiles.add(ROAD = new Tile(getSprite(9, 0), id++, "Road"));
	}
	
	public Tile getTile(int id) {
		return tiles.get(id);
	}
	
	public Image getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	private Image getSprite(int xIndex, int yIndex) {
		return Render.getSubImage(atlas, 32 * xIndex, 32 * yIndex, 32, 32);
	}
}
