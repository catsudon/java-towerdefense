package entity.enemy;

import javafx.scene.shape.Rectangle;
import main.Game;
import managers.EnemyManager;
import scenes.Playing;

import static help.Constants.Direction.*;
import static help.Constants.Enemies.BAT;
import static help.Constants.Enemies.KNIGHT;
import static help.Constants.Enemies.ORC;
import static help.Constants.Enemies.WOLF;

import help.Constants;

public abstract class Enemy {
	private float x, y;
	private Rectangle bounds;
	private int health;
	private int ID;
	private int enemyType;
	private int lastDir;
	private int reincarnateLeft;

	private int maxHealth;
	private int barWidth;

	private boolean alive;

	public Enemy(float x, float y, int ID, int enemyType, int reincarnateCount) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		this.reincarnateLeft = reincarnateCount;
		this.alive = true;

		this.maxHealth = Constants.Enemies.getStartHealth(enemyType);
		this.health = this.maxHealth;
		this.barWidth = 20;

		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDir = -1;
	}

	public void hurt(int damage) {
		this.health -= damage;
		if (this.health <= 0) {
			alive = false;
		}
	}

	public void move(float speed, int dir) {
		lastDir = dir;
		switch (dir) {

		case LEFT:
			this.x -= speed;
			break;

		case RIGHT:
			this.x += speed;
			break;

		case UP:
			this.y -= speed;
			break;

		case DOWN:
			this.y += speed;
			break;
		}

		updateHitbox();
	}

	private void updateHitbox() {
		bounds.setX((int) x);
		bounds.setY((int) y);
	}

	public float getHealthBarFloat() {
		return (float) health / maxHealth;
	}

	public void setPos(int x, int y) {
		// Don't use this one for move, this is for position fix.
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public int getLastDir() {
		return lastDir;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getReincarnateLeft() {
		return reincarnateLeft;
	}

	public int getBarWidth() {
		return barWidth;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isAlive() {
		return alive;
	}
}
