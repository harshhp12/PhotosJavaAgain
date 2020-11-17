package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * The controller for the user home page
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class userPageC implements LogOff{
	/**
	 * Use the interface to logout
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
}
