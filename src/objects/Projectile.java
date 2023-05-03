package objects;

import javafx.geometry.Point2D;

public class Projectile {
	private Point2D pos;
	private int id, projectileType;
	private boolean active;
	private float xSpeed, ySpeed;
	private float rotation;
	private int damage;
	
	public Projectile(float x, float y, float xSpeed, float ySpeed, float rotation, int damage, int id, int projectileType) {
		pos = new Point2D(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.rotation = rotation;
		this.damage = damage;
		this.id = id;
		this.active = true;
		this.projectileType = projectileType;
	}
	
	public void move() {
		pos = pos.add(xSpeed, ySpeed);
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

	public float getRotation() {
		return rotation;
	}
	
	
}
