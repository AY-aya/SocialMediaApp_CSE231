package socialapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author FatmaALZahraa
 */
public class Login {
    private String password;
    private String email;
    private String username;
    private FileHandler file = new FileHandler();

    public Login() {
    }

    // Constructor for email or username login
    public Login(String password, String identifier) {
        this.password = password;
        if (identifier.contains("@")) {
            this.email = identifier;
        } else {
            this.username = identifier;
        }
    }

    public UserProfile validate() throws InvalidLoginException {
        if ((email == null || email.isEmpty()) && (username == null || username.isEmpty())) {
            throw new IllegalArgumentException("Email or username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (!validatePassword()) {
            throw new InvalidLoginException("Invalid email or username and password combination");
        }

        // Get the user profile based on the identifier
        if (email != null) {
            return file.getUserByEmail(email);
        } else {
            return file.getSpecificUser(username);
        }
    }

    private boolean validatePassword() {
        if (email != null) {
            UserProfile user = file.getUserByEmail(email);
            return user != null && user.getPassword().equals(password);
        } else {
            UserProfile user = file.getSpecificUser(username);
            return user != null && user.getPassword().equals(password);
        }
    }
}
