package managers;

import java.util.ArrayList;

import static help.Constants.Direction.*;
import static help.Constants.Enemies.*;

import entity.enemy.Bat;
import entity.enemy.Enemy;
import entity.enemy.Knight;
import entity.enemy.Orc;
import entity.enemy.Wolf;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Render;
import objects.PathPoint;
import scenes.Playing;
import sharedObject.RenderableHolder;

import static help.Constants.Tiles.*;

public class EnemyManager {
	
	@SuppressWarnings("unused")
	private Playing playing;
	private Image[] enemyImages;
	
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private PathPoint start, end;
	
	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		this.start = start;
		this.end = end;
		
		this.playing = playing;
		enemyImages = new Image[4];
		addEnemy(ORC);
		addEnemy(BAT);
		addEnemy(KNIGHT);
		addEnemy(WOLF);
		loadEnemyImages();
	}
	
	private void loadEnemyImages() {
		Image atlas = RenderableHolder.mapSprite;
		for(int i = 0; i < 4; i++) {
			enemyImages[i] = Render.getSubImage(atlas, 32 * i, 32 * 1, 32, 32);
		}
	}
	
	public void update() {
		for(Enemy enemy : enemies) {
			if(!enemy.isAlive()) {
				continue;
			}
			updateEnemyMove(enemy);
		}
	}
	
	public void updateEnemyMove(Enemy enemy) {
		if(enemy.getLastDir() == -1) {
			setNewDirectionAndMove(enemy);
		}
		
		int newX = (int)(enemy.getX() + getSpeedXandWidth(enemy.getLastDir(), enemy.getEnemyType()));
		int newY = (int)(enemy.getY() + getSpeedYandHeight(enemy.getLastDir(), enemy.getEnemyType()));
		
		if(getTileType(newX, newY) == ROAD_TILE) {
			enemy.move(getSpeed(enemy.getEnemyType()), enemy.getLastDir());
		}
		else if (isAtEnd(enemy)) {
			System.out.println("Lives Lost!");
		}
		else {
			setNewDirectionAndMove(enemy);
		}
	}
	
	private boolean isAtEnd(Enemy enemy) {
		// TODO Auto-generated method stub
		if(enemy.getX() == 32 * end.getxIndex() && enemy.getY() == 32 * end.getyIndex()) {
			return true;
		}
		return false;
	}

	private void setNewDirectionAndMove(Enemy enemy) {
		int dir = enemy.getLastDir();
		
		// move into the current tile 100%
		
		int xIndex = (int)(enemy.getX() / 32);
		int yIndex = (int)(enemy.getY() / 32);
		
		fixEnemyOffsetTile(enemy, dir, xIndex, yIndex);
		
		if(isAtEnd(enemy)) {
			return ;
		}
		
		// Not walk back
		if(dir == LEFT || dir == RIGHT) {
			int newY = (int)(enemy.getY() + getSpeedYandHeight(UP, enemy.getEnemyType()));
			if(getTileType((int)enemy.getX(), newY) == ROAD_TILE) {
				enemy.move(getSpeed(enemy.getEnemyType()), UP);
			}
			else {
				enemy.move(getSpeed(enemy.getEnemyType()), DOWN);
			}
		}
		else {
			int newX = (int)(enemy.getX() + getSpeedXandWidth(RIGHT, enemy.getEnemyType()));
			if(getTileType(newX, (int)enemy.getY()) == ROAD_TILE) {
				enemy.move(getSpeed(enemy.getEnemyType()), RIGHT);
			}
			else {
				enemy.move(getSpeed(enemy.getEnemyType()), LEFT);
			}
		}
	}

	private void fixEnemyOffsetTile(Enemy enemy, int dir, int xIndex, int yIndex) {
		switch(dir) {
			// Left and Up not cause problems
//			case LEFT:
//				if(xIndex > 0) {
//					xIndex--;
//				}
//				break;	
//			case UP:
//				if(yIndex > 0) {
//					yIndex--;
//				}
//			break;
				
			case RIGHT:
				if(xIndex < 19) {
					xIndex++;
				}
				break;
				
			case DOWN:
				if(yIndex > 0) {
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
		if(dir == LEFT) {
			return -getSpeed(enemyType);
		}
		// Dealing with sprite offset
		else if(dir == RIGHT) {
			return getSpeed(enemyType) + 32;
		}
		return 0;
	}
	
	private float getSpeedYandHeight(int dir, int enemyType) {
		// TODO Auto-generated method stub
		if(dir == UP) {
			return -getSpeed(enemyType);
		}
		// Dealing with sprite offset
		else if(dir == DOWN) {
			return getSpeed(enemyType) + 32;
		}
		return 0;
	}

	public void addEnemy(int enemyType) {
		
		int x = 32 * start.getxIndex();
		int y = 32 * start.getyIndex();
		
		switch(enemyType) {
			case ORC:
				enemies.add(new Orc(x, y, 0));
			break;
			case BAT:
				enemies.add(new Bat(x, y, 0));
			break;
			case KNIGHT:
				enemies.add(new Knight(x, y, 0));
			break;
			case WOLF:
				enemies.add(new Wolf(x, y, 0));
			break;
		}
	}
	
	public void draw(GraphicsContext gc) {
		for(Enemy enemy : enemies) {
			if(!enemy.isAlive()) {
				continue;
			}
			drawEnemy(enemy, gc);
			drawHealthBar(enemy, gc);
		}
	}
	
	private void drawHealthBar(Enemy enemy, GraphicsContext gc) {
		// Full Health Bar
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect((int)enemy.getX() + 16 - (enemy.getBarWidth() / 2), (int)enemy.getY() - 8, enemy.getBarWidth(), 3);
		
		// Health Bar
		gc.setFill(Color.RED);
		gc.fillRect((int)enemy.getX() + 16 - (enemy.getBarWidth() / 2), (int)enemy.getY() - 8, (int)(getHealthPercentage(enemy) * enemy.getBarWidth()), 3);
	}
	
	private float getHealthPercentage(Enemy enemy) {
		return (float)enemy.getHealth() / enemy.getMaxHealth();
	}

	private void drawEnemy(Enemy enemy, GraphicsContext gc) {
		gc.drawImage(enemyImages[enemy.getEnemyType()], (int)enemy.getX(), (int)enemy.getY());
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}
