package scenes;

import javafx.scene.canvas.GraphicsContext;

public interface SceneMethods {
	
	public void render(GraphicsContext gc);
	
	public void mouseClicked(int x, int y);

	public void mouseMoved(int x, int y);

	public void mousePressed(int x, int y);

	public void mouseReleased(int x, int y);

	public void mouseDragged(int x, int y);
	
	public void mouseRightClicked(int x, int y);
}
