
package socialapp;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Aya Yasser
 */
public class FeedController extends PostDisplayer implements Initializable{
    public static Stage stage;
    //private static UserProfile user;
    //private UserProfile sharingAuthor;
    //private Post sharingPost;
    
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private VBox searchResult;
    
    public void setUser(UserProfile u, Stage s){
        user= new FileHandler().getSpecificUser(u.getUsername());
        stage=s;
        System.out.println("1 "+ this.user);
        
    }
    
    public void setUser(UserProfile user){
        this.user= new FileHandler().getSpecificUser(user.getUsername());
        System.out.println("from home method "+ this.user);
    }
    
    public ArrayList<Post> getPosts(){
        ArrayList<Post> postssss = new ArrayList<>(user.getPosts());

        if(user.getFriends() != null){
            ArrayList<String> friends= user.getFriends();
            for(String friend: friends){
                UserProfile u= new FileHandler().getSpecificUser(friend);
                postssss.addAll(u.getPosts());
            }
        }
     // Sort posts in reverse order
        Collections.sort(postssss, Collections.reverseOrder());
        
        return postssss;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        reload();
        //search button
        searchButton.setText("\uD83D\uDD0D");
        
    }
  
    public void search(ActionEvent e){
        //to flash button when pressed
        searchButton.getStyleClass().add("flash");
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
        pause.setOnFinished(event -> searchButton.getStyleClass().remove("flash"));
        pause.play();
        searchResult.getChildren().clear();
        String username= searchBar.getText();
        UserProfile foundUser= new FileHandler().getSpecificUser(username);
        if(foundUser== null){
            Label error= new Label("user not found... \ntry another username");
            searchResult.getChildren().add(error);
        }
        else{
                //to display the go to profile button
        	ImageView profilePic = new ImageView();
            if(foundUser.getProfilepicture()!= null){
            	System.out.println("tesssst");
            	String imagePath = foundUser.getProfilepicture();
            	Image image = new Image(new File(imagePath).toURI().toString());

            	profilePic.setImage(image);
            }
            else{
            	System.out.println("tewwwwwwwwwwwt");
            	profilePic.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
            }
            profilePic.setFitWidth(60);
            profilePic.setFitHeight(60);
            Circle clip = new Circle(30,30,30);
            profilePic.setClip(clip);

            Button go= new Button();
            go.setGraphic(profilePic);
            Label nameLabel= new Label(foundUser.getUsername());
            
            if(!foundUser.getUsername().equals(user.getUsername())){
                go.setOnAction(eh -> {
                //handle scene switching
                System.out.println("go to user profile");
                FriendProfileController f = new FriendProfileController();
                f.setDetails(user, foundUser);
                //f.setStage(stage1);

                // Load the FXML file with the controller instance set
                FXMLLoader loader = new FXMLLoader(getClass().getResource("friendProfile.fxml"));
                loader.setController(f); // Set the controller instance
                
                try {
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    // Load the CSS file
                    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                    Stage stage= (Stage) homePage.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Friend Profile");
                    stage.show();
/////
                } catch (IOException ex) {
                    Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            });
            }
            else{
                go.setOnAction(eh->{
                    try {
                        goToProfile(eh);
                    } catch (IOException ex) {
                        Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            searchResult.getChildren().addAll(go, nameLabel);
            
        	
        }
        searchResult.setStyle("-fx-background-color:  #AA98A9;");
    }
    
    @Override
    public void reload(){
        //get user posts
        box.getChildren().clear();
        ArrayList<Post> p= getPosts();
        
        if(p.isEmpty()== false){
            for(Post post: p){
            if(post.getOriginalPostID() == 0){
                VBox postBox = createPostBox(post);
                box.getChildren().add(postBox);
            }
            else{
                VBox postBox = displaySharedPost(post);
                box.getChildren().add(postBox);
            }
            }
        }
        else{
            Label l= new Label("find your friends using search and start creating posts");
            box.getChildren().add(l);
        }
    }
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
}
    

