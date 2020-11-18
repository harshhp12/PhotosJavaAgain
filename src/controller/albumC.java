package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import javafx.stage.FileChooser;

public class albumC implements LogOff{
	
	//the current Album
	private Album currentAlbum;
	
	//the index of current pic
	private int currIndex;
	
	@FXML TableView<Tag> tags;
	
	@FXML ImageView view;
	
	@FXML TableColumn <Tag, String> key;
	@FXML TableColumn<Tag, String> value;
	
	@FXML TextField caption;
	
	//the list of all users so we can update serialization
	private ListUsers ulist;
	
	//backend list to get the arrayList of tags from the user data
	private List<Tag> tagList = new ArrayList<Tag>();
	
	/**
	 * 
	 * @param app_stage
	 */
	public void start(Stage app_stage) {
		//specify how to fill in the tag value column
		value.setCellValueFactory(new Callback<CellDataFeatures<Tag, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Tag, String> u) {
			         return new SimpleStringProperty(u.getValue().returnValue());}
				  });
		
		//specify how to fill in the tag key column
		key.setCellValueFactory(new Callback<CellDataFeatures<Tag, String>, ObservableValue<String>>() {
				     public ObservableValue<String> call(CellDataFeatures<Tag, String> u) {
					         return new SimpleStringProperty(u.getValue().returnName());}
						  });
		
		
		populatePhotoViewer();
		currIndex=0;
		
	}
	
	/**
	 * the ImageView
	 * @param currentAlbum
	 */
	protected void populatePhotoViewer() {
		//if the album selected is empty
		if(currentAlbum.getPics().size()<1) return;
		
		Image img = new Image(new File(currentAlbum.getPics().get(0).getPhotoPath()).toURI().toString());
		view.setImage(img);
		
		ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(0).getTags());
		tags.setItems(obsList);
		
		tagList = currentAlbum.getPics().get(currIndex).getTags();
	}
	
	/**
	 * Use this to set the current album we are viewing
	 * @param selectedAlbum
	 */
	public void setAlbum(Album selectedAlbum) {
		this.currentAlbum = selectedAlbum;
		
	}
	
	/**
	 * Use this for serializing data
	 * @param ulist
	 */
	public void setUsers(ListUsers ulist) {
		this.ulist = ulist;
	}
	
	/**
	 * The button for previous picture
	 * @param event
	 * @throws IOException
	 */
	@FXML protected void handlePrev(ActionEvent event) throws IOException{
		currIndex--;
		if (currIndex < 0) {currIndex++;}
		else {
			view.setImage(new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString()));
			ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
			tags.setItems(obsList);
		}
	}
	
	/**
	 * The button for next picture
	 * @param event
	 * @throws IOException
	 */
	@FXML protected void handleNext(ActionEvent event) throws IOException{
		currIndex++;
		if(currIndex > currentAlbum.getPics().size()-1) {currIndex--;}
		else {
			view.setImage(new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString()));
		
			ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
			tags.setItems(obsList);
		}
	}
	
	/**
	 * Add a new photo w file path
	 * @param event
	 * @throws IOException
	 */
	@FXML protected void handleAdd(ActionEvent event) throws IOException{
		String filepath = "";
		FileChooser fileChooser = new FileChooser();
		
		//Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterGIF);
		
		//Show open file dialog
		File imgfile = fileChooser.showOpenDialog(null);
		if(imgfile == null) return;
		
		//Get the absolute path of image file
		filepath = imgfile.getAbsolutePath();
		Picture newPhoto = new Picture(filepath);
		
		//add photo to the current item
	}
	
	/**
	 * Safely quit
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML protected void handleQuit(ActionEvent event) throws ClassNotFoundException, IOException{
		//albumList.setItems(obsList);
		ListUsers.write(ulist);
		
		Platform.exit();
	}
	
	/**
	 * Add a new tag and update the list
	 * @param event
	 * @throws ClassNotFoundException
	 */
	@FXML 
	protected void handleAddTag(ActionEvent event) throws ClassNotFoundException{
		
		
	}
	
	/**
	 * Delete the tag and update the list, view
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleDeleteTag(ActionEvent event) throws ClassNotFoundException, IOException{		
		
		//get selected tag
		Tag selected = tags.getSelectionModel().getSelectedItem();
		ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
		//tagList = currentAlbum.getPics().get(currIndex).getTags();
		
		//remove it from our lists
		obsList.remove(selected);
		tagList.remove(currIndex);
		
		//update the viewing
		tags.setItems(obsList);
		
		//serialize data
		ListUsers.write(ulist);
	}
	
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
