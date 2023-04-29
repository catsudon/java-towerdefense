package managers;

import java.util.ArrayList;

import entity.enemy.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Render;
import scenes.Playing;
import sharedObject.RenderableHolder;

public class EnemyManager {
	
	@SuppressWarnings("unused")
	private Playing playing;
	private Image[] enemyImages;
	
	private ArrayList<Enemy> enemies = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		enemyImages = new Image[4];
		addEnemy(3 * 32, 9 * 32);
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
			enemy.move(0.5f, 0);
		}
	}
	
	public void addEnemy(int x, int y) {
		enemies.add(new Enemy(x, y, 0, 0));
	}
	
	public void draw(GraphicsContext gc) {
		for(Enemy enemy : enemies) {
			drawEnemy(enemy, gc);
		}
	}
	
	private void drawEnemy(Enemy e, GraphicsContext gc) {
		gc.drawImage(enemyImages[0], (int)e.getX(), (int)e.getY());
	}
}
