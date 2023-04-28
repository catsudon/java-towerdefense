package scenes;

import help.LevelBuilder;
import javafx.scene.canvas.GraphicsContext;
import main.Game;
import managers.TileManager;

public class Playing extends GameScene implements SceneMethods{

	private int[][] lvl;
	private TileManager tileManager;
	
	public Playing(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		lvl = LevelBuilder.getLevelData();
		tileManager = new TileManager();
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(int yIndex = 0; yIndex < lvl.length; yIndex++) {
			for(int xIndex = 0; xIndex < lvl[yIndex].length; xIndex++) {
				gc.drawImage(tileManager.getSprite(lvl[yIndex][xIndex]), 32 * xIndex, 32 * yIndex);
			}
		}
	}
}
