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
		gc.strokeRect(b.x, b.y, b.width, b.height);

		// MousePressed
		if (b.isMousePressed()) {
			gc.strokeRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
			gc.strokeRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
		}
	}
}