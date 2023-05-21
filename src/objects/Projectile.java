package objects;

import static utilities.Constants.Projectiles.CROISSANT;
import static utilities.Constants.Projectiles.CUPCAKE;

import entity.enemy.Enemy;
import javafx.geometry.Point2D;

public class Projectile {
	private Point2D pos;
	private int id, projectileType;
	private boolean active;
	private float xSpeed, ySpeed;
	private float rotationAngle;
	private int damage;
	private Enemy enemy;
	private Enemy lastEnemy;
	
	private float speedMultiplier;
	
	public Projectile(float x, float y, float xSpeed, float ySpeed, float rotationAngle, int damage, int id, int projectileType) {
		pos = new Point2D(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.rotationAngle = rotationAngle;
		this.damage = damage;
		this.id = id;
		this.active = true;
		this.projectileType = projectileType;
		this.enemy = null;
		this.speedMultiplier = 1;
	}
	
	public Projectile(float x, float y, int damage, int id, int projectileType, Enemy enemy) {
		pos = new Point2D(x, y);
		this.damage = damage;
		this.id = id;
		this.active = true;
		this.projectileType = projectileType;
		this.enemy = enemy;
		this.speedMultiplier = 1;
	}
	
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
	
	            if(projectileType == CROISSANT) {
	                float arctan = (float) Math.atan((float) yDiff / xDiff);
	                this.rotationAngle = (float) Math.toDegrees(arctan);
	                if (xDiff < 0) {
	                    this.rotationAngle += 180;
	                }
	            }
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

	public Point2D getPos() {
		return pos;
	}

	public int getId() {
		return id;
	}

	public int getProjectileType() {
		return projectileType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getDamage() {
		return damage;
	}

	public float getRotationAngle() {
		return rotationAngle;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public void setSpeedMultiplier(float speed) {
		this.speedMultiplier = speed;
	}

	public Enemy getLastEnemy() {
		return lastEnemy;
	}

	public void setLastEnemy(Enemy lastEnemy) {
		this.lastEnemy = lastEnemy;
	}
}
