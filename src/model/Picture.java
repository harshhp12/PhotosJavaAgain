package model;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class Picture implements Serializable {
	
	private String caption;
	private Calendar calendar;
	private ByteStreamPhoto photo;
	private List <Tag> tags;
	
	public Picture() {
		
		caption="";
		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		photo = new ByteStreamPhoto();
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
