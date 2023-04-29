package help;

import java.util.ArrayList;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ImageFix {
	
	// Rotate
	
	public static Image getRotatedImage(Image image, int rotateAngle) {
		ImageView imageView = new ImageView(image);
		imageView.setRotate(rotateAngle);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		
		Image rotatedImage = imageView.snapshot(params, null);
		return rotatedImage;
	}
	
	// Image Layer Build
	public static Image buildImage(Image[] images) {
		ArrayList <ImageView> imageViews = new ArrayList<>();
		
		for(Image image : images) {
			imageViews.add(new ImageView(image));
		}
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageViews);
		
		Image stackedImage = stackPane.snapshot(null, null);
		
		return stackedImage;
	}
	
	// Rotate second image only
	public static Image getBuildRotatedImage(Image[] images, int rotateAngle, int rotateAtIndex) {
		ArrayList <ImageView> imageViews = new ArrayList<>();
		
		for(int i = 0; i < images.length; i++) {
			if(rotateAtIndex == i) {
				imageViews.add(new ImageView(getRotatedImage(images[i], rotateAngle)));
			}
			else {
				imageViews.add(new ImageView(images[i]));
			}
		}
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageViews);
		
		Image stackedImage = stackPane.snapshot(null, null);
		
		return stackedImage;
	}
}
