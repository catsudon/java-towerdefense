package ui;

import static main.GameState.*;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import scenes.Playing;

public class ActionBar extends Bar {
	
	@SuppressWarnings("unused")
	private Playing playing;
	private MyButton bMenu;
	
	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
	}
	
	private void drawButtons(GraphicsContext gc) {
		bMenu.draw(gc);
	}
	
	public void draw(GraphicsContext gc) {

		// Background
		gc.setFill(Color.rgb(220, 123, 15));
		gc.fillRect(x, y, width, height);

		// Buttons
		drawButtons(gc);
	}
	
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.resetBooleans();
			setGameState(MENU);
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
	}
}
