package objects;

import javafx.geometry.Point2D;

public class Projectile {
	private Point2D pos;
	private int id, projectileType;
	private boolean active = true;
	
	public Projectile(float x, float y, int id, int projectileType) {
		pos = new Point2D(x, y);
		this.id = id;
		this.projectileType = projectileType;
	}
	
	public void move(float x, float y) {
		pos.add(x, y);
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
	
	
}
