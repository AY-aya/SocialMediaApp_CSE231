
package socialapp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
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
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Aya Yasser
 */
public abstract class PostDisplayer extends BaseController{
    
    @FXML
    protected VBox box;
    @FXML
    protected ScrollPane scrollPane;
    
    
    public void init(){
        //to display the go to profile button
    	ImageView imageView = new ImageView();
        if(user.getProfilepicture()!= null){
        	
        	String imagePath = user.getProfilepicture();
        	Image image = new Image(new File(imagePath).toURI().toString());

            imageView.setImage(image);
        }
        else{
        	//System.out.println("tewwwwwwwwwwwt");
            imageView.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
        }
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        Circle clip = new Circle(30,30,30);
        imageView.setClip(clip);
        picButton.setGraphic(imageView);
           
        //logout button
        logoutButton.setText("\uD83D\uDEAA");
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
      
        box.setFillWidth(true);
        box.prefHeightProperty().bind(scrollPane.heightProperty());
        box.prefWidthProperty().bind(scrollPane.widthProperty());
        box.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
       
    }
    public VBox createPostBox(Post post) {
        VBox postBox = new VBox(1); // spacing between elements
        System.out.println("222222222222222222");
        System.out.println(user);
        // Display author and profile pic
        HBox authorBox = new HBox(5);
        authorBox.setAlignment(Pos.CENTER_LEFT); 
        
        VBox details= new VBox(2);
        Label authorLabel = new Label(post.getAuthor());
        authorLabel.setAlignment(Pos.CENTER);
        authorLabel.setFont(new Font(16));
        authorLabel.setTextFill(Color.web("770737"));
        //date label
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        String formattedDate = formatter.format(post.getPostedOn());
        Label dateLabel = new Label(formattedDate);
        
        details.getChildren().addAll(authorLabel, dateLabel);
        details.setPadding(new javafx.geometry.Insets(5, 0, 0, 0));
        UserProfile postAuthor= new FileHandler().getSpecificUser(post.getAuthor());
        ImageView profilePic = new ImageView();
      if (postAuthor != null) {
    String profilePicturePath = postAuthor.getProfilepicture();
    Image profileImage;
    if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
        try {
            profileImage = new Image(new File(profilePicturePath).toURI().toString());
        } catch (Exception e) {
            // If there's an error loading the profile picture, load the default picture
            
        	profileImage = new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg"));
            System.err.println("Error loading profile picture: " + e.getMessage());
        }
    } else {
        // If profile picture path is null or empty, load the default picture
        profileImage = new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg"));
    }
    profilePic.setImage(profileImage);
} else {
    // If postAuthor is null, set a default profile picture
    profilePic.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
}
        
        profilePic.setFitWidth(60);
        profilePic.setFitHeight(60);
        // Clip the profile picture into a circle
        Circle clip = new Circle(30,30, 30);
        profilePic.setClip(clip);
        
        authorBox.getChildren().addAll(profilePic, details);
        
        // Display post description
        VBox descriptionBox = new VBox();
        Text descriptionText = new Text(post.getDescription());
        descriptionText.setWrappingWidth(400);
        descriptionBox.getChildren().add(descriptionText);
        descriptionBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        //display the post picture
        ImageView imageView = new ImageView();
        if(post.getPics() != null){
        	String imagePath = post.getPics().getFirst();
        	System.out.println(imagePath);
        	Image image = new Image(new File(imagePath).toURI().toString());
        	System.out.println(image);
        	
        	System.out.println("777777777777666");
        	
            imageView.setImage(image);
            imageView.setFitWidth(300); // adjust width 
            imageView.setFitHeight(300); // adjust height 
        }
        
