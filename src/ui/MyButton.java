package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MyButton {

	private int x, y, width, height, id;

	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	// For normal Buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	// For tile buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(GraphicsContext gc) {
		// Body
		drawBody(gc);

		// Border
		drawBorder(gc);

		// Text
		drawText(gc);
	}

	private void drawBorder(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);
		gc.strokeRect(x, y, width, height);
		if (mousePressed) {
			gc.fillRect(x, width, id, height);
			gc.fillRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	private void drawBody(GraphicsContext gc) {
		if (mouseOver)
			gc.setFill(Color.GRAY);
		else
			gc.setFill(Color.WHITE);
		gc.fillRect(x, y, width, height);

	}

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

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getId() {
		return id;
	}

}