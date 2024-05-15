
package socialapp;

import javafx.event.ActionEvent;
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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class ForgetPasswordController implements Initializable{
    private Stage stage;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField PasswordField;

    
    @FXML
   private AnchorPane scenePane;
    @FXML
    ImageView img;
    @FXML
    AnchorPane mainPane;


    @FXML
    private Label statusLabel;
    
    @FXML
    private TextField answerField;

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
            UserProfile userProfile = ForgetPassword.readUserData(identifier); // Pass email or username and answer
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        img.fitWidthProperty().bind(mainPane.widthProperty());
        img.fitHeightProperty().bind(mainPane.heightProperty());
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
    
    }
}
