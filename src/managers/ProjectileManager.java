package managers;

import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.tower.Tower;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Render;
import objects.Projectile;
import scenes.Playing;
import sharedObject.RenderableHolder;

import static help.Constants.Towers.*;
import static help.Constants.Projectiles.*;

public class ProjectileManager {

	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private Image[] projectileImages;
	
	private int projectile_ID = 0;
	
	public ProjectileManager(Playing playing) {
		this.playing = playing;
		this.projectile_ID = 0;
		
		importImages();
	}
	
	private void importImages() {
		Image atlas = RenderableHolder.mapSprite;
		projectileImages = new Image[3];
		
		for(int i = 0; i < 3; i++) {
			projectileImages[i] = Render.getSubImage(atlas, 32 * (7 + i), 32 * 1, 32, 32);
		}
	}
	
	public void newProjectile(Tower tower, Enemy enemy) {
		if(!enemy.isAlive()) {
			return ;
		}
		
		int type = getProjectileType(tower);
		
		int xDist = (int)Math.abs(tower.getX() - enemy.getX());
		int yDist = (int)Math.abs(tower.getY() - enemy.getY());
		int totalDist = xDist + yDist;
		
		// Percent
		float xPer = (float) xDist / totalDist;
		float yPer = 1.0f - xPer;
		
		float xSpeed = xPer * help.Constants.Projectiles.getSpeed(type);
		float ySpeed = yPer * help.Constants.Projectiles.getSpeed(type);
		
		if(tower.getX() > enemy.getX()) {
			xSpeed *= -1;
		}
		if(tower.getY() > enemy.getY()) {
			ySpeed *= -1;
		}
		
		projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, xSpeed, ySpeed, tower.getAtk(), projectile_ID++, type));
	}
	
	
	public void update() {
		for(Projectile projectile : projectiles) {
			if(projectile.isActive()) {
				projectile.move();
				if(isProjectileHittingEnemy(projectile)) {
					projectile.setActive(false);
				}
			}
		}
	}
	
	private boolean isProjectileHittingEnemy(Projectile projectile) {
		for(Enemy enemy : playing.getEnemyManager().getEnemies()) {
			if(enemy.getBounds().contains(projectile.getPos())) {
				enemy.hurt(projectile.getDamage());
				return true;
			}
		}
		return false;
	}

	public void draw(GraphicsContext gc) {
		for(int i = projectiles.size() - 1; i >= 0; i--) {
			if(!projectiles.get(i).isActive()) {
				projectiles.remove(i);
			}
		}
		for(Projectile projectile : projectiles) {
			gc.drawImage(projectileImages[projectile.getProjectileType()], (int)projectile.getPos().getX(), (int)projectile.getPos().getY());
		}
	}
	
	private int getProjectileType(Tower tower) {
		switch(tower.getTowerType()) {
			case ARCHER:
				return ARROW;
			case CANNON:
				return BOMB;
			case WIZARD:
				return CHAINS;
		}
		return 0;
	}
}
