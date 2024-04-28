package com.mycompany.phase1;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Register {

	private String  name;
	private String  username;
	private String  password;
	private String  email;
	private String  phonenumber;
	
	public Register() { }
	public Register( String  name ,String  username , String  password,String  email ,String  phonenumber ) {
	this . name = name;
	this . username = username;
	this .password=password;
	this .email =email;
	this .phonenumber=phonenumber;
	
	/*register takes all parameters */
	
	}
	public void validateAndWriteToFile(String filePath) throws IllegalArgumentException {
	    if (name == null || name.isEmpty()) {
	        throw new IllegalArgumentException("Name cannot be empty");
	    }
	    if (username == null || username.isEmpty()) {
	        throw new IllegalArgumentException("Username cannot be empty");
	    }
	    if (password == null || password.isEmpty()) {
	        throw new IllegalArgumentException("Password cannot be empty");
	    }
	    if (phonenumber == null || phonenumber.isEmpty()) {
	        throw new IllegalArgumentException("Phone number cannot be empty");
	    }
	    if (email == null || email.isEmpty()) {
	        throw new IllegalArgumentException("Email cannot be empty");
	    }
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        throw new IllegalArgumentException("Invalid email address");
	    }
	    if (!phonenumber.matches("^[0-9]{10}$")) {
	        throw new IllegalArgumentException("Invalid phone number");
	    } 
	    if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
	        throw new IllegalArgumentException("Username must be between 3 and 20 characters long and can contain only letters, digits, and underscore");
	    }
	    if (!password.matches("^(?=.*\\d)(?=\\S+$).{8,}$")) {
	        throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace");
	    }
	    if (existsInFile(username, email, filePath)) {
	        throw new IllegalArgumentException("Username or email already exists");
	    }

	    try {
	        FileWriter writer = new FileWriter(filePath, true); // append mode
	        String userData = String.join(",", name, username, password, email, phonenumber);
	        writer.write(userData + "\n");
	        writer.close();
	        System.out.println("Registration successful!");
	    } catch (IOException e) {
	        System.out.println("Error writing user data to file: " + e.getMessage());
	    }
	}

	private boolean existsInFile(String targetUsername, String targetEmail, String filePath) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length >= 2 && (parts[1].equals(targetUsername) || parts[3].equals(targetEmail))) {
	                return true;
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading from file: " + e.getMessage());
	    }
	    return false;
	}}
