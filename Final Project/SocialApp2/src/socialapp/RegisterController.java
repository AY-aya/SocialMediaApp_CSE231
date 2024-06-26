/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socialapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class RegisterController {

    @FXML
    private Button signupButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField answerField; // New TextField for the answer

    @FXML
   private AnchorPane scenePane;
    @FXML
    ImageView img;
    @FXML
    AnchorPane mainPane;
    private Stage stage;

    @FXML
    void initialize() {
        
        
        img.fitWidthProperty().bind(mainPane.widthProperty());
        img.fitHeightProperty().bind(mainPane.heightProperty());
        // Create a button
        Button emojiButton = new Button();
        
        emojiButton.setText("\uD83C\uDFE0");
        
        emojiButton.setFont(Font.font("Segoe UI Emoji"));
        // Set the size of the button
        emojiButton.setPrefSize(50, 50); 
        emojiButton.setLayoutX(10);
        emojiButton.setLayoutY(10);
        
        emojiButton.setOnAction(event -> {
            try {
                switchToWelcomeScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // Add the button to the scene pane
        scenePane.getChildren().add(emojiButton);
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
void signup(ActionEvent event) {
    String name = nameField.getText();
    String username = usernameField.getText();
    String password = passwordField.getText();
    String email = emailField.getText();
    String answer = answerField.getText(); 
    Register registration = new Register(name, username, password, email, answer);

    try {
        UserProfile newUser = registration.validateAndWriteToFile();
        showAlert("Registration Successful", "You have successfully registered!\nUsername: " + newUser.getUsername() + "\nEmail: " + newUser.getEmail());
    } catch (IllegalArgumentException e) {
        showAlert("Registration Error", e.getMessage());
    }
}
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
