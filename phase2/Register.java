package com.mycompany.phase1;


public class Register {

	private String  name;
	private String  username;
	private String  password;
	private String  email;
	private FileHandler file= new FileHandler();
	
	public Register() { }
	public Register( String  name ,String  username , String  password,String  email ) {
	this . name = name;
	this . username = username;
	this .password=password;
	this .email =email;
	
	
	/*register takes all parameters */
	
	}
        
        //must be used in try-catch with while loop to catch the exceptions
        //if successful returns the new user's profile object
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
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        throw new IllegalArgumentException("Invalid email address");
	    }
	    
	    if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
	        throw new IllegalArgumentException("Username must be between 3 and 20 characters long and can contain only letters, digits, and underscore");
	    }
	    if (!password.matches("^(?=.*\\d)(?=\\S+$).{8,}$")) {
	        throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace");
	    }
	    if (existsInFile(username, email)) {
	        throw new IllegalArgumentException("Username or email already exists");
	    }
            UserProfile newUser= new UserProfile(username, password, email, name);
            file.addUser(newUser);
            return newUser;
	    
	}

	private boolean existsInFile(String username, String email) {
                return file.AlreadyRegistered(email, username);
                //false if Email-password combination does not exist in the file
        }
}
