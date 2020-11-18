package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ListUsers;
import model.Picture;
import model.Tag;
import model.User;


/**
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class searchC implements LogOff {
	
	@FXML DatePicker fromDate;
	@FXML DatePicker toDate;
	@FXML ImageView view;
	@FXML TextField caption;
	@FXML TableView<Tag> tags;
	@FXML TableColumn <Tag, String> key;
	@FXML TableColumn<Tag, String> value;
	@FXML TextField dateText;
	
	int currIndex;
	List<Picture> searchResults = null;
	
	//sets the current user
	private User currUser;
	
	/**
	 * Use the interface to logout
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML 
	protected void handleLogout(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
	
	public void setUser(User user) {
		this.currUser = user; 
	}
	
	/**
	 * Safely quit
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML protected void handleQuit(ActionEvent event) throws ClassNotFoundException, IOException{
		Platform.exit();

	}
	/**
	 * Go back to user page
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML protected void handleBack(ActionEvent event) throws ClassNotFoundException, IOException{
		Parent parent;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user.fxml"));
		parent = (Parent) loader.load();
		userPageC controller = loader.getController();
		
		//setup the scene
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		//call start method to setup showing the albums
		controller.start(stage);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Create a new album with photos from search result
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleCreate(ActionEvent event) throws ClassNotFoundException, IOException{}
	
	/**
	 * Search by tags
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleSearchByTags(ActionEvent event) throws ClassNotFoundException, IOException{}

	@FXML
	protected void searchByDates(ActionEvent event) throws ClassNotFoundException, IOException{
		LocalDate startD = fromDate.getValue();
		LocalDate endD = toDate.getValue();
		
		//empty input
		if (startD == null || endD == null) {}
		
		//invalid range since end date is before start
		else if(endD.isBefore(startD)) {
			
		}
		
		 currIndex =0;
		 searchResults =
		 this.currUser.getPhotosInDateRange(startD.getYear(), startD.getMonthValue(), startD.getDayOfMonth(), endD.getYear(), endD.getMonthValue(), endD.getDayOfMonth());
		 
		 populatePhotoViewer();
		
	}
	/**
	 * Function to set all the fields
	 */
	protected void populatePhotoViewer() {
		//if the album selected is empty
		if(searchResults.size()<1) {
			
			System.out.println("Nopics");
			view.setImage(null); 
		
			caption.setText("");
		
			return;
			}
		
		//specify how to fill in the tag value column
		value.setCellValueFactory(new Callback<CellDataFeatures<Tag, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Tag, String> u) {
			return new SimpleStringProperty(u.getValue().returnValue());}});
				
		//specify how to fill in the tag key column
		key.setCellValueFactory(new Callback<CellDataFeatures<Tag, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Tag, String> u) {
			return new SimpleStringProperty(u.getValue().returnName());}});
		
		Image img = new Image(new File(searchResults.get(currIndex).getPhotoPath()).toURI().toString());
		view.setImage(img);
		
		ObservableList<Tag> obsList = FXCollections.observableArrayList(searchResults.get(currIndex).getTags());
		
		//set tags
		tags.setItems(obsList);
		
		//set caption
		caption.setText(searchResults.get(currIndex).getCaption());
		
		
		//tagList = searchResults.get(currIndex).getTags();
		
		//set date
		dateText.setText(searchResults.get(currIndex).getDate().toString());
		
	}
	
	/**
	 * Go through the previous picture in search results
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handlePrev(ActionEvent event) throws ClassNotFoundException, IOException{
		currIndex--;
		if (currIndex < 0) {currIndex++;}
		
		else {
			populatePhotoViewer();
		}
	}
	
	/**
	 * Go through the next picture in search results
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleNext(ActionEvent event) throws ClassNotFoundException, IOException{
		currIndex++;
		if(currIndex > searchResults.size()-1) {currIndex--;}
		else {
			populatePhotoViewer();
		}
	}

 

	
	
}
