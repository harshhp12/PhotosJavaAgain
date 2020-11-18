package controller;

import java.io.File;
import java.io.IOException;

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
	

	
	/**
	 * 
	 * @param app_stage
	 */
	public void start(Stage app_stage) {	
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
	}
	
	public void setAlbum(Album selectedAlbum) {
		this.currentAlbum = selectedAlbum;
		
	}
	
	@FXML protected void handlePrev(ActionEvent event) throws IOException{
		currIndex--;
		if (currIndex < 0) {currIndex++;}
		else {
			view.setImage(new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString()));
		}
	}
	
	@FXML protected void handleNext(ActionEvent event) throws IOException{
		currIndex++;
		if(currIndex > currentAlbum.getPics().size()-1) {currIndex--;}
		else {
			view.setImage(new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString()));
		}
	}
	/**
	 * 
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
		//ListUsers.write(ulist);
		
		Platform.exit();
	}
	@FXML 
	protected void handleAddTag(ActionEvent event) throws ClassNotFoundException{}
	
	@FXML
	protected void handleDeleteTag(ActionEvent event) throws ClassNotFoundException{}
	
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
