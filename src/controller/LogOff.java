package controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public interface LogOff {
	default void logout(ActionEvent event) throws ClassNotFoundException {
    	Parent parent;
    	 
		try {
						
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			parent = (Parent) loader.load();
		
			Scene scene = new Scene(parent);
							
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();			             
			app_stage.setScene(scene);
			app_stage.show();  
						
			 
		
		} catch (IOException e) {
			//catch block
			e.printStackTrace();
		}         
         
	}
}
