package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import main.Game;
import main.GameState;

public class InputUtility {
	
	public static double mouseX, mouseY;
	
	public static boolean mouseOnScreen = true;
	
	@SuppressWarnings("unused")
	private static boolean isLeftDown = false;
	
	private static boolean isLeftClickedLastTick = false;
	
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	
	public static void setKeyPressed(Game game, KeyCode keyCode, boolean pressed) {
		if(pressed) {
			if(!keyPressed.contains(keyCode)) {
				keyPressed.add(keyCode);
				game.getPlaying().keyPressed(keyCode);
			}
		}
		else {
			keyPressed.remove(keyCode);
		}
		System.out.println(keyPressed);
	}
	
	public static void mouseLeftClicked(Game game, int x, int y) {
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().mouseClicked(x, y);
				break;
			case PLAYING:
				game.getPlaying().mouseClicked(x, y);
				break;
			case SETTINGS:
				game.getSettings().mouseClicked(x, y);
				break;
			case EDIT:
				game.getEditing().mouseClicked(x, y);
				break;
		}
	}
	
	public static void mouseLeftDown(Game game, int x, int y) {
		isLeftDown = true;
		isLeftClickedLastTick = true;
		
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().mousePressed(x, y);
				break;
			case PLAYING:
				game.getPlaying().mousePressed(x, y);
				break;
			case SETTINGS:
				game.getSettings().mousePressed(x, y);
				break;
			case EDIT:
				game.getEditing().mousePressed(x, y);
				break;
		}
	}
	
	public static void mouseLeftRelease(Game game, int x, int y) {
		isLeftDown = false;
		
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().mouseReleased(x, y);
				break;
			case PLAYING:
				game.getPlaying().mouseReleased(x, y);
				break;
			case SETTINGS:
				game.getSettings().mouseReleased(x, y);
				break;
			case EDIT:
				game.getEditing().mouseReleased(x, y);
				break;
		}
	}
	
	public static boolean isLeftClickTriggered() {
		return isLeftClickedLastTick;
	}
	
	public static void updateInputState() {
		isLeftClickedLastTick = false;
	}
	
	public static void mouseMoved(Game game, int x, int y) {
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().mouseMoved(x, y);
				break;
			case PLAYING:
				game.getPlaying().mouseMoved(x, y);
				break;
			case SETTINGS:
				game.getSettings().mouseMoved(x, y);
				break;
			case EDIT:
				game.getEditing().mouseMoved(x, y);
				break;
		}
	}
	
	public static void mouseDragged(Game game, int x, int y) {
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().mouseDragged(x, y);
				break;
			case PLAYING:
				game.getPlaying().mouseDragged(x, y);
				break;
			case SETTINGS:
				game.getSettings().mouseDragged(x, y);
				break;
			case EDIT:
				game.getEditing().mouseDragged(x, y);
				break;
		}
	}
	
	public static void mouseRightClicked(Game game, int x, int y) {
		switch(GameState.gameState) {
			case MENU:
				game.getMenu().mouseRightClicked(x, y);
				break;
			case PLAYING:
				game.getPlaying().mouseRightClicked(x, y);
				break;
			case SETTINGS:
				game.getSettings().mouseRightClicked(x, y);
				break;
			case EDIT:
				game.getEditing().mouseRightClicked(x, y);
				break;
		}	
	}
}
