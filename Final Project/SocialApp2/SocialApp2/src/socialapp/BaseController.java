
package socialapp;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Aya Yasser
 */
public abstract class BaseController {
    protected UserProfile user;
    @FXML
    protected Button picButton;
    @FXML
    protected Button createPost;
    @FXML
    protected Button homePage;
    @FXML
    protected Button logoutButton;
    
    
    public void goToProfile(ActionEvent e) throws IOException{
        System.out.println("pic pressed");
        UserprofileController u = new UserprofileController();
        u.setDetails(user);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Userprofile.fxml"));
        loader.setController(u); // Set the controller
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage= (Stage)homePage.getScene().getWindow();
        stage.setTitle("Chirp Lounge");
        stage.setScene(scene);
        
    }
    
    public void logout(ActionEvent e) throws IOException{
        //to flash button when pressed
        logoutButton.getStyleClass().add("flashMain");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> logoutButton.getStyleClass().remove("flashMain"));
        pause.play();
        // Handle logout action here
        System.out.println("pressed");
        Stage stage = (Stage)homePage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        Parent root = loader.load();
     
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("R&L.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Chirp Lounge");      
        stage.show();    
    }

    public void createPosts(ActionEvent e){
        createPost.getStyleClass().add("flashMain");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> createPost.getStyleClass().remove("flashMain"));
        pause.play();      
        try {      	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postcreation.fxml"));           
            Parent root = loader.load();            
            CreatePostController c= loader.getController();            
            c.setUser(user);            
            Stage currentStage = (Stage) homePage.getScene().getWindow();
            double w= currentStage.getWidth();
            double h= currentStage.getHeight();
            Scene scene = new Scene(root);            
            currentStage.setScene(scene);          
            currentStage.setWidth(w);
            currentStage.setHeight(h);
            currentStage.setTitle("Create post");
            currentStage.show();
        } catch (IOException eh) {
            eh.printStackTrace();
        }       
    }
    
    public void goToHomePage(ActionEvent e) throws IOException{
        homePage.getStyleClass().add("flashMain");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration 
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
