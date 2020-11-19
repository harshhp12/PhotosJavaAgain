package model;

import java.io.Serializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import java.util.HashSet;

/**
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class Album implements Serializable {
	
	/**
	 Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3515004244590459483L;
	private String name;
	private List<Picture> pictures;
	private Date leastDate, maxDate;
	
	/**
	 * Constructor to create new album
	 * @param name
	 */
	public Album(String name) {
		this.name = name;
		this.leastDate = null;
		this.maxDate = null;
		pictures = new ArrayList<Picture>();
	}
	
	/**
	 * Method to rename/set the name for the album 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Obtain the name of the album
	 * @return
	 */
	public String returnName() {
		return name;
	}
	
	/**
	 * Return list of pictures
	 * @return
	 */
	public List<Picture> getPics(){
		return pictures;
	}
	
	/**
	 * Add a picture and return the newly added pic
	 * So that we can change its attributes
	 * @param photoPath
	 * @return
	 */
	public Picture addPhoto(String photoPath) {
		Picture newPic = new Picture(photoPath);
		pictures.add(newPic);
		if(pictures.size()==1) {
			leastDate = newPic.getDate();
			maxDate = newPic.getDate();
		}
		else if(newPic.getDate().compareTo(maxDate)>0) {
			this.maxDate = newPic.getDate();
		}
		else if(newPic.getDate().compareTo(leastDate)<0) {this.leastDate=newPic.getDate();}
			return newPic;
	}
	
	/**
	 * display album names
	 */
	public String toString() {
		String output = this.name;
		return output;
	}
	
	public void setPics(List<Picture> list) {
		this.pictures = list;
	}
	
	public Date getLeastDate() {
		return this.leastDate;
	}
	
	public Date getMaxdate() {
		return this.maxDate;
	}

}
	

