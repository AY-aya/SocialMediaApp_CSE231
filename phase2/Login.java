package com.mycompany.phase1;


public class Login {
      private String password;
      private String email;
      private String username;
      private FileHandler file = new FileHandler();

      public Login() {
      }

      public Login(String password, String username) {
          this.password = password;
          this.username= username;
      }

      public UserProfile validate() throws IllegalArgumentException {
          if (username == null || username.isEmpty()) {
              throw new IllegalArgumentException("Email cannot be empty");
          }

          if (password == null || password.isEmpty()) {
              throw new IllegalArgumentException("Password cannot be empty");
          }

          if (!ValidatePassword()) {
              throw new IllegalArgumentException("Email or password doesn't exist");
          }

          return file.getSpecificUser(username);
      }

      private boolean ValidatePassword() {
          UserProfile user = file.getSpecificUser(username);
          if(user == null){
              return false;
          }
          else if(user.getUsername().equals(username) && user.getPassword().equals(password)){
              return true;
          }
          return false;
          //false if Email-password combination does not exist in the file
      }
}