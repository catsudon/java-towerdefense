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

import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.tower.Tower; 

public class Playing extends GameScene implements SceneMethods {

	/*
	 * level grids.
	 */
	private int[][] lvl;
	/*
	 * tile manager class object.
	 */
	private TileManager tileManager;
	/*
	 * enemy manager class object.
	 */
	private EnemyManager enemyManager;
	/*
	 * tower manager class object.
	 */
	private TowerManager towerManager;
	/*
	 * projectile manager class object.
	 */
	private ProjectileManager projectileManager;
	/*
	 * wave manager class object.
	 */
	private WaveManager waveManager;
	/*
	 * sound player class object.
	 */
	private SoundPlayer soundPlayer = new SoundPlayer();
	/*
	 * action bar class object
	 */
	private ActionBar actionBar;
	/*
	 * selected tile.
	 */
	private Tile selectedTile;
	/*
	 * start point.
	 */
	private PathPoint start;
	/*
	 * end point.
	 */
	private PathPoint end;
	
	/*
	 * selected tower.
	 */
	private Tower selectedTower;
	/*
	 * gold tick count.
	 */
	private int goldTick;
	/*
	 * game paused status.
	 */
	private boolean gamePaused;
	
	/*
	 * intialize fields.
	 */ 
	public Playing(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		loadDefaultLevel();
		
		actionBar = new ActionBar(0, 640, 640, 160, this);

		this.tileManager = new TileManager();
		this.enemyManager = new EnemyManager(this, start, end);
		this.towerManager = new TowerManager(this);
		this.projectileManager = new ProjectileManager(this);
		this.waveManager = new WaveManager(this);
		
		this.mouseX = 320;
		this.mouseY = 690;
	}
	/*
	 * load level from file.
	 */
	private void loadDefaultLevel() {
		lvl = LoadSave.getLevelData("new_level");
		ArrayList<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}
	/*
	 * update status of the game (gold tick, wave cleared, lose).
	 */
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
	/*
	 * is the wave ended.
	 */
	private boolean isWaveTimerOver() {
		return waveManager.isWaveTimerOver();
	}
	/*
	 * is there a wave coming.
	 */
	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();
	}
	/*
	 * is the enemies is all dead.
	 */
	private boolean isAllEnemiesDead() {
		if(waveManager.isThereEnemyLeftInWave()) {
			return false;
		}
		return enemyManager.getEnemies().size() == 0;
	}
	/*
	 * spawn enemies in the wave.
	 */
	private void spawnEnemy() {
		enemyManager.spawnEnemy(this.getWaveManager().getNextEnemy(), this.getWaveManager().getWaveIndex());
	}
	/*
	 * check if it is the time to spawn new enemy.
	 */
	private boolean isTimeForNewEnemy() {
		if(this.getWaveManager().isTimeForNewEnemy()) {
			if(this.getWaveManager().isThereEnemyLeftInWave()) {
				return true;
			}
		}
		return false;
	}
	/*
	 * set selected tower.
	 */
	public void setSelectedTower(Tower selectedTower) {
		// TODO Auto-generated method stub
		this.selectedTower = selectedTower;
	}

	@Override
	/*
	 * draw everything on the screen.
	 */
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
	/*
	 * draw highlight border.
	 */
	private void drawHighlight(GraphicsContext gc) {
		if(mouseY >= 640) {
			return ;
		}
		gc.setStroke(Color.PINK);
		gc.strokeRect(mouseX, mouseY, 32, 32);
	}
	/* 
	 * draw selected tower.
	 */
	private void drawSelectedTower(GraphicsContext gc) {
		if(selectedTower == null) {
			return ;
		}
		gc.drawImage(towerManager.getTowerImages()[selectedTower.getTowerType()][0], mouseX, mouseY);
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
	 * get tile type based on the coordinate.
	 */
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
	/*
	 * handle mouse click 
	 */
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
			Tower t = getTowerAt(32 * xIndex, 32 * yIndex);
			if(t != null) {
				if(t.getTowerType() != selectedTower.getTowerType()) {
					return ;
				}
				else if(t.getTier() >= 2) {
					return ;
				}
				t.upgradeTower();
				soundPlayer.lvlUp();
				decreaseGold(selectedTower.getCost());
			}
			else {
				towerManager.addTower(selectedTower, xIndex, yIndex);
				decreaseGold(selectedTower.getCost());
			}
			selectedTower = null;
		}
		else {
			int xIndex = mouseX / 32;
			int yIndex = mouseY / 32;
			Tower tower = getTowerAt(32 * xIndex, 32 * yIndex);
			actionBar.displayTower(tower);
		}
	}
	/*
	 * decrease player's gold.
	 */
	private void decreaseGold(int towerCost) {
		actionBar.decreaseGold(towerCost);
	}
	/*
	 * get a tower at the specified coordinate.
	 */
	private Tower getTowerAt(int x, int y) {
		// TODO Auto-generated method stub
		return towerManager.getTowerAt(x, y);
	}
	/*
	 * check if the tile is grass type.
	 */
	private boolean isTileGrass(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}

	@Override
	/*
	 * handle mouse move.
	 */
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
	/* 
	 * handle moude pressed.
	 */
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	/*
	 * handle mouse release.
	 */
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		if(y >= 640) {
			actionBar.mouseReleased(x, y);
		}
	}

	@Override
	/*
	 * handle mouse drag.
	 */
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
	}
	/*
	 * getter for tileManager.
	 */
	public TileManager getTileManager() {
		return tileManager;
	}
	/*
	 * setter for lvl.
	 */
	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	@Override
	/*
	 * handle mouse right click.
	 */
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * handle keypress.
	 */
	public void keyPressed(KeyCode keyCode) {
		if(keyCode == KeyCode.ESCAPE) {
			setSelectedTower(null);
		}
	}
	/*
	 * make the tower shoot enemy.
	 */
	public void shootEnemy(Tower tower, Enemy enemy) {
		tower.attack(projectileManager, enemy);
	}
	/*
	 * getter for goldTick.
	 */
	public int getGoldTick() {
		return goldTick;
	}
	/*
	 * getter for towerManager.
	 */
	public TowerManager getTowerManager() {
		return towerManager;
	}
	/*
	 * getter for enemyManager.
	 */
	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
	/*
	 * getter for wave Manager.
	 */
	public WaveManager getWaveManager() {
		return waveManager;
	}
	/*
	 * getter for soundPlayer.
	 */
	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}
	/*
	 * add gold for player.
	 */
	public void rewardPlayer(int enemyType) {
		actionBar.addGold(Constants.Enemies.getConstantReward(enemyType));
	}
	/*
	 * check if the game is paused.
	 */
	public boolean isGamePaused() {
		return gamePaused;
	}
	/*
	 * set the pause status of the game.
	 */
	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	/*
	 * remove one life from the player.
	 */
	public void removeOneLife() {
		actionBar.removeOneLife();
	}
	/*
	 * reset everything.
	 */
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
