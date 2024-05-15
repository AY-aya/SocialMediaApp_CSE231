
package socialapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aya Yasser
 */
public class FriendProfileController extends PostDisplayer implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private UserProfile friend;
    @FXML
    private Label usernameLabel;
    @FXML
    private VBox biobox;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Label namelabel;
    @FXML
    private ImageView pic;
    @FXML
    private Label friendsIcon;
    @FXML
    private Label friendslabel;
    @FXML
    private Button friendButton;
    //3adelttt
    private Stage stage;

    public void setStage(Stage stage1) {
        this.stage = stage1;
    }
    // 3adlt henaa
    public void setDetails(UserProfile user, UserProfile friend){
        this.user= user;
        this.friend= friend;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        //setup friend profile
        usernameLabel.setText(friend.getUsername());
        if(friend.getProfilepicture()!= null){
        	System.out.println("tesssst");
        	String imagePath = friend.getProfilepicture();
        	Image image = new Image(new File(imagePath).toURI().toString());

        	pic.setImage(image);
        }
        else{
        	System.out.println("tewwwwwwwwwwwt");
        	pic.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
        }
        pic.setFitWidth(200);
        pic.setFitHeight(200);
        Circle clip = new Circle(100,100,100);
        pic.setClip(clip);

        //to display number of friends
        friendsIcon.setText("👥");
        if(friend.getFriends() != null)
            friendslabel.setText("friends: "+friend.getFriends().size());
        else
            friendslabel.setText("friends: "+0);
        //to display name and bio
        namelabel.setText("Name: "+friend.getName());
        friend.setBio("hello, this is my profile, enjoy!!");
        Text biotext = new Text(friend.getBio());
        biotext.setWrappingWidth(400);
        biotext.setFont(Font.font("Monotype Corsiva", 20));
        biobox.getChildren().add(biotext);
        biobox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        //to display posts
        reload();
        box.setFillWidth(true); 
        box.prefHeightProperty().bind(scrollPane.heightProperty());
        box.prefWidthProperty().bind(scrollPane.widthProperty());
        box.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        //to display friend button
        displayFriendshipButton();
    }    
   
    @Override
    public void reload(){
        //init();
        box.getChildren().clear();
        ArrayList<Post> p= friend.getPosts();
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
    }
    
     private void displayFriendshipButton() {
         
        if ( user.getFriends()!= null && user.getFriends().contains(friend.getUsername())) {
            // User is friends with the friend
            friendButton.setText("Friends ✅");
            friendButton.setStyle("-fx-background-color: #301934; -fx-text-fill: white;");
            friendButton.setOnAction(event -> {
                // Handle action for existing friends
                user.removeFriend(friend.getUsername());
                friend.removeFriend(user.getUsername());
                new FileHandler().editUserProfile(friend);
                new FileHandler().editUserProfile(user);
                displayFriendshipButton();
                friendslabel.setText("friends: "+friend.getFriends().size());
        
            });
        } else {
            // User is not friends with the friend
            friendButton.setText("Add Friend");
            friendButton.setStyle("-fx-background-color: white; -fx-text-fill: #301934;");
            friendButton.setOnAction(event -> {
                // Handle action for adding friend
                user.addFriend(friend.getUsername());
                friend.addFriend(user.getUsername());
                new FileHandler().editUserProfile(friend);
                new FileHandler().editUserProfile(user);
                displayFriendshipButton();
                friendslabel.setText("friends: "+friend.getFriends().size());
        
            });
        }
         

    }
}
