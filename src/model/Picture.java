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
	
	/**
	 * 	 Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String caption;
	private Calendar calendar;
	private List <Tag> tags;
	
	public Picture() {
		
		caption="";
		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		tags = new ArrayList<Tag>();
	}
	
	//ex: location, New Brunswick
	public void addTag(String name, String value) {
		
	}
	
	public void editTag() {
		
	}
	
	public void deleteTag() {
		
	}
	

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	

}
