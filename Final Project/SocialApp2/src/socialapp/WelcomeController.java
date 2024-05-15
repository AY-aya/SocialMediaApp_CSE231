
package socialapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author FatmaALZahraa
 */
public class WelcomeController implements Initializable {
    @FXML
    ImageView img;
    @FXML
    AnchorPane mainPane;
     public void Signup(ActionEvent event) throws IOException{
       Parent root= FXMLLoader.load(getClass().getResource("SignUp.fxml"));
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setResizable(false);
       stage.setScene(scene);
       stage.show();
       
    }
      public void lOGIN(ActionEvent event) throws IOException{
       Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setResizable(false);
       stage.setScene(scene);
       stage.show();
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        img.fitWidthProperty().bind(mainPane.widthProperty());
        img.fitHeightProperty().bind(mainPane.heightProperty());
    }
}
