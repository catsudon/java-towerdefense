package managers;

import static utilities.Constants.Direction.*;
import static utilities.Constants.Enemies.*;
import static utilities.Constants.Tiles.*;

import java.util.ArrayList;

import entity.enemy.Bat;
import entity.enemy.Enemy;
import entity.enemy.Knight;
import entity.enemy.Orc;
import entity.enemy.Wolf;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objects.PathPoint;
import scenes.Playing;
import sharedObject.RenderableHolder;
import utilities.ImageFix;

public class EnemyManager {

	@SuppressWarnings("unused")
	private Playing playing;
	private Image[] enemyImages;

	private ArrayList<Enemy> enemies = new ArrayList<>();
	private PathPoint start, end;
	
	private Image slowEffectImage;

	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		this.start = start;
		this.end = end;

		this.playing = playing;
		enemyImages = new Image[4];
		
		loadEffectImages();
		loadEnemyImages();
	}

	private void loadEffectImages() {
		slowEffectImage = ImageFix.getSubImage(RenderableHolder.mapSprite, 32 * 9, 32 * 2, 32, 32);
	}

	private void loadEnemyImages() {
		Image atlas = RenderableHolder.mapSprite;
		for (int i = 0; i < enemyImages.length; ++i) {
			enemyImages[i] = ImageFix.getSubImage(atlas, 32 * i, 32 * 1, 32, 32);
		}
	}

	public void update() {
		for (Enemy enemy : enemies) {
			if (!enemy.isAlive()) {
				continue;
			}
			updateEnemyMove(enemy);
		}
	}

	public void updateEnemyMove(Enemy e) {
		if (e.getLastDir() == -1) {
			setNewDirectionAndMove(e);
		}

		int newX = (int) (e.getX() + getSpeedXandWidth(e.getLastDir(), e.getEnemyType()));
		int newY = (int) (e.getY() + getSpeedYandHeight(e.getLastDir(), e.getEnemyType()));

		if (getTileType(newX, newY) == ROAD_TILE) {
			e.move(getConstantSpeed(e.getEnemyType()), e.getLastDir());
		} 
		else if (isAtEnd(e)) {
			e.kill();
			playing.removeOneLife();
		}
		else {
			setNewDirectionAndMove(e);
		}
	}

	private boolean isAtEnd(Enemy enemy) {
		// TODO Auto-generated method stub
		if (enemy.getX() == 32 * end.getxIndex() && enemy.getY() == 32 * end.getyIndex()) {
			return true;
		}
		return false;
	}

	private void setNewDirectionAndMove(Enemy enemy) {
		int dir = enemy.getLastDir();

		// move into the current tile 100%

		int xIndex = (int) (enemy.getX() / 32);
		int yIndex = (int) (enemy.getY() / 32);

		fixEnemyOffsetTile(enemy, dir, xIndex, yIndex);

		if (isAtEnd(enemy)) {
			return;
		}

		// Not walk back
		if (dir == LEFT || dir == RIGHT) {
			int newY = (int) (enemy.getY() + getSpeedYandHeight(UP, enemy.getEnemyType()));
			if (getTileType((int) enemy.getX(), newY) == ROAD_TILE) {
				enemy.move(getConstantSpeed(enemy.getEnemyType()), UP);
			} else {
				enemy.move(getConstantSpeed(enemy.getEnemyType()), DOWN);
			}
		} else {
			int newX = (int) (enemy.getX() + getSpeedXandWidth(RIGHT, enemy.getEnemyType()));
			if (getTileType(newX, (int) enemy.getY()) == ROAD_TILE) {
				enemy.move(getConstantSpeed(enemy.getEnemyType()), RIGHT);
			} else {
				enemy.move(getConstantSpeed(enemy.getEnemyType()), LEFT);
			}
		}
	}

	private void fixEnemyOffsetTile(Enemy enemy, int dir, int xIndex, int yIndex) {
		switch (dir) {
//		 Left and Up not cause problems
//			case LEFT:
//				if(xIndex > 0) {
//					xIndex--;
//				}
//				break;	
//		case UP:
//			if (yIndex > 0) {
//				yIndex--;
//			}
//			break;

		case RIGHT:
			if (xIndex < 19) {
				xIndex++;
			}
			break;

		case DOWN:
			if (yIndex > 0) {
				yIndex++;
			}
			break;
		}
		enemy.setPos(32 * xIndex, 32 * yIndex);
	}

	private int getTileType(int x, int y) {
		return playing.getTileType(x, y);
	}

	private float getSpeedXandWidth(int dir, int enemyType) {
		// TODO Auto-generated method stub
		if (dir == LEFT) {
			return -getConstantSpeed(enemyType);
		}
		// Dealing with sprite offset
		else if (dir == RIGHT) {
			return getConstantSpeed(enemyType) + 32;
		}
		return 0;
	}

	private float getSpeedYandHeight(int dir, int enemyType) {
		// TODO Auto-generated method stub
		if (dir == UP) {
			return -getConstantSpeed(enemyType);
		}
		// Dealing with sprite offset
		else if (dir == DOWN) {
			return getConstantSpeed(enemyType) + 32;
		}
		return 0;
	}

	public void addEnemy(int enemyType, int reIncarnationCount) {

		int x = 32 * start.getxIndex();
		int y = 32 * start.getyIndex();

		switch (enemyType) {
		case ORC:
			enemies.add(new Orc(x, y, 0, reIncarnationCount));
			break;
		case BAT:
			enemies.add(new Bat(x, y, 0, reIncarnationCount));
			break;
		case KNIGHT:
			enemies.add(new Knight(x, y, 0, reIncarnationCount));
			break;
		case WOLF:
			enemies.add(new Wolf(x, y, 0, reIncarnationCount));
			break;
		}
	}

	public void draw(GraphicsContext gc) {
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (!enemies.get(i).isAlive()) {
				enemies.remove(i);
			}
		}
		for (Enemy e : enemies) {
			drawEnemy(e, gc);
			drawHealthBar(e, gc);
			drawEffects(e, gc);
		}
	}
	
	private void drawEffects(Enemy e, GraphicsContext gc) {
		if(e.isSlowed()) {
			gc.drawImage(slowEffectImage, (int)e.getX(), (int)e.getY());
		}
	}

	private void drawHealthBar(Enemy enemy, GraphicsContext gc) {
		// Full Health Bar
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect((int) enemy.getX() + 16 - (enemy.getBarWidth() / 2), (int) enemy.getY() - 8, enemy.getBarWidth(),
				3);

		// Health Bar
		gc.setFill(Color.RED);
		gc.fillRect((int) enemy.getX() + 16 - (enemy.getBarWidth() / 2), (int) enemy.getY() - 8,
				(int) (getHealthPercentage(enemy) * enemy.getBarWidth()), 3);
	}

	private float getHealthPercentage(Enemy enemy) {
		return (float) enemy.getHealth() / enemy.getMaxHealth();
	}

	private void drawEnemy(Enemy enemy, GraphicsContext gc) {
		gc.drawImage(enemyImages[enemy.getEnemyType()], (int) enemy.getX(), (int) enemy.getY());
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void spawnEnemy(int nextEnemy, int reincarnationCount) {
		addEnemy(nextEnemy, reincarnationCount);
	}

	public int getAmountOfAliveEnemies() {
		return getEnemies().size();
	}

	public void reset() {
		enemies.clear();
	}
}
