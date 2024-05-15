/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socialapp;

/**
 *
 * @author FatmaALZahraa
 */
import java.io.Serializable;

public class Register implements Serializable {

    private String name;
    private String username;
    private String password;
    private String email;
    private String answer; // New field for answer
    private FileHandler file = new FileHandler();

    public Register() {
    }

    public Register(String name, String username, String password, String email, String answer) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.answer = answer;
    }

    // Validation and registration method
    public UserProfile validateAndWriteToFile() throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }

        // Additional validation checks...

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters long and can contain only letters, digits, and underscore");
        }
     if (!this.password.matches("^(?=.*\\d)(?=\\S+$).{8,}$")) {
    // Password does not meet the criteria
    // Handle the error or provide feedback to the user
}


        if (existsInFile(username, email)) {
            throw new IllegalArgumentException("Username or email already exists");
        }
        UserProfile newUser = new UserProfile(username, password, email, answer, name); // Pass answer to UserProfile constructor
        file.addUser(newUser);
        return newUser;
    }

    private boolean existsInFile(String username, String email) {
        return file.AlreadyRegistered(email, username);
        // false if Email-password combination does not exist in the file
    }
}
