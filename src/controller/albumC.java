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
import javafx.scene.control.ChoiceBox;
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

/**
 * @author Rithvik Aleshetty
 * @author Harsh Patel
 *
 */
public class albumC implements LogOff{
	
	//the current Album
	private Album currentAlbum;
	
	//the index of current pic
	private int currIndex;
	
	//sets the current user
	private User currUser;
	
	//Use the ID's from the FXML
	@FXML TableView<Tag> tags;
	@FXML ImageView view;
	@FXML TableColumn <Tag, String> key;
	@FXML TableColumn<Tag, String> value;
	@FXML TextField caption;
	@FXML TextField keyText;
	@FXML TextField valueText;
	@FXML TextField captionText;
	@FXML ChoiceBox<Album> albumDrop;
	@FXML TextField dateField;
	
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
		
		//show the photo
		currIndex=0;
		populatePhotoViewer();
		
		//set the albums drop down
		ObservableList<Album> albumsList = FXCollections.observableArrayList(currUser.getAlbums());
		albumDrop.setItems(albumsList);
		
		//set the date
		if(currentAlbum.getPics().size()>0) {
			dateField.setText(currentAlbum.getPics().get(currIndex).getDate().toString());}
	}
	
	/**
	 * the ImageView
	 * @param currentAlbum
	 */
	protected void populatePhotoViewer() {
		//if the album selected is empty
		if(currentAlbum.getPics().size()<1) {System.out.println("Nopics"); return;}
		
		Image img = new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString());
		view.setImage(img);
		
		ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
		tags.setItems(obsList);
		caption.setText(currentAlbum.getPics().get(currIndex).getCaption());
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
	 * Set the current user
	 * @param user
	 */
	public void setAUser (User user) {
		this.currUser = user;
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
		
		//decrement the index
		currIndex--;
		
		//if we have reached the first photo
		if (currIndex < 0) {currIndex++;}
		
		else {
			//set the image
			view.setImage(new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString()));
			
			//set the tags
			ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
			tags.setItems(obsList);
			tagList = currentAlbum.getPics().get(currIndex).getTags();
			
			//set the caption
			caption.setText(currentAlbum.getPics().get(currIndex).getCaption());
			
			//set the date
			dateField.setText(currentAlbum.getPics().get(currIndex).getDate().toString());
		}
	}
	
	/**
	 * The button for next picture
	 * @param event
	 * @throws IOException
	 */
	@FXML protected void handleNext(ActionEvent event) throws IOException{
		//increase the index
		currIndex++;
		
		//if we reached the last photo
		if(currIndex > currentAlbum.getPics().size()-1) {currIndex--;}
		else {
			//set the image thumbnail
			view.setImage(new Image(new File(currentAlbum.getPics().get(currIndex).getPhotoPath()).toURI().toString()));
			
			//set the tag list
			ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
			tags.setItems(obsList);
			tagList = currentAlbum.getPics().get(currIndex).getTags();
			
			//set the caption
			caption.setText(currentAlbum.getPics().get(currIndex).getCaption());
			
			//set the date
			dateField.setText(currentAlbum.getPics().get(currIndex).getDate().toString());
			
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
		currentAlbum.addPhoto(filepath);
		
		ListUsers.write(ulist);
		
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
	 * @throws IOException 
	 */
	@FXML 
	protected void handleAddTag(ActionEvent event) throws ClassNotFoundException, IOException{
		
		ObservableList<Tag> obsList = FXCollections.observableArrayList(currentAlbum.getPics().get(currIndex).getTags());
		Tag newTag = new Tag(keyText.getText(), valueText.getText());
		
		obsList.add(newTag);
		tagList.add(newTag);
		
		//update the viewing
		tags.setItems(obsList);
		
		//serialize data
		ListUsers.write(ulist);
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
	
	/**
	 * remove photo from album
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	@FXML
	protected void removePhoto(ActionEvent event) throws ClassNotFoundException, IOException{
		//get rid of the pic from the current Album
		currentAlbum.getPics().remove(currIndex);
		if(currIndex > 0) {
			currIndex--;
			populatePhotoViewer();
		}
		else {
			populatePhotoViewer();
		}
		
		//serialize
		ListUsers.write(ulist);
	}
	
	/**
	 * add or change caption
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleCaption(ActionEvent event) throws ClassNotFoundException, IOException{
		//set the caption
		currentAlbum.getPics().get(currIndex).setCaption(captionText.getText());
		
		caption.setText(currentAlbum.getPics().get(currIndex).getCaption());
		
		//serialize
		ListUsers.write(ulist);
		
	}
	
	/**
	 * Handle what happens on click of copy button
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleCopy(ActionEvent event) throws ClassNotFoundException, IOException{
		//want to obtain selected album and add the current photo to it
		Album selected = albumDrop.getSelectionModel().getSelectedItem();
		Picture copiedPic = selected.addPhoto(currentAlbum.getPics().get(currIndex).getPhotoPath());
		
		//set the caption of the pic in the other album
		copiedPic.setCaption(currentAlbum.getPics().get(currIndex).getCaption());
		
		//set the tags of the pic in the other album
		copiedPic.setTags(currentAlbum.getPics().get(currIndex).getTags());
		//set the date of the pic in the other album
		
		//serialize data
		ListUsers.write(ulist);
	}
	/**
	 * Handle what happens on click of the move button
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	protected void handleMove(ActionEvent event) throws ClassNotFoundException, IOException{
		//want to obtain selected album and add the current photo to it
		Album selected = albumDrop.getSelectionModel().getSelectedItem();
		Picture copiedPic = selected.addPhoto(currentAlbum.getPics().get(currIndex).getPhotoPath());
				
		//set the caption of the pic in the other album
		copiedPic.setCaption(currentAlbum.getPics().get(currIndex).getCaption());
				
		//set the tags of the pic in the other album
		copiedPic.setTags(currentAlbum.getPics().get(currIndex).getTags());
		//set the date of the pic in the other album
		
		//delete the photo from our album
		currentAlbum.getPics().remove(currIndex);
		if(currIndex > 0) {
			currIndex--;
			populatePhotoViewer();
		}
		else {
			populatePhotoViewer();
		}
		
		//serialize data
		ListUsers.write(ulist);	
	}
}

	