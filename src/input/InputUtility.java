package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import main.Game;
import main.GameState;

public class InputUtility {
	
	public static double mouseX, mouseY;
	
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	
	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if(pressed) {
			if(!keyPressed.contains(keycode)) {
				keyPressed.add(keycode);
			}
		}
		else {
			keyPressed.remove(keycode);
		}
		System.out.println(keyPressed);
	}
	
	public static void mouseLeftClicked(Game game, int x, int y) {
		switch(GameState.gameState) {
			case PLAYING:
					game.getPlaying().mouseClicked(x, y);
				break;
		}
	}
	
	public static void mouseLeftDown(Game game, int x, int y) {
		isLeftDown = true;
		isLeftClickedLastTick = true;
		
		switch(GameState.gameState) {
			case PLAYING:
				game.getPlaying().mousePressed(x, y);
				break;
		}
	}
	
	public static void mouseLeftRelease(Game game, int x, int y) {
		isLeftDown = false;
		
		switch(GameState.gameState) {
		case PLAYING:
			game.getPlaying().mouseReleased(x, y);
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
			case PLAYING:
				game.getPlaying().mouseMoved(x, y);
				break;
		}
	}
	
	public static void mouseDragged(Game game, int x, int y) {
		switch(GameState.gameState) {
			case PLAYING:
				game.getPlaying().mouseDragged(x, y);
				break;
		}
	}
}