        //interaction buttons
        HBox interactions = new HBox(2);
        // 1. like
        Button heartButton = new Button("❤" +post.getNumberOfLikes());
        heartButton.getStyleClass().add("heart-button");
        if(post.getLikedBy().contains(user.getUsername())){
            heartButton.getStyleClass().add("clicked");
        }
        heartButton.setOnAction(e -> {
            
            //System.out.println(postAuthor);
            int index= postAuthor.findPostIndex(post);
            if (heartButton.getStyleClass().contains("clicked")) {
                heartButton.getStyleClass().remove("clicked"); // Remove the clicked class
                post.unlike(user.getUsername());
            } else {
                heartButton.getStyleClass().add("clicked"); // Add the clicked class
                post.like(user.getUsername());
            }
            heartButton.setText("❤ " +post.getNumberOfLikes());
            updatePostInFile(post, postAuthor, index);
        });
        
        // 2. comment
        Button commentButton = new Button("💬" + post.getCommentcount()); // You can use any icon or text you prefer
        commentButton.getStyleClass().add("comment-button");
        commentButton.setOnAction(e -> {
            //to flash button when pressed
            commentButton.getStyleClass().add("flash");
            PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
            pause.setOnFinished(event -> commentButton.getStyleClass().remove("flash"));
            pause.play();
            
            //
            //code to handle the comment logic*****************
            System.out.println("testing commmmment");
            openCommentWindow(post);
        });
        
        //3. share
        Button shareButton = new Button( "🔄️");
        shareButton.getStyleClass().add("share-button");
        interactions.getChildren().addAll( heartButton, commentButton, shareButton);
        shareButton.setOnAction(e ->{
            //to flash the button when clicked
            shareButton.getStyleClass().add("flash");
            PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
            pause.setOnFinished(event -> shareButton.getStyleClass().remove("flash"));
            pause.play();
            
            //logic to handle sharing
            System.out.println("testing shareee");
            Switchtosharepost(post, postBox);
        });
        
