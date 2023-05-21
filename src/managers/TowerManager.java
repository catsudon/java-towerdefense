package managers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import scenes.Playing;
import utilities.ImageFix;
import utilities.SpritesHolder;
import utilities.Utility;

import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.tower.Tower;

public class TowerManager {
	
	private Playing playing;
	private Image[][] towerImages;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount;
	
	public TowerManager(Playing playing) {
		this.playing = playing;
		
		loadTowerImages();
	}

	private void loadTowerImages() {
		Image atlas = SpritesHolder.getMapSprite();
		towerImages = new Image[3][2];
		
		for(int i = 0; i < 3; i++) {
			towerImages[i][0] = ImageFix.getSubImage(atlas, 32 * (i * 2), 96 * 1, 32, 32);
			towerImages[i][1] = ImageFix.getSubImage(atlas, 32 * (i * 2 + 1), 96 * 1, 32, 32);
		}
	}
	
	public void draw(GraphicsContext gc) {
		
		for(Tower tower : towers) {
			gc.drawImage(towerImages[tower.getTowerType()][tower.getAnimationStatus()], tower.getX(), tower.getY());
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
					tower.toggleAnimationStatus();
				}
			}
		}
	}

	private boolean isEnemyInRange(Tower tower, Enemy enemy) {
		int range = Utility.getEuclideanDistance(tower.getX(), tower.getY(), enemy.getX(), enemy.getY());
		return range <= tower.getRange();
	}

	public Image[][] getTowerImages() {
		return towerImages;
	}

	public void addTower(Tower selectedTower, int xIndex, int yIndex) {
		// TODO Auto-generated method stub
		selectedTower.setX(32 * xIndex);
		selectedTower.setY(32 * yIndex);
		selectedTower.setId(towerAmount++);
		towers.add(selectedTower);
	}
	
	public void removeTower(Tower tower) {
		towers.remove(tower);
	}

	public Tower getTowerAt(int x, int y) {
		for(Tower tower : towers) {
			if(tower.getX() == x && tower.getY() == y) {
				return tower;
			}
		}
		return null;
	}

	public void upgradeTower(Tower tower) {
		tower.upgradeTower();
	}

	public void reset() {
		towers.clear();
		towerAmount = 0;
	}
}
