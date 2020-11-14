package model;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ListUsers implements Serializable {
	private List<User> users;
	
	public ListUsers() {
		users = new ArrayList<User>();
	}
	
	 public List<User> returnUserList()
	  {
		  return users;
	  }
	 
	 public void addUserToList(User u)
	  {
		  users.add(u);
	  }
	 
	 public void removeUserFromList(User u)
	  {
		  users.remove(u);
	  }
}
