package ui;

import static main.GameState.MENU;
import static main.GameState.setGameState;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objects.Tile;
import scenes.Editing;

public class Toolbar extends Bar {
	private Editing editing;
	private MyButton bMenu, bSave;
	private Tile selectedTile;

	private ArrayList<MyButton> tileButtons = new ArrayList<>();

	public Toolbar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		initButtons();
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		bSave = new MyButton("Save", 2, 684, 100, 30);

		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		int i = 0;
		for (Tile tile : editing.getGame().getTileManager().tiles) {
			tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
			i++;
		}

	}

	private void saveLevel() {
		editing.saveLevel();
	}

	public void draw(GraphicsContext gc) {

		// Background
		gc.setFill(Color.rgb(220, 123, 15));
		gc.fillRect(x, y, width, height);

		// Buttons
		drawButtons(gc);
	}

	private void drawButtons(GraphicsContext gc) {
		bMenu.draw(gc);
		bSave.draw(gc);

		drawTileButtons(gc);
		drawSelectedTile(gc);

	}

	private void drawSelectedTile(GraphicsContext gc) {

		if (selectedTile != null) {
			gc.drawImage(selectedTile.getSprite(), 550, 650, 50, 50);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(550, 650, 50, 50);
		}

	}

	private void drawTileButtons(GraphicsContext gc) {
		for (MyButton b : tileButtons) {

			// Sprite
			gc.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height);

			// MouseOver
			if (b.isMouseOver()) {
				gc.setStroke(Color.WHITE);
			}
			else {
				gc.setStroke(Color.BLACK);
			}

			// Border
			gc.strokeRect(b.x, b.y, b.width, b.height);

			// MousePressed
			if (b.isMousePressed()) {
				gc.strokeRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
				gc.strokeRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
			}

		}

	}

	public Image getButtImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.resetBooleans();
			setGameState(MENU);
		}
		else if (bSave.getBounds().contains(x, y)) {
			saveLevel();
		}
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					selectedTile = editing.getGame().getTileManager().getTile(b.getId());
					editing.setSelectedTile(selectedTile);
					return;
				}
			}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		for (MyButton b : tileButtons) {
			b.setMouseOver(false);
		}

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		for (MyButton b : tileButtons)
			b.resetBooleans();

	}

}
