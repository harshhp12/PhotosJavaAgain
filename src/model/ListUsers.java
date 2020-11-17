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


/**
 * This is the class for the list of users
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class ListUsers implements Serializable {
	private static final long serialVersionUID = -5515097954743129288L;
	public static final String storeDir = "dat";
	public static final String storeFile = "users.dat";
	
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
	 
	 /**
	  * string for all the usernames, separated by a space
	  */
	 public String toString() {
		  if (users == null)
			  return "no users";
		  String output = "";
		  for(User u : users)
		  {
			  output += u.getUsername() + " ";
		  }
		  return output;
	  }
	 /**
	  * This is the deserialization code taken from the slides
	  * @return
	  * @throws IOException
	  * @throws ClassNotFoundException
	  */
	 public static ListUsers read() throws IOException, ClassNotFoundException {
		   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
		   ListUsers ulist = (ListUsers) ois.readObject();
		   ois.close();
		   return ulist;
	   }
	  /**
	   * This is the serialization code taken from the slides
	   * @param ulist
	   * @throws IOException
	   */
	   public static void write (ListUsers ulist) throws IOException {
		   ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
		   oos.writeObject(ulist);
		   oos.close();
	   }
}
