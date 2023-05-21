package scenes;

import main.Game;
import ui.MyButton;
import static main.GameState.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver extends GameScene implements SceneMethods {

	/*
	 * replay button.
	 */
	private MyButton bReplay;
	/*
	 * back to menu button.
	 */
	private MyButton bMenu;

	/*
	 * initialize fields.
	 */
	public GameOver(Game game) {
		super(game);
		initButtons();
	}

	/*
	 * initialize buttons.
	 */
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
	/*
	 * render gameover page.
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(new Image(ClassLoader.getSystemResource("images/gameover.jpg").toString()), 0, 0, 640, 800);

		// game over text
		gc.setFont(Font.font("LucidaSans", FontWeight.BOLD, 100));
		gc.setFill(Color.RED);
		gc.fillText("Game Over!", 40, 170);
		gc.setStroke(Color.BLACK);
		double originalWidth = gc.getLineWidth();
		gc.setLineWidth(3);
		gc.strokeText("Game Over!", 40, 170);
		gc.setLineWidth(originalWidth);
		
		// buttons
		
		gc.setFont(Font.font("LucidaSans", FontWeight.BOLD, 12));
		bMenu.draw(gc);
		bReplay.draw(gc);
	}

	/*
	 * restart the game.
	 */
	private void replayGame() {
		// reset everything
		resetAll();

		// change state to playing
		setGameState(PLAYING);

	}

	/*
	 * resert everything.
	 */
	private void resetAll() {
		game.getPlaying().resetEverything();
	}

	@Override
	/*
	 * handle mouse click.
	 */
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameState(MENU);
			resetAll();
		} else if (bReplay.getBounds().contains(x, y))
			replayGame();
	}

	@Override
	/*
	 * handle mouse move.
	 */
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bReplay.getBounds().contains(x, y))
			bReplay.setMouseOver(true);
	}

	@Override
	/*
	 * handle mouse press.
	 */
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bReplay.getBounds().contains(x, y))
			bReplay.setMousePressed(true);

	}

	@Override
	/*
	 * handle mouse release.
	 */
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bReplay.resetBooleans();

	}

	@Override
	/*
	 * handle mouse drag.
	 */
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	/*
	 * handle mouse right click.
	 */
	public void mouseRightClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}