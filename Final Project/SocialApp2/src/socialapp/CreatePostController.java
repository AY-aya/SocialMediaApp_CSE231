package socialapp;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;



public class CreatePostController implements Initializable {
    private UserProfile user;
    private ImageView imageView;
    @FXML
    private TextField postTextArea; // Add TextArea
    @FXML
    private ImageView imageview;
    @FXML
    private Button uploadbutton;
    @FXML
    private Button backbutton;
    private File selectedFile;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    } 
    public void setUser(UserProfile u){
        user= u;
    }
    
 @FXML
public void uploadImage(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image File");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
    Stage stage = (Stage) uploadbutton.getScene().getWindow(); // Use uploadbutton instead of imageView
    List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
    if (selectedFiles != null && !selectedFiles.isEmpty()) {
        selectedFile = selectedFiles.get(0); // Assume only one file is selected
        try {
            Image image = new Image(selectedFile.toURI().toString());
            imageview.setImage(image); // Use imageview instead of imageView
        } catch (Exception e) {
            showErrorAlert("Error loading image: " + e.getMessage());
        }
    }
}

      public void createPost(ActionEvent event) {
        String postText = postTextArea.getText();
        /*Image postImage = imageview.getImage();*/
        
        if(selectedFile != null) {
        	//String img= postImage.);
            //System.out.println(img);
            ArrayList<String> pics= new ArrayList<>();
            pics.add(selectedFile.getAbsolutePath());
            // Assuming Post is a class representing a post
            //Post post=new Post(user.getUsername(),postText, pics);
            user.createpost(user.getUsername(),postText, pics);
            
        }
        else {
        	user.createpost(user.getUsername(), postText);
        }
        FileHandler file =new FileHandler();
        file.editUserProfile(user);
       
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    public void goToHomePage(ActionEvent e) throws IOException{
        backbutton.getStyleClass().add("flashMain");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> backbutton.getStyleClass().remove("flashMain"));
        pause.play();
        FeedController f = new FeedController();
        f.setUser(user);
        // Load the FXML file with the controller instance set
        FXMLLoader loader = new FXMLLoader(getClass().getResource("feed.fxml"));
        loader.setController(f); // Set the controller instance
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage= (Stage)backbutton.getScene().getWindow();
        stage.setTitle("Chirp Lounge");
        stage.setScene(scene);
  
    }
    
    

}

