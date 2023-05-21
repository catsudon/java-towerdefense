package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bar {

	/*
	 * x position of the bar.
	 */
	protected int x;
	/*
	 * y position of the bar.
	 */
	protected int y;
	/*
	 * bar width.
	 */
	protected int width;
	/*
	 * bar height.
	 */
	protected int height
	/*
	 * initialize fields.
	 */
	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}
	/*
	 * draw a reaction when the button has got an action.
	 */
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