
package socialapp;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
import javafx.stage.Stage;

public class CommentController implements Initializable{
    //Post post=new Post("jana","ay haga");
    //UserProfile user=new UserProfile("username", "pass", "email", "name");
    ImageView profilePic;
    UserProfile currentUser;
    Post currentPost;
    PostDisplayer f;
    @FXML
    VBox box;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    AnchorPane pane;
    @FXML
    TextField commenttext;
    Label authorLabel;    
   
    public void setDetails(Post p, UserProfile u, PostDisplayer ff){
        currentUser= u;
        currentPost= p;
        f=ff;
    }   
    public void initialize(URL url, ResourceBundle rb) {    
         ArrayList<Comment> comments = currentPost.getComments();
        if (currentPost.getCommentcount() != null) {
         for (Comment comment : comments) {
         VBox commentBox = CreateCommentBox(comment);        
         box.getChildren().add(commentBox);
         }
        }
        box.setFillWidth(true); 
        //box.prefHeightProperty().bind(ScrollPane.heightProperty());
        //box.prefWidthProperty().bind(ScrollPane.widthProperty());
        //box.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
    }
    
    private VBox CreateCommentBox(Comment comment){
       VBox commentBox = new VBox(1); 
        // Display author and profile pic
        HBox authorBox = new HBox(5);
        authorBox.setAlignment(Pos.CENTER_LEFT); 
        
        VBox details= new VBox(2);
        authorLabel = new Label(comment.getAuthor());
        authorLabel.setAlignment(Pos.CENTER); 
        authorLabel.setFont(new Font(16));
        authorLabel.setTextFill(Color.web("770737"));
        //date label
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        String formattedDate = formatter.format(comment.getPostedOn());
        Label dateLabel = new Label(formattedDate);
        
        details.getChildren().addAll(authorLabel, dateLabel);
        details.setPadding(new javafx.geometry.Insets(5, 0, 0, 0));
        //profile picture
        
            UserProfile user = new FileHandler().getSpecificUser(comment.getAuthor());
            ImageView profilePic = new ImageView();
            if(user.getProfilepicture()!= null){

                    String imagePath = user.getProfilepicture();
                    Image image = new Image(new File(imagePath).toURI().toString());

                profilePic.setImage(image);
            }
            else{

                    profilePic.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
                }
                profilePic.setFitWidth(60);
                profilePic.setFitHeight(60);
                Circle clip = new Circle(30,30,30);
                profilePic.setClip(clip);

                authorBox.getChildren().addAll(profilePic, details);
                  
            VBox descriptionBox = new VBox();
            Text descriptionText = new Text(comment.getDescription());
            descriptionText.setWrappingWidth(400);
            descriptionBox.getChildren().add(descriptionText);
            descriptionBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        HBox interactions = new HBox(2);
        Button heartButton = new Button("❤" +comment.getNumberOfLikes());
        // Set the initial style class for the heart button
        heartButton.getStyleClass().add("heart-button");
        if(comment.getLikedBy().contains(currentUser.getUsername())){
            heartButton.getStyleClass().add("clicked");
        }
        
        // Add an event handler to toggle the clicked class
        heartButton.setOnAction(e -> {
        //likesCount++;
        int i = currentPost.findCommentIndex(comment);
        if (heartButton.getStyleClass().contains("clicked")) {
            heartButton.getStyleClass().remove("clicked"); // Remove the clicked class
            comment.unlike(currentUser.getUsername());
        } else {
            heartButton.getStyleClass().add("clicked"); // Add the clicked class
            comment.like(currentUser.getUsername());
        }
        currentPost.replaceCommentAtIndex(i, comment);
        updateInFile();
        heartButton.setText("❤ " +comment.getNumberOfLikes());
        });
        interactions.getChildren().addAll( heartButton);
        commentBox.getChildren().addAll(authorBox ,descriptionBox, interactions );
        commentBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
                                                    BorderStrokeStyle.SOLID, 
                                                    null, 
                                                    new BorderWidths(1))));
        commentBox.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        commentBox.prefWidthProperty().bind(box.widthProperty());
        commentBox.setStyle("-fx-background-color: white;");
        return commentBox;
    }
    @FXML
private void submitComment() {
    if(commenttext.getText().isEmpty()||commenttext.getText()==null){
        System.out.println("error");
        showErrorAlert("Cant post an empty comment ");} 
    else{
    
       currentPost.addComment(currentUser.getUsername(), commenttext.getText());
    
    // Create a comment box for the newly added comment and add it to the VBox
    Comment newComment = currentPost.getComments().getLast(); // Get the last added comment
    VBox newCommentBox = CreateCommentBox(newComment);
    box.getChildren().add(newCommentBox);
    
    updateInFile();
    // Clear the comment text field after submission
    commenttext.clear();
  }
}

private void updateInFile(){
    UserProfile postAuthor= new FileHandler().getSpecificUser(currentPost.getAuthor());
    int index= postAuthor.findPostIndex(currentPost);
    f.updatePostInFile(currentPost, postAuthor, index);
    f.reload();
}
 private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
