
package socialapp;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Aya Yasser
 */
public class SocialApp extends Application{

    /**
     * @param args the command line arguments
     */
    private static UserProfile user1;
    public static void main(String[] args) {
        // TODO code application logic here
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    
    // Load the FXML file with the controller instance set
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
    
    Parent root = loader.load();
 
    Scene scene = new Scene(root);
    // Load the CSS file
    scene.getStylesheets().add(getClass().getResource("R&L.css").toExternalForm());
    Image icon = new Image(getClass().getResourceAsStream("3.jpg"));

        // Set the application icon
    stage.getIcons().add(icon);
    stage.setTitle("Chirp Lounge");
    stage.setScene(scene);
    stage.show();
        
    }
    
}
