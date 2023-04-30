package managers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Render;
import scenes.Playing;
import sharedObject.RenderableHolder;

import static help.Constants.Towers.*;

import java.util.ArrayList;

import entity.tower.Tower;

public class TowerManager {
	
	private Playing playing;
	private Image[] towerImages;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount;
	
	public TowerManager(Playing playing) {
		this.playing = playing;
		
		loadTowerImages();
		initTowers();
	}
	
	private void initTowers() {
		//tower = new Tower(3 * 32, 6 * 32, 0, ARCHER);
	}

	private void loadTowerImages() {
		Image atlas = RenderableHolder.mapSprite;
		towerImages = new Image[3];
		
		for(int i = 0; i < 3; i++) {
			towerImages[i] = Render.getSubImage(atlas, 32 * (4 + i), 32 * 1, 32, 32);
		}
	}
	
	public void draw(GraphicsContext gc) {
		
		for(Tower tower : towers) {
			gc.drawImage(towerImages[tower.getTowerType()], tower.getX(), tower.getY());
		}
	}
	
	public void update() {
		
	}

	public Image[] getTowerImages() {
		return towerImages;
	}

	public void addTower(Tower selectedTower, int xIndex, int yIndex) {
		// TODO Auto-generated method stub
		towers.add(new Tower(32 * xIndex, 32 * yIndex, towerAmount++, selectedTower.getTowerType()));
	}

	public Tower getTowerAt(int x, int y) {
		for(Tower tower : towers) {
			if(tower.getX() == x && tower.getY() == y) {
				return tower;
			}
		}
		return null;
	}
}
