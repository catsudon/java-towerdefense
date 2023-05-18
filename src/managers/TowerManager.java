package managers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Render;
import scenes.Playing;
import sharedObject.RenderableHolder;

import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.tower.Tower;

import help.Utilz;

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
		//tower = new Archer(3 * 32, 6 * 32, 0);
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
		for(Tower tower : towers) {
			tower.update();
			attackEnemyIfInRange(tower);
		}
	}

	private void attackEnemyIfInRange(Tower tower) {
		for(Enemy enemy : playing.getEnemyManager().getEnemies()) {
			if(!enemy.isAlive()) {
				continue;
			}
			if(isEnemyInRange(tower, enemy)) {
				if(tower.isCooldownOver()) {
					playing.shootEnemy(tower, enemy);
					tower.resetCooldown();
				}
			}
			else {
				
			}
		}
	}

	private boolean isEnemyInRange(Tower tower, Enemy enemy) {
		int range = Utilz.getEuclideanDistance(tower.getX(), tower.getY(), enemy.getX(), enemy.getY());
		return range <= tower.getRange();
	}

	public Image[] getTowerImages() {
		return towerImages;
	}

	public void addTower(Tower selectedTower, int xIndex, int yIndex) {
		// TODO Auto-generated method stub
		selectedTower.setX(32 * xIndex);
		selectedTower.setY(32 * yIndex);
		selectedTower.setId(towerAmount++);
		towers.add(selectedTower);
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
