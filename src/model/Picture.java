package model;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class Picture implements Serializable {
	
	private String photoPath;
	private String caption;
	private Calendar calendar;
	private List <Tag> tags;
	/**
	 * Constructor one for picture
	 * @param photoPath
	 */
	public Picture(String photoPath) {
		this.photoPath = photoPath;
		this.caption="";
		this.calendar = Calendar.getInstance();
		this.calendar.set(Calendar.MILLISECOND, 0);
		this.tags = new ArrayList<Tag>();
	}
	

	/**
	 * ex: location, New Brunswick
	 * @param name
	 * @param value
	 */
	public void addTag(String name, String value) {
		
	}
	
	public void editTag() {
		
	}
	/**
	 * 
	 * @return
	 */
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void deleteTag() {
		
	}
	
	/**
	 * Set caption of the photo
	 * @param caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	

}
