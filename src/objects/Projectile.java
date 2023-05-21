package objects;

import static utilities.Constants.Projectiles.CUPCAKE;

import entity.enemy.Enemy;
import javafx.geometry.Point2D;

public class Projectile {
	/*
	 * position of projectile.
	 */
	private Point2D pos;
	/*
	 * projectile id.
	 */
	private int id;
	/*
	 * projectile type.
	 */
	private int projectileType;
	/*
	 * projectile active status.
	 */
	private boolean active;
	/*
	 * projectile speed in x axis.
	 */
	private float xSpeed;
	/*
	 * projectile speed in y axis.
	 */
	private float ySpeed;
	/*
	 * projectile rotation angle.
	 */
	private float rotationAngle;
	/*
	 * projectile damage.
	 */
	private int damage;
	/*
	 * enemy the projectile is aiming.
	 */
	private Enemy enemy;
	/*
	 * last enemy projectile hit.
	 */
	private Enemy lastEnemy;
	/*
	 * speed multiplier for projectile speed.
	 */
	private float speedMultiplier;
	
	/*
	 * intialize fields for projectile with rotation angle.
	 */
	public Projectile(float x, float y, float xSpeed, float ySpeed, float rotationAngle, int damage, int id, int projectileType) {
		pos = new Point2D(x, y);
		this.xSpeed = xSpeed * 3;
		this.ySpeed = ySpeed * 3;
		this.rotationAngle = rotationAngle;
		this.damage = damage;
		this.id = id;
		this.active = true;
		this.projectileType = projectileType;
		this.enemy = null;
		this.speedMultiplier = 1;
	}
	/*
	 * initialize field for normal projectile.
	 */
	public Projectile(float x, float y, int damage, int id, int projectileType, Enemy enemy) {
		pos = new Point2D(x, y);
		this.damage = damage;
		this.id = id;
		this.active = true;
		this.projectileType = projectileType;
		this.enemy = enemy;
		this.speedMultiplier = 1;
	}
	/*
	 * move projectile.
	 */
	public void move() {
		Thread thread = new Thread(() -> {
	        if(enemy != null && !enemy.isAlive()) {
	            enemy = null;
	            return ;
	        }
	        if(enemy != null) {
	            int xDiff = (int) (this.getPos().getX() - (enemy.getX() + 16));
	            int yDiff = (int) (this.getPos().getY() - (enemy.getY() + 16));
	            int totalDist = Math.abs(xDiff) + Math.abs(yDiff);
	            if(Math.hypot(xDiff, yDiff) <= 16) {
	                this.enemy = null;
	                return ;
	            }
	            
	            // Percent
	            float xPer = (float) Math.abs(xDiff) / (totalDist + 0.001f);
	            float yPer = 1.0f - xPer;
	
	            this.xSpeed = xPer * utilities.Constants.Projectiles.getConstantSpeed(projectileType);
	            this.ySpeed = yPer * utilities.Constants.Projectiles.getConstantSpeed(projectileType);
	            
	            if (this.getPos().getX() > enemy.getX()) {
	                this.xSpeed *= -1;
	            }
	            if (this.getPos().getY() > enemy.getY()) {
	                this.ySpeed *= -1;
	            }
	            
	            this.rotationAngle = 0;
	            
	            if(projectileType == CUPCAKE) {
	            	enemy = null;
	            }
	        }
	        if(xSpeed == 0 && ySpeed == 0) {
	        	this.active = false;
	        }
	        this.pos = this.pos.add(xSpeed * speedMultiplier, ySpeed * speedMultiplier);
		});
		thread.start();
    }
	/*
	 * getter for projectile pos.
	 */
	public Point2D getPos() {
		return pos;
	}
	/*
	 * getter for projectile id.
	 */
	public int getId() {
		return id;
	}
	/*
	 * getter for projectile type.
	 */
	public int getProjectileType() {
		return projectileType;
	}
	/*
	 * getter for projectile active status.
	 */
	public boolean isActive() {
		return active;
	}
	/*
	 * setter for projectile active status.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/*
	 * getter for projectile damage.
	 */
	public int getDamage() {
		return damage;
	}
	/*
	 * getter for projectile rotation angle.
	 */
	public float getRotationAngle() {
		return rotationAngle;
	}
	/*
	 * set the enemy projectile is aiming.
	 */
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	/*
	 * getter for projectile x speed.
	 */
	public float getxSpeed() {
		return xSpeed;
	}
	/*
	 * setter for projectile x speed.
	 */
	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}
	/*
	 * getter for projectile y speed.
	 */
	public float getySpeed() {
		return ySpeed;
	}
	/*
	 * setter for projectile y speed.
	 */
	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	/*
	 * setter for speed multiplier
	 */
	public void setSpeedMultiplier(float speed) {
		this.speedMultiplier = speed;
	}
	/*
	 * get the last enemy projectile hit.
	 */
	public Enemy getLastEnemy() {
		return lastEnemy;
	}
	/*
	 * set the last enemy projectile hit.
	 */
	public void setLastEnemy(Enemy lastEnemy) {
		this.lastEnemy = lastEnemy;
	}
}
