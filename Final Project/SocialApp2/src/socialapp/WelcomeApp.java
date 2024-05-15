/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package socialapp;

/**
 *
 * @author FatmaALZahraa
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("WELCOME.fxml"));

        // Create a scene with the loaded FXML content
        Scene scene = new Scene(root);

        // Set the scene in the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("CHIRP LOUNGE"); // Set the title of the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
