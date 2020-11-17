package app;
import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ListUsers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.loginC;


/**
 * All this class does is start up the initial scene (login)
 * 
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 */
public class Photos extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//start off the .dat file with the list
		//ListUsers masterUserList = ListUsers.read();
		
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("/view/login.fxml"));
		BorderPane root = (BorderPane)loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); 

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
}