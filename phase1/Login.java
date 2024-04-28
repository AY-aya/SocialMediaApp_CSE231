package com.mycompany.phase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Login {
    private String password;
    private String email;

    public Login() {
    }

    public Login(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public void validate(String filePath) throws IllegalArgumentException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        try {
            if (!existsInFile(email, password, filePath)) {
                throw new IllegalArgumentException("Email or password doesn't exist");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private boolean existsInFile(String targetEmail, String targetPassword, String filePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[3].equals(targetEmail) && parts[2].equals(targetPassword)) {
                    return true; // Email-password combination exists in the file
                }
            }
            // If the loop completes without finding a matching combination, log an error message
            System.out.println("Error logging in: Email-password combination does not exist in the file");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false; // Email-password combination does not exist in the file
    }
