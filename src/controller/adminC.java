package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Callback;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.*;

/**
 * This is the controller for the admin page
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class adminC implements LogOff{
	
	@FXML TableView<User> userList;
	@FXML TableColumn<User, String> usernames;
	private ObservableList<User> obsList;
	private List<User> users = new ArrayList<User>();
	private ListUsers ulist;
	/**
	 * Display the users list for the administrator
	 * @param mainStage
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void start(Stage mainStage) throws ClassNotFoundException, IOException{
		//deserialize data
		ulist = ListUsers.read();
		users = ulist.returnUserList();
		
		//create new observable list backed by an arraylist
		obsList = FXCollections.observableArrayList(users);
		
		//set the tableview
		userList.setItems(obsList);
	}
	/**
	 * Use the interface method to logout 
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
	
	@FXML TextField username;
	
	/**
	 * Create new user upon button click action
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	@FXML protected void handleCreate(ActionEvent event) throws ClassNotFoundException, IOException{
		//create the user
		String usernm = username.getText();
		User newUsr = new User(usernm);
		
		//add the user to the ListUser
		obsList.add(newUsr);
		users.add(newUsr);
		
		//write it in for persistence
		ListUsers.write(ulist);
		
		//update the tableview
		userList.setItems(obsList);
		//usernames.setCellFactory(arg0);
	
		//convert user data into cells
		//userList.
		
		if(obsList.size()==1) {userList.getSelectionModel().select(0);}
		else {}
	}
	
	/**
	 * Handle the deletion of a user upon pressing delete
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML
	protected void handleDelete(ActionEvent event) throws ClassNotFoundException{
		
	}
}
