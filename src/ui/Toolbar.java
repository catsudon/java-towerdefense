package ui;

import static main.GameState.MENU;
import static main.GameState.setGameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objects.Tile;
import scenes.Editing;

public class Toolbar extends Bar {
	private Editing editing;
	private MyButton bMenu, bSave;
	private Tile selectedTile;

	//private ArrayList<MyButton> tileButtons = new ArrayList<>();
	
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();

	private MyButton bGrass, bWater, bRoadS, bRoadC, bWaterC, bWaterB, bWaterI;
	private MyButton bPathStart, bPathEnd;
	
	private Image startPathImage, endPathImage;
	
	private MyButton currentButton;
	private int currentIndex;
	
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

		int id = 0;
		
		initMapButton(bGrass, editing.getGame().getTileManager().getGrass(), xStart, yStart, xOffset, w, h, id++);
		initMapButton(bWater, editing.getGame().getTileManager().getWater(), xStart, yStart, xOffset, w, h, id++);
		initMapButton(bRoadS, editing.getGame().getTileManager().getRoadS(), xStart, yStart, xOffset, w, h, id++);
		initMapButton(bRoadC, editing.getGame().getTileManager().getRoadC(), xStart, yStart, xOffset, w, h, id++);
		initMapButton(bWaterC, editing.getGame().getTileManager().getCorners(), xStart, yStart, xOffset, w, h, id++);
		initMapButton(bWaterB, editing.getGame().getTileManager().getBeaches(), xStart, yStart, xOffset, w, h, id++);
		initMapButton(bWaterI, editing.getGame().getTileManager().getBeaches(), xStart, yStart, xOffset, w, h, id++);
		
		initMapButton(bPathStart, editing.getGame().getTileManager().getPathStart(), xStart, yStart + xOffset, 0, w, h, id++);
		initMapButton(bPathEnd, editing.getGame().getTileManager().getPathEnd(), xStart + xOffset, yStart + xOffset, 0, w, h, id++);
		startPathImage = editing.getGame().getTileManager().getPathStart().get(0).getSprite();
		endPathImage = editing.getGame().getTileManager().getPathEnd().get(0).getSprite();
	}
	
	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOffSet, int w, int h, int id) {
		b = new MyButton("", x + xOffSet * id, y, w, h, id);
		map.put(b, list);
	}
	
	public void rotateSprite() {
		if(currentButton == null) {
			return ;
		}
		currentIndex = (currentIndex + 1) % map.get(currentButton).size();
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);
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

		drawMapButtons(gc);
		drawSelectedTile(gc);

	}

	private void drawMapButtons(GraphicsContext gc) {
		for (Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			// Sprite
			gc.drawImage(entry.getValue().get(0).getSprite(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
			drawButtonFeedback(gc, b);
		}
	}
	
	private void drawSelectedTile(GraphicsContext gc) {

		if (selectedTile != null) {
			gc.drawImage(selectedTile.getSprite(), 550, 650, 50, 50);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(550, 650, 50, 50);
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
			for (MyButton b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					selectedTile = map.get(b).get(0);
					editing.setSelectedTile(selectedTile);
					currentButton = b;
					currentIndex = 0;
					return ;
				}
			}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		for (MyButton b : map.keySet()) {
			b.setMouseOver(false);
		}

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else {
			for (MyButton b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return ;
				}
			}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}
		else if (bSave.getBounds().contains(x, y)) {
			bSave.setMousePressed(true);
		}
		else {
			for (MyButton b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return ;
				}
			}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		for(MyButton b : map.keySet()) {
			b.resetBooleans();
		}
	}

	public Image getStartPathImage() {
		return startPathImage;
	}

	public Image getEndPathImage() {
		return endPathImage;
	}

}
