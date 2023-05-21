package utilities;

import java.util.ArrayList;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ImageFix {
	
	/*
	 * Get a subimage from the provided image.
	 */
	public static Image getSubImage(Image image, int x, int y, int w, int h) {
		PixelReader pixelReader = image.getPixelReader();
		WritableImage writableImage = new WritableImage(pixelReader, x, y, w, h);
		
		return writableImage;
	}
	
	/*
	 * Rotate the provided image by the specified angle.
	 */
	public static Image getRotatedImage(Image image, int rotateAngle) {
		ImageView imageView = new ImageView(image);
		imageView.setRotate(rotateAngle);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		
		Image rotatedImage = imageView.snapshot(params, null);
		return rotatedImage;
	}
	
	/*
	 * Build an image by stacking multiple images on top of each other.
	 */
	public static Image buildImage(Image[] images) {
		ArrayList<ImageView> imageViews = new ArrayList<>();
		
		for (Image image : images) {
			imageViews.add(new ImageView(image));
		}
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageViews);
		
		Image stackedImage = stackPane.snapshot(null, null);
		
		return stackedImage;
	}
	
	/*
	 * Build an image by stacking multiple images on top of each other, with the option to rotate a specific image.
	 */
	public static Image getBuildRotatedImage(Image[] images, int rotateAngle, int rotateAtIndex) {
		ArrayList<ImageView> imageViews = new ArrayList<>();
		
		for (int i = 0; i < images.length; i++) {
			if (rotateAtIndex == i) {
				imageViews.add(new ImageView(getRotatedImage(images[i], rotateAngle)));
			} else {
				imageViews.add(new ImageView(images[i]));
			}
		}
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageViews);
		
		Image stackedImage = stackPane.snapshot(null, null);
		
		return stackedImage;
	}
	
	/*
	 * Build an array of stacked images by rotating a specific image in each frame.
	 */
	public static Image[] getBuildRotatedImage(Image[] images, Image secondImage, int rotateAngle) {
		
		Image[] stackedImages = new Image[images.length];
		
		for (int i = 0; i < images.length; i++) {
			ArrayList<ImageView> imageViews = new ArrayList<>();
			
			imageViews.add(new ImageView(images[i]));
			imageViews.add(new ImageView(getRotatedImage(secondImage, rotateAngle)));
			
			StackPane stackPane = new StackPane();
			stackPane.getChildren().addAll(imageViews);
			
			Image stackedImage = stackPane.snapshot(null, null);
			
			stackedImages[i] = stackedImage;
		}
		
		return stackedImages;
	}
}