        // Add author, pic, and description to the postBox
        postBox.getChildren().addAll(authorBox ,descriptionBox, imageView, interactions );
        //postBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        postBox.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        //postBox.prefWidthProperty().bind(box.widthProperty());
        postBox.setStyle("-fx-background-color: white;");
        return postBox;
    }
    
    public VBox displaySharedPost(Post post){
        VBox postBox = new VBox(5); // spacing between elements
        
        // Display author and profile pic
        HBox authorBox = new HBox(5);
        authorBox.setAlignment(Pos.CENTER_LEFT); 
        
        VBox details= new VBox(2);
        Label authorLabel = new Label(post.getAuthor());
        authorLabel.setAlignment(Pos.CENTER); 
        authorLabel.setFont(new Font(16));
        authorLabel.setTextFill(Color.web("770737"));
        //date label
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        String formattedDate = formatter.format(post.getPostedOn());
        Label dateLabel = new Label(formattedDate);
        
        details.getChildren().addAll(authorLabel, dateLabel);
        details.setPadding(new javafx.geometry.Insets(5, 0, 0, 0));
        
        UserProfile postAuthor= new FileHandler().getSpecificUser(post.getAuthor());
        ImageView profilePic = new ImageView();
      if (postAuthor != null) {
    String profilePicturePath = postAuthor.getProfilepicture();
    Image profileImage;
    if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
        try {
            profileImage = new Image(new File(profilePicturePath).toURI().toString());
        } catch (Exception e) {
            // If there's an error loading the profile picture, load the default picture
            
        	profileImage = new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg"));
            System.err.println("Error loading profile picture: " + e.getMessage());
        }
    } else {
        // If profile picture path is null or empty, load the default picture
        profileImage = new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg"));
    }
    profilePic.setImage(profileImage);
} else {
    // If postAuthor is null, set a default profile picture
    profilePic.setImage(new Image(getClass().getResourceAsStream("DefaultProfilePic.jpg")));
}
        
 //
        profilePic.setFitWidth(60);
        profilePic.setFitHeight(60);
        // Clip the profile picture into a circle
        Circle clip = new Circle(30,30, 30);
        profilePic.setClip(clip);
        
        authorBox.getChildren().addAll(profilePic, details);
        //authorBox.setStyle("-fx-border-color: gray gray gray gray; -fx-border-width: 0 0 1 0;");
        
        // Display post description
        VBox descriptionBox = new VBox();
        Text descriptionText = new Text(post.getDescription());
        descriptionText.setWrappingWidth(400);
        descriptionBox.getChildren().add(descriptionText);
        descriptionBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        Post original= post.getOriginalPost();
        VBox originalPostBox= createPostBox(original);
        originalPostBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        
        HBox interactions = new HBox(2);
        // 1. like
        Button heartButton = new Button("❤" +post.getNumberOfLikes());
        heartButton.getStyleClass().add("heart-button");
        if(post.getLikedBy().contains(user.getUsername())){
            heartButton.getStyleClass().add("clicked");
        }
        
        heartButton.setOnAction(e -> {
            
            //System.out.println(postAuthor);
            int index= postAuthor.findPostIndex(post);
            if (heartButton.getStyleClass().contains("clicked")) {
                heartButton.getStyleClass().remove("clicked"); 
                post.unlike(user.getUsername());
            } else {
                heartButton.getStyleClass().add("clicked");
                post.like(user.getUsername());
            }
            heartButton.setText("❤ " +post.getNumberOfLikes());
            updatePostInFile(post, postAuthor, index);
        });
        
        // 2. comment
        Button commentButton = new Button("💬" + post.getCommentcount());
        commentButton.getStyleClass().add("comment-button");
        commentButton.setOnAction(e -> {
            //to flash button when pressed
            commentButton.getStyleClass().add("flash");
            PauseTransition pause = new PauseTransition(Duration.millis(200)); 
            pause.setOnFinished(event -> commentButton.getStyleClass().remove("flash"));
            pause.play();
            
            
            System.out.println("testing commmmment");
            openCommentWindow(post);
        });
        
        //3. share
        Button shareButton = new Button( "🔄️");
        shareButton.getStyleClass().add("share-button");
        interactions.getChildren().addAll( heartButton, commentButton, shareButton);
        shareButton.setOnAction(e ->{
            //to flash the button when clicked
            shareButton.getStyleClass().add("flash");
            PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust the duration as needed
            pause.setOnFinished(event -> shareButton.getStyleClass().remove("flash"));
            pause.play();
            System.out.println("testing shareee");
            Switchtosharepost(post, postBox);
        });
        
        originalPostBox.getChildren().removeLast();
        postBox.getChildren().addAll(authorBox ,descriptionBox ,originalPostBox,interactions );
        postBox.setPadding(new javafx.geometry.Insets(2, 10, 2, 10));
        postBox.setStyle("-fx-background-color: white;");
        return postBox;
    }
    
    public void updatePostInFile(Post post, UserProfile userA, int index){
        //System.out.println(post);
        userA.replacePostAtIndex(index, post);
        new FileHandler().editUserProfile(userA);
        
    }
    
    public void openCommentWindow(Post post) {
     try {
       
        CommentController c = new CommentController();
        
        c.setDetails(post, user, this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("comment.fxml"));
        loader.setController(c);
        Parent x = loader.load();
        Stage commentStage = new Stage();
        commentStage.setResizable(false);
        Scene y = new Scene(x);
        y.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        commentStage.setScene(y);
        commentStage.setTitle("comments");
        commentStage.show();
     } catch (IOException e) {
        e.printStackTrace();
        // Handle any exceptions
     }
  }
    public abstract void reload();

    public void Switchtosharepost(Post p, VBox pBox) {
        try {
            
            Stage currentStage = (Stage) homePage.getScene().getWindow();
            
            SharePostController c = new SharePostController();
            c.setUser(user, p, pBox);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sharepost.fxml"));
            loader.setController(c);
            Parent x = loader.load();
            Scene scene = new Scene(x);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            currentStage.setScene(scene);
            currentStage.setTitle("share post");
            currentStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
          }
        }
     

    
}
