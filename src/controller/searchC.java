package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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


}
