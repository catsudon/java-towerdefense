package managers;

import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.tower.Tower;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameState;
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

		for (int i = 0; i < 3; i++) {
			projectileImages[i] = Render.getSubImage(atlas, 32 * (7 + i), 32 * 1, 32, 32);
		}
	}

	public void mawarikougeki(Tower tower) {

		int type = getProjectileType(tower);

		float xSpeed = (float) (0.5 * help.Constants.Projectiles.getSpeed(type));
		float ySpeed = (float) (0.5 * help.Constants.Projectiles.getSpeed(type));

		for (int i = -50; i <= 50; ++i) {
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, i, (float) Math.sin(i), 0,
					tower.getAtk(), projectile_ID++, type));
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, (float) Math.sin(i), i, 0,
					tower.getAtk(), projectile_ID++, type));
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, -i, (float) Math.sin(i), 0,
					tower.getAtk(), projectile_ID++, type));
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, -(float) Math.sin(i), i, 0,
					tower.getAtk(), projectile_ID++, type));
		}

	}

	public void newProjectile(Tower tower, Enemy enemy) {
		if (!enemy.isAlive()) {
			return;
		}

		int type = getProjectileType(tower);

		int xDiff = (int) (tower.getX() - enemy.getX());
		int yDiff = (int) (tower.getY() - enemy.getY());
		int totalDist = Math.abs(xDiff) + Math.abs(yDiff);

		// Percent
		float xPer = (float) Math.abs(xDiff) / totalDist;
		float yPer = 1.0f - xPer;

		float xSpeed = xPer * help.Constants.Projectiles.getSpeed(type);
		float ySpeed = yPer * help.Constants.Projectiles.getSpeed(type);

		if (tower.getX() > enemy.getX()) {
			xSpeed *= -1;
		}
		if (tower.getY() > enemy.getY()) {
			ySpeed *= -1;
		}

		float arctan = (float) Math.atan((float) yDiff / xDiff);
		float rotationAngle = (float) Math.toDegrees(arctan);

		if (xDiff < 0) {
			rotationAngle += 180;
		}

		projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, xSpeed, ySpeed, rotationAngle,
				tower.getAtk(), projectile_ID++, type));
	}

	public void update() {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(50);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						for (Projectile projectile : projectiles) {
							if (projectile.isActive()) {
								projectile.move();
								if (isProjectileHittingEnemy(projectile)) {
									projectile.setActive(false);
								}
							}
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();

	}

	private boolean isProjectileHittingEnemy(Projectile projectile) {
		for (Enemy enemy : playing.getEnemyManager().getEnemies()) {
			if (enemy.getBounds().contains(projectile.getPos())) {
				enemy.hurt(projectile.getDamage());
				if (!enemy.isAlive()) {
					playing.getEnemyManager().getEnemies().remove(enemy);
					playing.getEnemyManager().addEnemy(enemy.getEnemyType(), enemy.getReincarnateLeft() - 1);
					if (enemy.getReincarnateLeft() % 10 == 0)
						playing.getEnemyManager().addEnemy(enemy.getEnemyType(), enemy.getReincarnateLeft() - 8);

				}
				return true;
			}
		}
		return false;
	}

	public void draw(GraphicsContext gc) {

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(50);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						for (int i = projectiles.size() - 1; i >= 0; i--) {
							if (!projectiles.get(i).isActive()) {
								projectiles.remove(i);
							}
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();

		for (Projectile projectile : projectiles) {
			if (!projectile.isActive()) {
				continue;
			}

			gc.translate(projectile.getPos().getX(), projectile.getPos().getY());
			gc.rotate(projectile.getRotation());

			// -16, -16 is to use the center of the image to be axis of rotation
			gc.drawImage(projectileImages[projectile.getProjectileType()], -16, -16);

			// Restore
			gc.rotate(-projectile.getRotation()); // Can't swap order with translate
			gc.translate(-projectile.getPos().getX(), -projectile.getPos().getY());
		}
	}

	private int getProjectileType(Tower tower) {
		switch (tower.getTowerType()) {
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
