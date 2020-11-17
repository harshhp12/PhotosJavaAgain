package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * The controller for the user home page
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class userPageC implements LogOff{
	
	@FXML TableView<Album> albumList;
	@FXML TableColumn<Album, String> aName;
	@FXML TableColumn<Album, String> numberPhotos;
	@FXML TableColumn<Album, String> dates;
	
	//setup the variables for the list similar to how it was done in admin page
	
	//observable list to show in table
	private ObservableList<Album> obsList;
	
	//backend list to get the arrayList of albums from the user data
	private List<Album> albums = new ArrayList<Album>();
	
	//the current user that is logged in
	private User user;
	
	//the list of all users so we can update serialization
	private ListUsers ulist;
	
	/**
	 * Set up the startup to show album list
	 * @param mainStage
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void start(Stage mainStage) throws ClassNotFoundException, IOException{
	}
	
	/**
	 * Use the interface to logout
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
	
	/**
	 * Handle creation of a new album upon button click
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML
	protected void handleCreate(ActionEvent event) throws ClassNotFoundException{
		
	}
	
	/**
	 * Handle deletion of an album upon button click
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML
	protected void handleDelete(ActionEvent event) throws ClassNotFoundException{
		
	}
	
	/**
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML
	protected void handleRename(ActionEvent event) throws ClassNotFoundException{
		
	}
	
	/**
	 * handle search: open another fxml
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML
	protected void handleSearch(ActionEvent event) throws ClassNotFoundException{
		
	}
	
	/**
	 * Handle opening of album: open another fxml
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML
	protected void handleOpen(ActionEvent event) throws ClassNotFoundException{
		
	}
	
	/**
	 * Take in a user and set the current user from the loginController
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUlist(ListUsers ulist) {
		this.ulist = ulist;
	}
	
}
