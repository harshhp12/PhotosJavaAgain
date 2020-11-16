package model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
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
	 * add an album into the current list of the users albums
	 * @param albumName
	 */
	public void addAlbum(String albumName) {
		Album newAlbum = new Album(albumName);
		albums.add(newAlbum);
	}
	
	/**
	 * Delete the album at index i from the current list
	 * @param i
	 */
	public void deleteAlbum(int i) {
		albums.remove(i);
	}
	
}
