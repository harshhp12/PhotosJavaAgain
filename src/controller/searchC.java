package controller;

import java.io.IOException;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ListUsers;


/**
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class searchC implements LogOff {
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
	 * Safely quit
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML protected void handleQuit(ActionEvent event) throws ClassNotFoundException, IOException{
		Platform.exit();

	}
	/**
	 * Go back to user page
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML protected void handleBack(ActionEvent event) throws ClassNotFoundException, IOException{
		Parent parent;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user.fxml"));
		parent = (Parent) loader.load();
		//searchC controller = loader.getController();
		
		//setup the scene
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		//call start method to setup showing the albums
		//controller.start(stage);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	protected void handleCreate(ActionEvent event) throws ClassNotFoundException, IOException{}
	
	@FXML
	protected void handleSearchByTags(ActionEvent event) throws ClassNotFoundException, IOException{}

	@FXML
	protected void handlePrev(ActionEvent event) throws ClassNotFoundException, IOException{}
	
	@FXML
	protected void handleNext(ActionEvent event) throws ClassNotFoundException, IOException{}




	
	
}
