package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import main.Game;

import input.InputUtility;

public class GameScreen extends Canvas {
	
	private Game game;
	
	public GameScreen(Game game, double width, double height) {
		super(width, height);
		
		this.game = game;
		
		this.setVisible(true);
		addListener();
	}
	
	public void drawImage(GraphicsContext gc, Image image) {
		//gc.drawImage(image, 0, 0, null);
	
		//gc.drawImage(getSubImage(image, 32 * 9, 32 * 1, 32, 32), 0, 0);
	
		//gc.drawImage(sprites.get(8), 0, 0);
	}
	
	public void paintComponent(GraphicsContext gc) {
		game.getRender().render(gc);
	}
	
	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});
		
		this.setOnMouseClicked((MouseEvent event) -> {
			if(event.getButton() == MouseButton.PRIMARY) {
				InputUtility.mouseLeftClicked(game, (int)event.getX(), (int)event.getY());
			}
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				InputUtility.mouseLeftDown(game, (int)event.getX(), (int)event.getY());
			}
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				InputUtility.mouseLeftRelease(game, (int)event.getX(), (int)event.getY());
			}
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
				InputUtility.mouseMoved(game, (int)event.getX(), (int)event.getY());
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
				InputUtility.mouseDragged(game, (int)event.getX(), (int)event.getY());
			}
		});
	}
}
