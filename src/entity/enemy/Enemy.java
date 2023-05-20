package entity.enemy;

import static utilities.Constants.Direction.*;

import javafx.scene.shape.Rectangle;
import utilities.Constants;

public abstract class Enemy {

	private float x, y;
	private int ID;
	private int enemyType;
	private int reincarnateLeft;
	private boolean alive;
	private int maxHealth;
	private int health;
	
	private int barWidth;
	private Rectangle bounds;
	private int lastDir;
	
	protected int slowTick = 2 * 60;
	protected int slowTickLimit = 2 * 60;

	public Enemy(float x, float y, int ID, int enemyType, int reincarnateCount) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		this.reincarnateLeft = reincarnateCount;
		this.alive = true;
		this.maxHealth = Constants.Enemies.getConstantStartHealth(enemyType);
		this.health = this.maxHealth;
		this.barWidth = 20;
		this.bounds = new Rectangle((int) x, (int) y, 32, 32);
		this.lastDir = -1;
	}

	public void hurt(int damage) {
		this.health -= damage;
		if (this.health <= 0) {
			alive = false;
		}
	}

	public void move(float speed, int dir) {
		this.lastDir = dir;
		
		if(isSlowed()) {
			slowTick++;
			speed *= 0.5f;
		}
		
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
		this.bounds.setX((int)x);
		this.bounds.setY((int)y);
	}

	public void slow() {
		this.slowTick = 0;
	}
	
	public void kill() {
		this.alive = false;
	}
	
	public void setPos(int x, int y) {
		// Don't use this one for move, this is for position fix.
		this.x = x;
		this.y = y;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}
	
	public float getHealthBarFloat() {
		return (float) health / maxHealth;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public int getReincarnateLeft() {
		return reincarnateLeft;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public int getBarWidth() {
		return barWidth;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getLastDir() {
		return lastDir;
	}

	public int getSlowTick() {
		return slowTick;
	}

	public int getSlowTickLimit() {
		return slowTickLimit;
	}
}
