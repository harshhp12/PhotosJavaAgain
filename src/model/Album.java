package model;

import java.io.Serializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class Album implements Serializable {
	private String name;
	private List<Picture> pictures;
	
	public Album(String name) {
		this.name = name;
		pictures = new ArrayList<Picture>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String returnName() {
		return name;
	}
	
	
}
