package controller;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import app.Photos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

/**
 * This is the controller used to login upon startup
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class loginC{

	private Stage primaryStage;

	public void changeScene(String fxml) throws IOException{
		Parent pane = FXMLLoader.load(
				getClass().getResource(fxml));

		Scene scene = new Scene( pane );
		primaryStage.setScene(scene);
	}



	@FXML TextField username;
	@FXML Button loginButton;
	
	/**
	 * Handle the action upon pressing login
	 * Either enter admin mode or user mode
	 * @param event
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	@FXML private void handleLogin(ActionEvent event) throws IOException, ClassNotFoundException {    
		Parent parent;
		//handle the login here
		Button login = (Button)event.getSource();

		//Grab the list of users from the .dat if there is a list of users
		ListUsers ulist = null;
		try {
			ulist = ListUsers.read();
		} catch (IOException e1) {
			//If there is no current list, create a default stock one
			User stock = new User("stock");
			
			ulist = new ListUsers();
			ulist.addUserToList(stock);
			ListUsers.write(ulist);
		}
		
		if(login == loginButton) {
			String usernm = username.getText();

			if(usernm.equals("admin")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin.fxml"));
				parent = (Parent) loader.load();
				adminC controller = loader.getController();
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				//call start method to setup showing the usernames
				controller.start(stage);
				stage.setScene(scene);
				stage.show();

			}
			
			else {
				if(ulist.isUserInList(usernm)) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user.fxml"));
					parent = (Parent) loader.load();
					userPageC controller = loader.getController();
					
					//set the current user that is logging in
					controller.setUlist(ulist);
					controller.setUser(ulist.getUserByUsername(usernm));
					
					//setup the scene
					Scene scene = new Scene(parent);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					//call start method to setup showing the albums
					controller.start(stage);
					stage.setScene(scene);
					stage.show();
				}
				
				else {System.out.println("User is not in list, please create a user(fom admin) or type in correct username");}
				
			}
			
			}
			
		}

	}



