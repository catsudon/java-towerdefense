package managers;

import static utilities.Constants.Tiles.*;

import java.util.ArrayList;

import javafx.scene.image.Image;
import objects.Tile;
import utilities.ImageFix;
import utilities.SpritesHolder;

public class TileManager {
	
	@SuppressWarnings("unused")
	private Tile GRASS, WATER, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, BL_WATER_CORNER,
	TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, T_WATER, R_WATER, B_WATER, L_WATER, TL_ISLE, TR_ISLE,
	BR_ISLE, BL_ISLE;
	
	private Image atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();
	
	public ArrayList<Tile> grass = new ArrayList<>();
	public ArrayList<Tile> water = new ArrayList<>();
	public ArrayList<Tile> roadS = new ArrayList<>();
	public ArrayList<Tile> roadC = new ArrayList<>();
	public ArrayList<Tile> corners = new ArrayList<>();
	public ArrayList<Tile> beaches = new ArrayList<>();
	public ArrayList<Tile> islands = new ArrayList<>();

	public ArrayList<Tile> pathStart = new ArrayList<>();
	public ArrayList<Tile> pathEnd = new ArrayList<>();
	
	public TileManager() {
		atlas = SpritesHolder.getMapSprite();
		createTiles();
	}
	
	private void createTiles() {
		int id = 0;
		
		grass.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));
		water.add(WATER = new Tile(getAnimationSprites(0, 0, 4), id++, WATER_TILE));

		roadS.add(ROAD_LR = new Tile(getSprite(8, 0), id++, ROAD_TILE));
		roadS.add(ROAD_TB = new Tile(ImageFix.getRotatedImage(getSprite(8, 0), 90), id++, ROAD_TILE));
		
		roadC.add(ROAD_B_TO_R = new Tile(getSprite(7, 0), id++, ROAD_TILE));
		
		roadC.add(ROAD_L_TO_B = new Tile(ImageFix.getRotatedImage(getSprite(7, 0), 90), id++, ROAD_TILE));
		roadC.add(ROAD_L_TO_T = new Tile(ImageFix.getRotatedImage(getSprite(7, 0), 180), id++, ROAD_TILE));
		roadC.add(ROAD_T_TO_R = new Tile(ImageFix.getRotatedImage(getSprite(7, 0), 270), id++, ROAD_TILE));

		corners.add(BL_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(5, 0), 0), id++, WATER_TILE));
		corners.add(TL_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(5, 0), 90), id++, WATER_TILE));
		corners.add(TR_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(5, 0), 180), id++, WATER_TILE));
		corners.add(BR_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(5, 0), 270), id++, WATER_TILE));

		beaches.add(T_WATER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(6, 0), 0), id++, WATER_TILE));
		beaches.add(R_WATER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(6, 0), 90), id++, WATER_TILE));
		beaches.add(B_WATER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(6, 0), 180), id++, WATER_TILE));
		beaches.add(L_WATER = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(6, 0), 270), id++, WATER_TILE));

		islands.add(TL_ISLE = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(4, 0), 0), id++, WATER_TILE));
		islands.add(TR_ISLE = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(4, 0), 90), id++, WATER_TILE));
		islands.add(BR_ISLE = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(4, 0), 180), id++, WATER_TILE));
		islands.add(BL_ISLE = new Tile(ImageFix.getBuildRotatedImage(getAnimationSprites(0, 0, 4), getSprite(4, 0), 270), id++, WATER_TILE));
	
		pathStart.add(new Tile(getSprite(7, 2), -1, -1));
		pathEnd.add(new Tile(getSprite(8, 2), -2, -1));
		
		tiles.addAll(grass);
		tiles.addAll(water);
		tiles.addAll(roadS);
		tiles.addAll(roadC);
		tiles.addAll(corners);
		tiles.addAll(beaches);
		tiles.addAll(islands);
		
		tiles.addAll(pathStart);
		tiles.addAll(pathEnd);
	}
	
	/*
	private Image[] getImages(int firstX, int firstY, int secondX, int secondY) {
		return new Image[] {getSprite(firstX, firstY), getSprite(secondX, secondY)};
	}
	*/
	
	public Tile getTile(int id) {
		return tiles.get(id);
	}
	
	public Image getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	private Image getSprite(int xIndex, int yIndex) {
		return ImageFix.getSubImage(atlas, 32 * xIndex, 32 * yIndex, 32, 32);
	}
	
	public Image getAnimationSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex); 
	}
	
	private Image[] getAnimationSprites(int xIndex, int yIndex, int amount) {
		Image[] images = new Image[amount];
		
		for(int i = 0; i < amount; i++) {
			images[i] = getSprite(xIndex + i, yIndex);
		}
		return images;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public ArrayList<Tile> getRoadS() {
		return roadS;
	}

	public ArrayList<Tile> getRoadC() {
		return roadC;
	}

	public ArrayList<Tile> getCorners() {
		return corners;
	}

	public ArrayList<Tile> getBeaches() {
		return beaches;
	}

	public ArrayList<Tile> getIslands() {
		return islands;
	}

	public boolean isSpriteAnimation(int spriteID) {
		return tiles.get(spriteID).isAnimation();
	}
}
