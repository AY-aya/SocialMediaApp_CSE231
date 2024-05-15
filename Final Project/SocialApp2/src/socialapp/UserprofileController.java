package socialapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

    public class UserprofileController extends PostDisplayer implements Initializable {
    @FXML
    private ImageView imageView;
    
    @FXML
    private Label biolabel;
    @FXML
    private TextField biotext;
    @FXML
    private Button uploadImage;
    //@FXML
    //private UserProfile user;
    @FXML
    private ListView<String> friendListView; 
       
    @FXML
    private Button savebutton;
    
    
    public void loadPosts() {
        ArrayList<Post> posts = user.getPosts();
        
        // Clear existing content in box
        box.getChildren().clear();
        
        for (Post post : posts) {
            if (post.getOriginalPostID() == 0) {
                VBox postBox = createPostBox(post);
                box.getChildren().add(postBox);
            } else {
                VBox postBox = displaySharedPost(post);
                box.getChildren().add(postBox);
            }
        }
    }
       
       public void setDetails(UserProfile u){
        user= u;
    }   

      // Method to display the friend list for a specific UserProfile
      private void displayFriendList(UserProfile userProfile) {
          
          if (userProfile != null) {
              //System.out.println("4444444444444444");
              // Call the viewFriendList() method to retrieve the friend list
              ArrayList<String> friends = userProfile.getFriends();
              if (friends != null && !friends.isEmpty()) {
                  friendListView.getItems().addAll(friends);
                 // System.out.println("555555555555555");
              }
              else
                  friendListView.getItems().add("no friends found");
          }
      }
    @FXML
    private void friendsview() {
              // Clear the list view
              friendListView.getItems().clear();
             
              displayFriendList(user);
              
          }
    
public void uploadImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image File");

    // Set the initial directory to the user's home directory
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    // Add filters for image files
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );

    Stage stage = (Stage) imageView.getScene().getWindow();

    // Show the file chooser dialog
    List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

    if (selectedFiles != null && !selectedFiles.isEmpty()) {
        for (File file : selectedFiles) {
            System.out.println("Selected file: " + file.getAbsolutePath());
        }
        
        try {
            // Load the first selected image file
            File selectedFile = selectedFiles.get(0);
            FileInputStream inputStream = new FileInputStream(selectedFile);
            Image image = new Image(inputStream);
            
            // Set the profile picture path
            user.setProfilepicture(selectedFile.getAbsolutePath());
            new FileHandler().editUserProfile(user);
            init();
            
            // Display the image in the imageView
            imageView.setImage(image);

            // Apply circular clip to the imageView
            applyCircularClip();
            
            // Close the input stream
            inputStream.close();
        } catch (Exception e) {
            // Handle any exceptions
            showErrorAlert("Error loading image: " + e.getMessage());
        }
    } else {
        System.out.println("No files selected.");
    }
}
      private void applyCircularClip() {
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        Circle clip = new Circle(60,60,60);
        imageView.setClip(clip);

      }

      private void showErrorAlert(String message) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText(message);
          alert.showAndWait();
      }
      
      private void initialize() {
        // Load bio from UserProfile or initialize with empty string
        String bioText = user != null ? user.getBio() : "";
        if (bioText.isEmpty()) {
            biolabel.setText("Bio: Write your bio here...");
            
        } else {
            biolabel.setText("Bio: " + bioText);
        }
    }

      // Method to save or update the bio
    
     @FXML
    public void saveBio(ActionEvent e) {
        // Get the updated bio text from the TextField
        String updatedBio = biotext.getText();
        user.setBio(updatedBio);
        new FileHandler().editUserProfile(user);
        initialize();
        // Update the bio in the UserProfile or wherever appropriate
        // For now, let's just print the bio
        System.out.println("Bio saved/updated: " + updatedBio);
    }
     
     @FXML

     @Override
    public void openCommentWindow(Post post) {
    try {
        // Load the FXML file for the comment window
        CommentController c = new CommentController();
        
        c.setDetails(post, user, this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("comment.fxml"));
        loader.setController(c);
        Parent x = loader.load();
        // Create a new stage for the Schedule scene
        Stage commentStage = new Stage();
        commentStage.setResizable(false);
        Scene y = new Scene(x);
        y.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        commentStage.setScene(y);
        commentStage.setTitle("comments");
        // Show the new stage
        commentStage.show();
     } catch (IOException e) {
        e.printStackTrace();
        // Handle any exceptions
     }

}
    @Override
    public void reload(){
        loadPosts();
    } 
   
 
    ///// SWITCHING SCENES CODESSS!!!!!!!!!
    // when homepage button is pressed
    public void SwitchtoHomepage(ActionEvent event) {
    try {
        Parent root =FXMLLoader.load(getClass().getResource("feed.fxml")); 
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.setTitle("HomePage");
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    // when logout button is pressed
     public void switchtologinscene(ActionEvent event) {
    try {
        Parent root =FXMLLoader.load(getClass().getResource("login.fxml")); 
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.setTitle("login");
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String bioText = user != null ? user.getBio() : "";
        if (bioText != null && bioText.isEmpty()) {
            biolabel.setText("Bio: Write your bio here...");
        } else {
            biolabel.setText("Bio: " + bioText);
        }
        
        //loadAndDisplayFriends();
        reload();
       if (user != null) {
    if (user.getProfilepicture() != null) {
        try {
            imageView.setImage(new Image(new File(user.getProfilepicture()).toURI().toString()));
        } catch (Exception e) {
            // If there's an error loading the profile picture, load the default picture
            imageView.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
            System.err.println("Error loading profile picture: " + e.getMessage());
        }
    } else {
        // If profile picture path is null, load the default picture
        imageView.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
    }
} else {
    // If user is null, set a default profile picture
    imageView.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
}
        applyCircularClip();
        init();
    }
  
    }
