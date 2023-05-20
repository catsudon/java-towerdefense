package managers;

import static utilities.Constants.Projectiles.*;
import static utilities.Constants.Towers.*;

import java.util.ArrayList;
import java.util.Random;

import entity.enemy.Enemy;
import entity.tower.Tower;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import objects.Projectile;
import scenes.Playing;
import sharedObject.RenderableHolder;
import utilities.ImageFix;

public class ProjectileManager {

	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private Image[] projectileImages;
	private Image[] explosionImages;

	private int projectile_ID = 0;

	public ProjectileManager(Playing playing) {
		this.playing = playing;
		this.projectile_ID = 0;

		importImages();
	}

	private void importImages() {
		Image atlas = RenderableHolder.mapSprite;
		projectileImages = new Image[5];

		for (int i = 0; i < 3; i++) {
			projectileImages[i] = ImageFix.getSubImage(atlas, 32 * (7 + i), 32 * 1, 32, 32);
		}
		
		projectileImages[3] = ImageFix.getSubImage(atlas, 32 * (7), 32 * 3, 32, 32);
		projectileImages[4] = ImageFix.getSubImage(atlas, 32 * (8), 32 * 3, 32, 32);

		explosionImages = new Image[7];
		
		for(int i = 0; i < 7; i++) {
			explosionImages[i] = ImageFix.getSubImage(atlas, 32 * i, 32 * 2, 32, 32);
		}
		
	}

	public void mawarikougeki(Tower tower) {

		int index;
		int[] typeList = {1,3,4};
		Random rand = new Random();

		for (int i = -50; i <= 50; i+=4) {
			index = typeList[rand.nextInt(3)];
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, i, (float) Math.sin(i), 0,
					tower.getAtk(), projectile_ID++, index));
			index = typeList[rand.nextInt(3)];
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, (float) Math.sin(i), i, 0,
					tower.getAtk(), projectile_ID++, index));
			index = typeList[rand.nextInt(3)];
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, -i, (float) Math.sin(i), 0,
					tower.getAtk(), projectile_ID++, index));
			index = typeList[rand.nextInt(3)];
			projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, -(float) Math.sin(i), i, 0,
					tower.getAtk(), projectile_ID++, index));
		}

	}

	public void newProjectile(Tower t, Enemy e) {
		if (!e.isAlive()) {
			return;
		}
		
		int type = getProjectileType(t);

		projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, t.getAtk(), projectile_ID++, type, e));
	}

	public void update() {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(50);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						for (Projectile p : projectiles) {
							if (p.isActive()) {
								p.move();
								if (isProjectileHittingEnemy(p)) {
									if(p.getProjectileType() == CUPCAKE) {
										explosions.add(new Explosion(p.getPos()));
										explodeOnEnemies(p);
									}
									
									if(p.getProjectileType() == MACARONS) {
										playing.getSoundPlayer().shoot();
									}
									
									if(p.getProjectileType() == CROISSANT) {
										playing.getSoundPlayer().whoosh();
										p.setEnemy(null);
									}
									else {
										p.setActive(false);
									}
								}
								else if(isProjectileOutOfBounds(p)) {
									p.setActive(false);
								}
							}
						}
						for(Explosion e : explosions) {
							e.update();
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();

	}
	
	protected boolean isProjectileOutOfBounds(Projectile p) {
		return !(p.getPos().getX() >= 0 && p.getPos().getX() < 640 && p.getPos().getY() >= 0 && p.getPos().getX() < 640);
	}

	private void explodeOnEnemies(Projectile p) {
		playing.getSoundPlayer().boom();
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(!e.isAlive()) {
				continue ;
			}
			float radius = 40.0f;
			float xDist = (float)Math.abs(p.getPos().getX() - e.getX());
			float yDist = (float)Math.abs(p.getPos().getY() - e.getY());
			
			float realDist = (float) Math.hypot(xDist, yDist);
			
			if(realDist <= radius) {
				e.hurt(p.getDamage());
			}
		}
	}

	private void drawExplosions(GraphicsContext gc) {
		for(int i = explosions.size() - 1; i >= 0; i--) {
			if(explosions.get(i).getExplosionIndex() >= 7) {
				explosions.remove(i);
			}
		}
		for(Explosion e : explosions) {
			if(e.getExplosionIndex() < 7) {
				gc.drawImage(explosionImages[e.getExplosionIndex()], (int)e.getPos().getX() - (32 / 2), (int)e.getPos().getY() - (32 / 2));
			}
		}
	}

	private boolean isProjectileHittingEnemy(Projectile p) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if(!e.isAlive()) {
				continue ;
			}
			if (e.getBounds().contains(p.getPos())) {
				e.hurt(p.getDamage());
				if (!e.isAlive()) {
					p.setEnemy(null);
					playing.rewardPlayer(e.getEnemyType());
					
					playing.getEnemyManager().getEnemies().remove(e);

				}
				else {
					if(p.getProjectileType() == MACARONS) {
						e.slow();
					}
				}
				return true;
			}
		}
		return false;
	}

	public void draw(GraphicsContext gc) {

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(10);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						for (int i = projectiles.size() - 1; i >= 0; i--) {
							Projectile p = projectiles.get(i);
							if (!p.isActive()) {
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

		for (Projectile p : projectiles) {
			if (!p.isActive()) {
				continue;
			}
			if (p.getProjectileType() == CROISSANT) {
				gc.translate(p.getPos().getX(), p.getPos().getY());
				gc.rotate(p.getRotationAngle());
	
				// -16, -16 is to use the center of the image to be axis of rotation
				gc.drawImage(projectileImages[p.getProjectileType()], -16, -16);
	
				// Restore
				gc.rotate(-p.getRotationAngle()); // Can't swap order with translate
				gc.translate(-p.getPos().getX(), -p.getPos().getY());
			}
			else {
				gc.drawImage(projectileImages[p.getProjectileType()], (int)p.getPos().getX() - (32 / 2), (int)p.getPos().getY() - (32 / 2));
			}
		}
		drawExplosions(gc);
	}

	private int getProjectileType(Tower tower) {
		switch (tower.getTowerType()) {
		case CHEF:
			return CROISSANT;
		case PRINCESS:
			return CUPCAKE;
		case OWNER:
			return MACARONS;
		}
		return 0;
	}
	
	public class Explosion {
		
		private Point2D pos;
		private int explosionTick = 0, explosionIndex = 0;
		
		public Explosion(Point2D pos) {
			this.pos = pos;
		}
		
		public void update() {
			explosionTick++;
			if(explosionTick >= 4) {
				explosionTick = 0;
				explosionIndex++;
			}
		}
		
		public Point2D getPos() {
			return pos;
		}
		
		public int getExplosionTick() {
			return explosionTick;
		}
		
		public int getExplosionIndex() {
			return explosionIndex;
		}
	}

	public void reset() {
		projectiles.clear();
		explosions.clear();

		projectile_ID = 0;
	}
}
