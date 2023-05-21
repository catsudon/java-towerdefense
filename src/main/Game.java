package main;

import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import managers.TileManager;
import scenes.Editing;
import scenes.GameOver;
import scenes.Menu;
import scenes.Playing;
import utilities.LoadSave;
import money.Money;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.GraphicsContext;

public class Game extends Application {

	private static final double FPS_SET = 120.0;
	private static final double UPS_SET = 120.0;

	private static final double timePerFrame = 1_000_000_000.0 / FPS_SET;
	private static final double updatesPerFrame = 1_000_000_000.0 / UPS_SET;

	private long lastFrameTime = 0;
	private long lastUpdateTime = 0;
	private long lastCheckedTime = 0;
	
	@SuppressWarnings("unused")
	private int frames = 0;
	@SuppressWarnings("unused")
	private int updates = 0;

	private GameScreen gameScreen;

	private Render render;

	private Menu menu;
	private Playing playing;
	private Editing editing;

	private TileManager tileManager;
	private GameOver gameOver;

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();

		Scene scene = new Scene(root);

		primaryStage.setTitle("Croissant VS Salad");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);

		createDefaultLevel();
		initClasses();

		root.getChildren().add(gameScreen);

		primaryStage.show();
		Money wallet = new Money(0);
		
		playing.getSoundPlayer().bgm();

		AnimationTimer animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				// Render
				if (now - lastFrameTime >= timePerFrame) {
					GraphicsContext gc = gameScreen.getGraphicsContext2D();
					gameScreen.paintComponent(gc);
					frames++;
					lastFrameTime = now;
				}

				// Update
				if (now - lastUpdateTime >= updatesPerFrame) {
					updateGame();
					updates++;
					lastUpdateTime = now;
				}

				if (now - lastCheckedTime >= 1_000_000_000) {
					lastCheckedTime = now;
					if (GameState.gameState == main.GameState.PLAYING) {
						wallet.updateMoney(5);
					}
				}
				gameScreen.requestFocus();
			}
		};

		animationTimer.start();
	}

	private void updateGame() {
		InputUtility.updateInputState();

		switch (GameState.gameState) {
			case EDIT:
				break;
			case MENU:
				break;
			case PLAYING:
				playing.update();
			default:
				break;
		}
	}

	private void createDefaultLevel() {
		LoadSave.getRandomLevelData("new_level");
	}

	private void initClasses() {
		tileManager = new TileManager();

		gameScreen = new GameScreen(this, 640, 800);
		render = new Render(this);

		menu = new Menu(this);
		playing = new Playing(this);
		editing = new Editing(this);
		gameOver = new GameOver(this);
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void displayFPSUPS() {
		System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
		frames = 0;
		updates = 0;
	}

	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Editing getEditing() {
		return editing;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

	public GameOver getGameOver() {
		return gameOver;
	}
}