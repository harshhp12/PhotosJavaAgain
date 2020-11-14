package model;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ByteStreamPhoto implements Serializable {
	private int height, width;
	private int [][] pxls;
	
	public ByteStreamPhoto() {}
	
	public int returnWidth() {
		return width;
	}
	
	public int[][] returnPxls(){
		return pxls;
	}
	
	public int returnHeight() {
		return height;
	}
	
	public Image returnImage() {
		WritableImage image = new WritableImage(width, height);
		PixelWriter x = image.getPixelWriter();
		for(int a = 0; a<width; a++) {
			for(int b = 0; b<height; b++) {
				x.setArgb(a, b, pxls[a][b]);
			}
		}
		return image;
	}
	
}


