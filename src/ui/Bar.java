package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bar {

	protected int x, y, width, height;

	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}
	
	protected void drawButtonFeedback(GraphicsContext gc, MyButton b) {
		// MouseOver
		if (b.isMouseOver()) {
			gc.setStroke(Color.WHITE);
		}
		else {
			gc.setStroke(Color.BLACK);
		}

		// Border
		gc.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		// MousePressed
		if (b.isMousePressed()) {
			gc.strokeRect(b.getX() + 1, b.getY() + 1, b.getWidth() - 2, b.getHeight() - 2);
			gc.strokeRect(b.getX() + 2, b.getY() + 2, b.getWidth() - 4, b.getHeight() - 4);
		}
	}
}