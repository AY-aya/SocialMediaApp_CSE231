
package socialapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SharePostController implements Initializable {
    @FXML
    ImageView profilePic;
    @FXML
    TextField shareText;
    @FXML
    Label userlabel;
    @FXML
    Button homePage;
    @FXML
    VBox box;
    @FXML
    VBox postbox;
    
    private Post post;
    private UserProfile user;
    
    public void setUser(UserProfile user, Post post, VBox postBox){
       this.user=new FileHandler().getSpecificUser(user.getUsername());
       this.post= post;
       this.postbox= postBox;
    }
    
    @Override
    public void initialize(URL url,ResourceBundle rb){        
       box.getChildren().add(postbox);
       int numChildren = postbox.getChildren().size();
       if (numChildren > 0) {
            // Remove the last node
            postbox.getChildren().remove(numChildren - 1);
        } else {
            System.out.println("postbox is empty, no nodes to remove");
        }
       
       if(user.getProfilepicture()!= null){
       	System.out.println("tesssst");
       	String imagePath = user.getProfilepicture();
       	Image image = new Image(new File(imagePath).toURI().toString());

           profilePic.setImage(image);
       }
       else{
           profilePic.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
       }
       Circle profileCircle= new Circle(100,100,100);
       profilePic.setClip(profileCircle);
       
       userlabel.setText(user.getUsername());
       
       
        //home page button
        homePage.setText("\uD83C\uDFE0");
        homePage.setStyle("-fx-text-fill: black;");
        homePage.setStyle("-fx-background-color:  #301934;");
        homePage.getStyleClass().add("Main-buttons");
        Tooltip tooltip1 = new Tooltip("home page");
        tooltip1.setStyle("-fx-font-size: 12");
        Tooltip.install(homePage, tooltip1);
        
       
    }
    public void share(ActionEvent e) throws IOException{
        String text= shareText.getText();
        post.sharePost(user, text, post);
        new FileHandler().editUserProfile(user);
        goToHomePage(e);
        
    }
    public void goToHomePage(ActionEvent e) throws IOException{
        homePage.getStyleClass().add("flashMain");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> homePage.getStyleClass().remove("flashMain"));
        pause.play();
        FeedController f = new FeedController();
        f.setUser(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("feed.fxml"));
        loader.setController(f); // Set the controller
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage= (Stage)homePage.getScene().getWindow();
        stage.setTitle("Chirp Lounge");
        stage.setScene(scene);
  
    }
   
    
   

}
