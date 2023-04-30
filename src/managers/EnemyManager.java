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
	private float speed = 0.5f;
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
			updateEnemyMove(enemy);
		}
	}
	
	public void updateEnemyMove(Enemy enemy) {
		if(enemy.getLastDir() == -1) {
			setNewDirectionAndMove(enemy);
		}
		
		int newX = (int)(enemy.getX() + getSpeedXandWidth(enemy.getLastDir()));
		int newY = (int)(enemy.getY() + getSpeedYandHeight(enemy.getLastDir()));
		
		if(getTileType(newX, newY) == ROAD_TILE) {
			enemy.move(speed, enemy.getLastDir());
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
			int newY = (int)(enemy.getY() + getSpeedYandHeight(UP));
			if(getTileType((int)enemy.getX(), newY) == ROAD_TILE) {
				enemy.move(speed, UP);
			}
			else {
				enemy.move(speed, DOWN);
			}
		}
		else {
			int newX = (int)(enemy.getX() + getSpeedXandWidth(RIGHT));
			if(getTileType(newX, (int)enemy.getY()) == ROAD_TILE) {
				enemy.move(speed, RIGHT);
			}
			else {
				enemy.move(speed, LEFT);
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

	private float getSpeedXandWidth(int dir) {
		// TODO Auto-generated method stub
		if(dir == LEFT) {
			return -speed;
		}
		// Dealing with sprite offset
		else if(dir == RIGHT) {
			return speed + 32;
		}
		return 0;
	}
	
	private float getSpeedYandHeight(int dir) {
		// TODO Auto-generated method stub
		if(dir == UP) {
			return -speed;
		}
		// Dealing with sprite offset
		else if(dir == DOWN) {
			return speed + 32;
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
			drawEnemy(enemy, gc);
		}
	}
	
	private void drawEnemy(Enemy enemy, GraphicsContext gc) {
		gc.drawImage(enemyImages[enemy.getEnemyType()], (int)enemy.getX(), (int)enemy.getY());
	}
}
