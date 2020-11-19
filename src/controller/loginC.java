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
			
			//create stock Album with stock photos
			stock.addAlbum("stockAlbum");
			stock.getAlbums().get(0).addPhoto("data/Murdoch.png");
			stock.getAlbums().get(0).getPics().get(0).addTag("Person", "Matt Murdoch");
			stock.getAlbums().get(0).getPics().get(0).setCaption("Daredevil's best character");
			
			stock.getAlbums().get(0).addPhoto("data/badBunny.png");
			stock.getAlbums().get(0).getPics().get(1).addTag("Person", "Bad Bunny");
			stock.getAlbums().get(0).getPics().get(1).setCaption("Lo mejor de Puerto Rico");

			stock.getAlbums().get(0).addPhoto("data/coffee.jpg");
			stock.getAlbums().get(0).getPics().get(2).addTag("Object", "Coffee");
			stock.getAlbums().get(0).getPics().get(2).setCaption("Delicious pour over");

			
			stock.getAlbums().get(0).addPhoto("data/greg.jpg");
			stock.getAlbums().get(0).getPics().get(3).addTag("Person", "Greg Schiano");
			stock.getAlbums().get(0).getPics().get(3).setCaption("Chop chop");

			stock.getAlbums().get(0).addPhoto("data/kyler.jpg");
			stock.getAlbums().get(0).getPics().get(4).addTag("Person", "Kyler Murray");
			stock.getAlbums().get(0).getPics().get(4).setCaption("Kyler Murray of the Cardinals");

			//serialize the info
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
				
				else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Warning");
					alert.setHeaderText("User Not in List");
					alert.setContentText("User Not in list, please create user from admin, or type in correct username");
					alert.showAndWait();
				
				
				}
				
			}
			
			}
			
		}

	}



