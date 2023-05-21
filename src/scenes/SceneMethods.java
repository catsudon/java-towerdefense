package scenes;

import javafx.scene.canvas.GraphicsContext;

public interface SceneMethods {
	/*
	 * render.
	 */
	public void render(GraphicsContext gc);
	/*
	 * handle mouse click.
	 */
	public void mouseClicked(int x, int y);
	/*
	 * handle mouse move.
	 */
	public void mouseMoved(int x, int y);
	/*
	 * handle mouse press.
	 */
	public void mousePressed(int x, int y);
	/*
	 * handle mouse release.
	 */
	public void mouseReleased(int x, int y);
	/*
	 * handle mouse drag.
	 */
	public void mouseDragged(int x, int y);
	/*
	 * handle mouse right click.
	 */
	public void mouseRightClicked(int x, int y);
}
