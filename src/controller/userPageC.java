package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

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
		//the albums field is set to the albums of the current user
		albums = user.getAlbums();
		
		//create new observableList backed by the arrayList of users
		obsList = FXCollections.observableArrayList(albums);
		
		//specify how to set the columns:
		
		//specify how to fill in the columns:
		//album name column
		aName.setCellValueFactory(new Callback<CellDataFeatures<Album, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Album, String> u) {
			         return new SimpleStringProperty(u.getValue().returnName());}
				  });
		
		//album number of photos column
		
		
		//album dates column
		
		
		
		//set the object items
		albumList.setItems(obsList);
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
	
	@FXML TextField albumName;
	/**
	 * Handle creation of a new album upon button click
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	@FXML 
	protected void handleCreate(ActionEvent event) throws ClassNotFoundException, IOException{
		//get the intended album name from the textfield
		String name = albumName.getText();
		
		//create the album
		Album newAlbum = new Album(name);
		System.out.println(name);
		
		//add the album to lists
		obsList.add(newAlbum);
		albums.add(newAlbum);
		
		//write it in for persistence/serialization 
		ListUsers.write(ulist);
		
		//update our view
		albumList.setItems(obsList);
	}
	
	/**
	 * Handle deletion of an album upon button click
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	@FXML
	protected void handleDelete(ActionEvent event) throws ClassNotFoundException, IOException{
		//get selected album
		Album selected = albumList.getSelectionModel().getSelectedItem();
		//remove it from our lists
		obsList.remove(selected);
		albums.remove(selected);
		//update our view
		albumList.setItems(obsList);
		//write it in for persistence/serialization
		ListUsers.write(ulist);
	}
	
	/**
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	@FXML
	protected void handleRename(ActionEvent event) throws ClassNotFoundException, IOException{
		//get the new name
		String newName = albumName.getText();
		
		//if empty input
		if(newName.isEmpty()) {System.out.println("Cannot rename to empty"); return;}
		
		System.out.println(newName);
		
		//change the name of the selected album
		albumList.getSelectionModel().getSelectedItem().setName(newName);
		
		//update the obsList
		obsList.clear();
		obsList = FXCollections.observableArrayList(albums);
		//albums=user.getAlbums();
		
		
		//set the view
		albumList.setItems(obsList);		
		
		//write it in for persistence/serialization
		ListUsers.write(ulist);
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
	/**
	 * Safely quit
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML protected void handleQuit(ActionEvent event) throws ClassNotFoundException, IOException{
		albumList.setItems(obsList);
		ListUsers.write(ulist);
		
		Platform.exit();
	}
	
}
