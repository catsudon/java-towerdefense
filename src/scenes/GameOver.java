package scenes;

import main.Game;
import ui.MyButton;
import static main.GameState.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver extends GameScene implements SceneMethods {

	private MyButton bReplay, bMenu;

	public GameOver(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 300;
		int yOffset = 100;

		bMenu = new MyButton("Menu", x, y, w, h);
		bReplay = new MyButton("Replay", x, y + yOffset, w, h);

	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 640, 800);
		
		// game over text
		gc.setFont(Font.font("LucidaSans", FontWeight.BOLD, 50));
		gc.setFill(Color.RED);
		gc.fillText("Game Over!", 180, 80);

		// buttons
		
		gc.setFont(Font.font("LucidaSans", FontWeight.BOLD, 12));
		bMenu.draw(gc);
		bReplay.draw(gc);
	}

	private void replayGame() {
		// reset everything
		resetAll();

		// change state to playing
		setGameState(PLAYING);

	}

	private void resetAll() {
		game.getPlaying().resetEverything();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameState(MENU);
			resetAll();
		} else if (bReplay.getBounds().contains(x, y))
			replayGame();
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bReplay.getBounds().contains(x, y))
			bReplay.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bReplay.getBounds().contains(x, y))
			bReplay.setMousePressed(true);

	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bReplay.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}