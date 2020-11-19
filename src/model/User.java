package model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This is the class for the user which is a part of the ListUsers
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class User implements Serializable {

	/**
	 Generated serial versionID 
	 */
	private static final long serialVersionUID = 3147952036443552859L;
	
	private String username;
	private List<Album> albums;
	/**
	 * Constructor for creating a new user
	 * @param username
	 */
	public User(String username) {
		this.username = username;
		albums = new ArrayList<Album>();
	}
	
	/**
	 * Return the username
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Return the list of albums
	 * @return
	 */
	public List<Album> getAlbums(){
		return albums;
	}
	
	/**
	 * 
	 * @param albumName
	 * @return
	 */
	public Album addAlbum(String albumName) {
		Album newAlbum = new Album(albumName);
		albums.add(newAlbum);
		
		return newAlbum;
	}
	
	
	/**
	 * Delete the album at index i from the current list
	 * @param i
	 */
	public void deleteAlbum(int i) {
		albums.remove(i);
	}
	
	public List<Picture> getPhotosInDateRange(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		Calendar calUserStartDate = Calendar.getInstance();
		calUserStartDate.set(startYear, startMonth, startDay);
		
		Calendar calUserEndDate = Calendar.getInstance();
		calUserEndDate.set(endYear, endMonth, endDay);
		
		List<Picture> photosInDateRange = new ArrayList<Picture>();
		
		for(Album userAlbum : albums) {
			for(Picture photo : userAlbum.getPics()) {
				Date photoDate = photo.getDate();
				Calendar calPhotoDate = Calendar.getInstance();
				calPhotoDate.setTime(photoDate);

				int photoYear = calPhotoDate.get(Calendar.YEAR);
				int photoMonth = calPhotoDate.get(Calendar.MONTH) + 1;
				int photoDay = calPhotoDate.get(Calendar.DAY_OF_MONTH);

				Calendar calPhotoDate2 = Calendar.getInstance();
				calPhotoDate2.set(photoYear, photoMonth, photoDay);
				
				if((calPhotoDate2.compareTo(calUserStartDate) > 0 && calPhotoDate2.compareTo(calUserEndDate) < 0) 
						|| (calPhotoDate2.equals(calUserStartDate)) || calPhotoDate2.equals(calUserEndDate)) {
					photosInDateRange.add(photo);
				}
			}
		}
		
		return photosInDateRange;
	}
	
	public List<Picture> getPhotosWithTag(String key, String value){
		
		List<Picture> photosWithTag = new ArrayList<Picture>();
		
		for(Album userAlbum : albums) {
			for(Picture photo : userAlbum.getPics()) {
				List<Tag> tags = photo.getTags();
				for(int i = 0; i < tags.size(); i++) {		
					if(tags.get(i).returnName().equals(key) && tags.get(i).returnValue().equals(value)) {
						photosWithTag.add(photo);
					}	
				}
			}
		}
		
		return photosWithTag;
	}
	
	public List<Picture> getPhotosWithTagsOR(String key, String value, String key2, String value2){
List<Picture> photosWithTag = new ArrayList<Picture>();
		
		for(Album userAlbum : albums) {
			for(Picture photo : userAlbum.getPics()) {
				List<Tag> tags = photo.getTags();
				for(int i = 0; i < tags.size(); i++) {		
					if(tags.get(i).returnName().equals(key) && tags.get(i).returnValue().equals(value)
							|| tags.get(i).returnName().equals(key2) && tags.get(i).returnValue().equals(value2)) {
						photosWithTag.add(photo);
					}	
				}
			}
		}
		
		return photosWithTag;
	}
	
	public List<Picture> getPhotosWithTagsAND(String key, String value, String key2, String value2){
List<Picture> photosWithTag = new ArrayList<Picture>();
		
		for(Album userAlbum : albums) {
			for(Picture photo : userAlbum.getPics()) {
				List<Tag> tags = photo.getTags();
				for(int i = 0; i < tags.size(); i++) {		
					if(tags.get(i).returnName().equals(key) && tags.get(i).returnValue().equals(value)
							&& tags.get(i).returnName().equals(key2) && tags.get(i).returnValue().equals(value2)) {
						photosWithTag.add(photo);
					}	
				}
			}
		}
		
		return photosWithTag;
	}
	
}
