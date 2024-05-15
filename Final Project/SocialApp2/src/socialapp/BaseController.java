/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
//comment
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
    
    public void init(){
        //to display the go to profile button
        ImageView imageView = new ImageView();
        if(user.getProfilepicture()!= null){
        	System.out.println("tesssst");
            imageView.setImage(new Image(getClass().getResourceAsStream(user.getProfilepicture())));
        }
        else{
        	System.out.println("tewwwwwwwwwwwt");
            imageView.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
        }
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        Circle clip = new Circle(30,30,30);
        imageView.setClip(clip);
        picButton.setGraphic(imageView);
        
        //logout button
        logoutButton.setText(/*"\uD83D\uDD19"*/ "\uD83D\uDEAA");
        logoutButton.getStyleClass().add("Main-buttons"); // Add a custom style class for styling
        
        //home page button
        homePage.setText("\uD83C\uDFE0");
        homePage.getStyleClass().add("Main-buttons");
        
        //create post button
        createPost.setText("\uD83D\uDCDD");
        createPost.getStyleClass().add("Main-buttons");
        
        Tooltip tooltip = new Tooltip("logout");
        tooltip.setStyle("-fx-font-size: 12");
        Tooltip.install(logoutButton, tooltip);
        Tooltip tooltip1 = new Tooltip("home page");
        tooltip1.setStyle("-fx-font-size: 12");
        Tooltip.install(homePage, tooltip1);
        Tooltip tooltip2 = new Tooltip("create post");
        tooltip2.setStyle("-fx-font-size: 12");
        Tooltip.install(createPost, tooltip2);
        Tooltip tooltip3 = new Tooltip("go to profile");
        tooltip3.setStyle("-fx-font-size: 12");
        Tooltip.install(picButton, tooltip3);
        
        
    }
    
    public void goToProfile(ActionEvent e) throws IOException{
        System.out.println("pic pressed");
        UserprofileController u = new UserprofileController();
        u.setDetails(user);
        // Load the FXML file with the controller instance set
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Userprofile.fxml"));
        loader.setController(u); // Set the controller instance
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage= (Stage)homePage.getScene().getWindow();
        stage.setTitle("Chirp Lounge");
        //double w= stage.getWidth();
        //double h= stage.getHeight();
        stage.setScene(scene);
        //stage.setWidth(w);
        //stage.setHeight(h);
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
        //loader.setController(f); // Set the controller instance
        Parent root = loader.load();
     
            
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("Userprofile.fxml"));
        //Parent root = loader.load();
        Scene scene = new Scene(root);
        //double w= scene.getWidth();
        //double h= scene.getHeight();
        // Load the CSS file
        scene.getStylesheets().add(getClass().getResource("R&L.css").toExternalForm());
        //stage.setWidth(w);
        //stage.setHeight(h);
        stage.setScene(scene);
        stage.setTitle("Chirp Lounge");
        //stage.setResizable(false);
        //
        stage.show();
        
    }

    public void createPosts(ActionEvent e){
        createPost.getStyleClass().add("flashMain");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> createPost.getStyleClass().remove("flashMain"));
        pause.play();
        
        try {
        	// Load the FXML file with the controller instance set
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
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> homePage.getStyleClass().remove("flashMain"));
        pause.play();
        FeedController f = new FeedController();
        f.setUser(user);
        // Load the FXML file with the controller instance set
        FXMLLoader loader = new FXMLLoader(getClass().getResource("feed.fxml"));
        loader.setController(f); // Set the controller instance
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage= (Stage)homePage.getScene().getWindow();
        stage.setTitle("Chirp Lounge");
        stage.setScene(scene);
  
    }
    
    
    
    
}
