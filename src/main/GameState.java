package main;

public enum GameState {
	MENU,
	EDIT,
	PLAYING,
	SETTINGS;

	public static GameState gameState = MENU;
	
	public static void setGameState(GameState state) {
		gameState = state;
	}
}
