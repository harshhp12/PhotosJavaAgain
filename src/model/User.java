package model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class User implements Serializable {

	private String username;
	private List<Album> albums;
	
	public User(String username) {
		this.username = username;
		albums = new ArrayList<Album>();
	}
	
	public String getUsername() {
		return username;
	}
	
	
}
