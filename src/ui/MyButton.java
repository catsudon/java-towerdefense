package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MyButton {
	/*
	 * button x position.
	 */
	private int x;
	/*
	 * button y position.
	 */
	private int y;
	/*
	 * button width.
	 */
	private int width;
	/*
	 * button height.
	 */
	private int height;
	/*
	 * button id.
	 */
	private int id;


	/*
	 * button text;
	 */
	private String text;
	/*
	 * button boundaries.
	 */
	private Rectangle bounds;
	/*
	 * button status.
	 */
	private boolean mouseOver, mousePressed;

	/*
	 * an initializer for normal button.
	 */
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	/*
	 * an initializer for tile button.
	 */
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}

	/*
	 * intialize bounds.
	 */
	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	/*
	 * a function for drawing buttons.
	 */
	public void draw(GraphicsContext gc) {
		// Body
		drawBody(gc);

		// Border
		drawBorder(gc);

		// Text
		drawText(gc);
	}
	/*
	 * a function for drawing borders.
	 */
	private void drawBorder(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);
		gc.strokeRect(x, y, width, height);
		if (mousePressed) {
			gc.fillRect(x, width, id, height);
			gc.fillRect(x + 2, y + 2, width - 4, height - 4);
		}

	}
	/*
	 * a function for drawing body of the button.
	 */
	private void drawBody(GraphicsContext gc) {
		if (mouseOver)
			gc.setFill(Color.GRAY);
		else
			gc.setFill(Color.WHITE);
		gc.fillRect(x, y, width, height);

	}
	/*
	 * a function for drawing the button text.
	 */
	private void drawText(GraphicsContext gc) {
		Font font = new Font("Arial", 12);
		Text newText = new Text(text);
		newText.setFont(font);
		
		int w = (int) newText.getLayoutBounds().getWidth();
		int h = (int) newText.getLayoutBounds().getHeight();
		gc.setFill(Color.BLACK);
		gc.strokeText(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
		gc.fillText(text, x - w / 2 + width / 2, y + h / 2 + height / 2);

	}
	/*
	 * reset button status.
	 */
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	/*
	 * set button text.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/*
	 * set button mouse pressed status.
	 */
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	/*
	 * set button mouse over status.
	 */
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	/*
	 * getter for button mouse over status.
	 */
	public boolean isMouseOver() {
		return mouseOver;
	}
	/*
	 * getter for button mouse pressed status.
	 */
	public boolean isMousePressed() {
		return mousePressed;
	}
	/*
	 * getter for button bounds.
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	/*
	 * getter for button x position.
	 */
	public int getX() {
		return x;
	}
	/*
	 * getter for button y position.
	 */
	public int getY() {
		return y;
	}
	/*
	 * getter for button width
	 */
	public int getWidth() {
		return width;
	}
	/*
	 * getter for button height.
	 */
	public int getHeight() {
		return height;
	}
	/*
	 * getter for button id.
	 */
	public int getId() {
		return id;
	}

}