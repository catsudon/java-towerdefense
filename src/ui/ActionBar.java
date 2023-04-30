package ui;

import static main.GameState.*;

import entity.tower.Tower;
import help.Constants.Towers;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import scenes.Playing;

public class ActionBar extends Bar {
	
	@SuppressWarnings("unused")
	private Playing playing;
	private MyButton bMenu;
	
	private MyButton[] towerButtons;
	private Tower selectedTower;
	
	private Tower displayedTower;
	
	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		
		towerButtons = new MyButton[3];
		
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		for(int i = 0; i < towerButtons.length; i++) {
			towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
		}
	}
	
	private void drawButtons(GraphicsContext gc) {
		bMenu.draw(gc);
		
		for(MyButton b : towerButtons) {
			gc.setFill(Color.GRAY);
			gc.fillRect(b.x, b.y, b.width, b.height);
			gc.drawImage(playing.getTowerManager().getTowerImages()[b.getId()], b.x, b.y, b.width, b.height);
		
			drawButtonFeedback(gc, b);
		}
	}
	
	public void draw(GraphicsContext gc) {

		// Background
		gc.setFill(Color.rgb(220, 123, 15));
		gc.fillRect(x, y, width, height);

		// Buttons
		drawButtons(gc);
		
		// Displayed Tower
		drawDisplayedTower(gc);
	}
	
	private void drawDisplayedTower(GraphicsContext gc) {
		if(displayedTower == null) {
			return ;
		}
		gc.setFill(Color.GRAY);
		gc.fillRect(410, 645, 220, 85);
		
		gc.setStroke(Color.BLACK);
		gc.strokeRect(410, 645, 220, 85);
		gc.strokeRect(420, 650, 50, 50);
		
		gc.drawImage(playing.getTowerManager().getTowerImages()[displayedTower.getTowerType()], 420, 650, 50, 50);
		
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("LucidaSans", FontWeight.BOLD, 15));
		gc.fillText("" + Towers.GetName(displayedTower.getTowerType()), 490, 660);
		gc.fillText("ID: " + displayedTower.getId(), 490, 675);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.resetBooleans();
			setGameState(MENU);
		}
		else {
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					
					b.setMouseOver(true);
					
					selectedTower = new Tower(0, 0, -1, b.getId());
					playing.setSelectedTower(selectedTower);
					return ;
				}
			}
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for(MyButton b : towerButtons) {
			b.setMouseOver(false);
		}
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return ;
				}
			}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else {
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return ;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for(MyButton b : towerButtons) {
			b.resetBooleans();
		}
	}

	public void displayTower(Tower tower) {
		// TODO Auto-generated method stub
		displayedTower = tower;
	}
}
