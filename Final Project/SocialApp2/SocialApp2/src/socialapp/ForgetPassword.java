
package socialapp;

import java.io.IOException;

public class ForgetPassword {

    private static final FileHandler fileHandler = new FileHandler();
    
// Function to read user data from the file using either email or username
    public static UserProfile readUserData(String identifier) throws IOException {
        if (identifier.contains("@")) {
            return fileHandler.getUserByEmail(identifier);
        } else {
            return fileHandler.getSpecificUser(identifier);
        }
    }

    // Function to update user's password in the system
public static boolean updatePassword(String identifier, String newPassword) {
    if (!newPassword.matches("^(?=.*[A-Z])(?=.*\\d)(?=\\S+$).{8,}$")) {
        throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least one digit.");
    }

    return fileHandler.updatePassword(identifier, newPassword);
  }
}