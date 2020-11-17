package controller;

import java.io.IOException;
import model.*;

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
	
	
}
