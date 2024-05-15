
package socialapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Aya Yasser
 */
public class Phase1 extends Application {

    public static void main(String[] args) {
        
        FileHandler file = new FileHandler();
        
        /** testing Registeration:
        
        Register reg= new Register("AYA YASSER","ayaUSERNAME","my1234PASS","ayaY@gmail.com");
        UserProfile newUser= reg.validateAndWriteToFile();
        System.out.println(newUser);
        **********/
        
        /** login test
        Login log= new Login("my1234PASS","ayaUSERNAME");
        UserProfile user= log.validate();
        System.out.println(user);
        **********/
        
        // test for login/posts/comments:
        /*
        Login log= new Login("my1234PASS","ayaUSERNAME");
        ArrayList<Post> posts= new ArrayList<>();
        
        UserProfile user1= log.validate();
        
        user1.createpost(user1.getUsername(), "this is my first post");
        user1.createpost(user1.getUsername(), "this is my second post");
        posts = user1.getPosts();
        
        file.storePostsInFile(posts);
        
        Post post1 = file.getSpecificPost(1); //load post1 and display
        System.out.println(post1);
        Post post2 = file.getSpecificPost(2); //load post2 and display
        System.out.println(post2);
        post2.addComment(user1.getUsername(), "this is a comment"); //add a comment to post2
        file.editPost(post2);          //save the edited post
        post2 = file.getSpecificPost(2); //display the updated post2 from the file
        System.out.println(post2);
        *****/
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        File fxmlFile = new File("F:\\uni\\semester 4\\java projects\\phase1\\src\\main\\java\\com\\mycompany\\phase1\\feed.fxml");
           Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());                 
        Scene scene = new Scene(root);
        stage.setTitle("CareConnectClinic");  
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    
}
