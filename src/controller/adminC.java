package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class adminC implements LogOff{
	
	
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
	
	@FXML TextField username;
	@FXML protected void handleCreate(ActionEvent event) throws ClassNotFoundException{
		String usernm = username.getText();
		
	}
}
