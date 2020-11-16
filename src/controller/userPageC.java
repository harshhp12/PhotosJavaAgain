package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class userPageC implements LogOff{
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
}
