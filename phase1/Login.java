package com.mycompany.phase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author FatmaALZahraa
 */
public class Login{
    private String  username;
    private String  password;
    private String  email;
    private static Map<String, String> userCredentials; // Store username as key and password as value
    private static Map<String, String> userEmails; // Store email as key and username as value

    //constructors
    public Login() { }
    public Login( String  username , String  password,String  email  ) {
        this . password = password;
        this . username = username;
        this .email =email;
    }

    //methods
    public void validate() throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
          if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters long and can contain only letters, digits, and underscore");
        }
         if (!password.matches("^(?=.*\\d)(?=\\S+$).{8,}$")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace");
        }

    }

    // Method to load user credentials from a file
    private static void loadUserCredentialsFromFile(String filePath) {
        userCredentials = new HashMap<>();
        userEmails = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String username = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    userCredentials.put(username, password);
                    userEmails.put(email, username);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to check if login is valid
    public static boolean isValidLogin(String filePath, String usernameOrEmail, String password) {
        loadUserCredentialsFromFile(filePath);
        if (userCredentials.containsKey(usernameOrEmail)) {
            String storedPassword = userCredentials.get(usernameOrEmail);
            return storedPassword.equals(password);
        } else if (userEmails.containsKey(usernameOrEmail)) {
            String username = userEmails.get(usernameOrEmail);
            String storedPassword = userCredentials.get(username);
            return storedPassword.equals(password);
        }
        return false;
    } 
}
