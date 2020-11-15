package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class adminC implements LogOff{
	
	
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
}
