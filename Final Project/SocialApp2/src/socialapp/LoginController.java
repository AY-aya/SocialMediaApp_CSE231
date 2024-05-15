/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socialapp;

/**
 *
 * @author FatmaALZahraa
 */
// Adjust the package path as per your project structure

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private Button Button;

    @FXML
    private TextField identifierField;

    @FXML
    private TextField passwordField;
  @FXML
    private AnchorPane scenePane;

    private Stage stage;
    private static UserProfile user1;
    
    @FXML
    ImageView img;
    @FXML
    AnchorPane mainPane;
     @FXML
void initialize() {
    }


    @FXML
    void switchToWelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WELCOME.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void login(ActionEvent event) throws IOException {
        String identifier = identifierField.getText();
        String password = passwordField.getText();

        try {
            user1 = validateLogin(identifier, password);
            gotohome(event);
            //showAlert("Login Successful", "Welcome, " + userProfile.getUsername() + "!");
        } catch (InvalidLoginException e) {
            showAlert("Login Error", e.getMessage());
        } catch (IllegalArgumentException e) {
            showAlert("Login Error", e.getMessage());
        }
    }

    private UserProfile validateLogin(String identifier, String password) throws InvalidLoginException, IllegalArgumentException {
        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalArgumentException("Email or username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        Login login;

        if (identifier.contains("@")) {
            login = new Login(password, identifier);
        } else {
            login = new Login(password, identifier);
        }

        return login.validate();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Signup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ForgetPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgetPassword.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void gotohome (ActionEvent event)throws IOException  {
    	
         
        //  Instantiate the controller
        FeedController f = new FeedController();
        f.setUser(user1, stage);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("feed.fxml"));
        loader.setController(f); // Set the controller instance
        Parent root = loader.load();
     
            
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("Userprofile.fxml"));
        //Parent root = loader.load();
        Scene scene = new Scene(root);
        //double w= scene.getWidth();
        //double h= scene.getHeight();
        // Load the CSS file
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage2 = (Stage)Button.getScene().getWindow();
        //stage2.setResizable(true);
        stage2.setScene(scene);
        //stage2.setWidth(w);
        //stage2.setHeight(h);
        stage2.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create a button
        Button emojiButton = new Button();

        // Set the text of the button to display an emoji icon
        emojiButton.setText("\uD83C\uDFE0");

        // Set the font to a font that supports emoji (e.g., "Segoe UI Emoji")
        emojiButton.setFont(Font.font("Segoe UI Emoji"));

        // Set the size of the button
        emojiButton.setPrefSize(50, 50); // Adjust the size as needed

        // Set the layout position of the button to the upper left corner
        emojiButton.setLayoutX(10);
        emojiButton.setLayoutY(10);

        // Set an action event handler for the button
        emojiButton.setOnAction(event -> {
            try {
                switchToWelcomeScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Add the button to the scene pane
        scenePane.getChildren().add(emojiButton);
        img.fitWidthProperty().bind(mainPane.widthProperty());
        img.fitHeightProperty().bind(mainPane.heightProperty());
        
    }

}

