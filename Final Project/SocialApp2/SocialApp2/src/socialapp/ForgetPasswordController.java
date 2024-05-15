
package socialapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class ForgetPasswordController {
    private Stage stage;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Pane scenePane;

    @FXML
    private Label statusLabel;
    
    @FXML
    private TextField answerField;

    @FXML
    void initialize() {
        // Create a button
        Button emojiButton = new Button();
       
        emojiButton.setText("\uD83C\uDFE0");
        
        emojiButton.setFont(Font.font("Segoe UI Emoji"));
        
        emojiButton.setPrefSize(50, 50); // Adjust the size as needed
        
        emojiButton.setLayoutX(10);
        emojiButton.setLayoutY(10);
        
        emojiButton.setOnAction(event -> {
            try {
                switchToWelcomeScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
    void forgetPassword(ActionEvent event) throws IOException {
        String identifier = emailField.getText();
        String newPassword = newPasswordField.getText();
        String answer = answerField.getText(); // Get the answer from the answer field
        try {
            UserProfile userProfile = new FileHandler().getUserByEmailAndAnswer(identifier, answer); // Pass email or username and answer
            if (userProfile != null) {
                // Update password
                ForgetPassword.updatePassword(userProfile.getUsername(), newPassword);
                showAlert("Password Updated", "Your password has been updated successfully.");
            } else {
                showAlert("User Not Found", "User not found or answer is incorrect.");
            }
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
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

    public void Signup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}