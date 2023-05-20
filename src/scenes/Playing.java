package scenes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TileManager;
import managers.TowerManager;
import managers.WaveManager;
import media.SoundPlayer;
import objects.PathPoint;
import objects.Tile;
import ui.ActionBar;
import utilities.Constants;
import utilities.LoadSave;

import static utilities.Constants.Tiles.*;
import static utilities.Constants.Towers.OWNER;
import static utilities.Constants.Towers.PRINCESS;

import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.tower.Tower; 

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	
	private TileManager tileManager;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private ProjectileManager projectileManager;
	private WaveManager waveManager;
	
	private ActionBar actionBar;
	private Tile selectedTile;
	
	private PathPoint start;
	private PathPoint end;
	
	@SuppressWarnings("unused")
	private int mouseX, mouseY;
	
	private Tower selectedTower;

	private int goldTick;

	private boolean gamePaused;
	
	private SoundPlayer soundPlayer = new SoundPlayer();
	
	public Playing(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		loadDefaultLevel();
		
		lvl = LoadSave.GetLevelData("new_level");
		
		actionBar = new ActionBar(0, 640, 640, 160, this);

		this.tileManager = new TileManager();
		this.enemyManager = new EnemyManager(this, start, end);
		this.towerManager = new TowerManager(this);
		this.projectileManager = new ProjectileManager(this);
		this.waveManager = new WaveManager(this);
		
		this.mouseX = 320;
		this.mouseY = 690;
	}
	
	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
		ArrayList<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}
	
	public void update() {
		if(gamePaused) {
			return ;
		}
		updateTick();
		waveManager.update();
		
		// Gold Tick
		goldTick++;
		if(goldTick % (60 * 3) == 0) {
			actionBar.addGold(1);
			goldTick = 0;
		}
		
		if(isAllEnemiesDead()) {
			if(isThereMoreWaves()) {
				waveManager.startWaveTimer();
				// Check Timer
				if(isWaveTimerOver()) {
					// Increase Wave Index 
					waveManager.increaseWaveIndex();
					waveManager.resetEnemyIndex();
				}
				
				
			}
		}
		
		if(isTimeForNewEnemy()) {
			spawnEnemy();
		}
		
		enemyManager.update();
		towerManager.update();
		projectileManager.update();
	}
	
	private boolean isWaveTimerOver() {
		return waveManager.isWaveTimerOver();
	}

	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();
	}

	private boolean isAllEnemiesDead() {
		if(waveManager.isThereEnemyLeftInWave()) {
			return false;
		}
		return enemyManager.getEnemies().size() == 0;
	}
	
	private void spawnEnemy() {
		enemyManager.spawnEnemy(this.getWaveManager().getNextEnemy(), this.getWaveManager().getWaveIndex());
	}

	private boolean isTimeForNewEnemy() {
		if(this.getWaveManager().isTimeForNewEnemy()) {
			if(this.getWaveManager().isThereEnemyLeftInWave()) {
				return true;
			}
		}
		return false;
	}
	
	public void setSelectedTower(Tower selectedTower) {
		// TODO Auto-generated method stub
		this.selectedTower = selectedTower;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		drawLevel(gc);
		projectileManager.draw(gc);
		enemyManager.draw(gc);
		towerManager.draw(gc);
		drawSelectedTower(gc);	
		drawHighlight(gc);
		actionBar.draw(gc);
	}

	private void drawHighlight(GraphicsContext gc) {
		if(mouseY >= 640) {
			return ;
		}
		gc.setStroke(Color.PINK);
		gc.strokeRect(mouseX, mouseY, 32, 32);
	}

	private void drawSelectedTower(GraphicsContext gc) {
		if(selectedTower == null) {
			return ;
		}
		gc.drawImage(towerManager.getTowerImages()[selectedTower.getTowerType()], mouseX, mouseY);
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
			actionBar.mouseClicked(x, y);
			return ;
		}
		if (selectedTower != null){
			if(!isTileGrass(mouseX, mouseY)) {
				return ;
			}
			int xIndex = mouseX / 32;
			int yIndex = mouseY / 32;
			if(getTowerAt(32 * xIndex, 32 * yIndex) != null) {
				return ;
			}
			decreaseGold(selectedTower.getCost());
			towerManager.addTower(selectedTower, xIndex, yIndex);
			selectedTower = null;
		}
		else {
			int xIndex = mouseX / 32;
			int yIndex = mouseY / 32;
			Tower tower = getTowerAt(32 * xIndex, 32 * yIndex);
			actionBar.displayTower(tower);
		}
	}
	
	private void decreaseGold(int towerCost) {
		actionBar.decreaseGold(towerCost);
	}

	private Tower getTowerAt(int x, int y) {
		// TODO Auto-generated method stub
		return towerManager.getTowerAt(x, y);
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
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
			actionBar.mouseMoved(x, y);
			mouseX = x;
			mouseY = y;
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
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			actionBar.mouseReleased(x, y);
		}
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
	}

	public TileManager getTileManager() {
		return tileManager;
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	@Override
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyCode keyCode) {
		if(keyCode == KeyCode.ESCAPE) {
			setSelectedTower(null);
		}
	}

	public void shootEnemy(Tower tower, Enemy enemy) {
		if(tower.getTowerType() == OWNER) {
			projectileManager.mawarikougeki(tower);
		}
		else {
			projectileManager.newProjectile(tower, enemy);
		}
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}
	
	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}
	
	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}
	
	public void rewardPlayer(int enemyType) {
		actionBar.addGold(Constants.Enemies.getConstantReward(enemyType));
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public void removeOneLife() {
		actionBar.removeOneLife();
	}

	public void resetEverything() {
		actionBar.resetEverything();

		// managers
		enemyManager.reset();
		towerManager.reset();
		projectileManager.reset();
		waveManager.reset();

		mouseX = 0;
		mouseY = 0;

		selectedTower = null;
		goldTick = 0;
		gamePaused = false;
	}
}
