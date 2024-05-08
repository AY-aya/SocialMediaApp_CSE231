package com.mycompany.phase1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class FileHandler {
    
    public void storeUsersInFile(ArrayList<UserProfile> users) {
        
        // Serialize the list of User objects to a file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usersfile.ser"))) {
            out.writeObject(users);
            System.out.println("Users saved to file.");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public ArrayList<UserProfile> loadUsersFromFile(){
        ArrayList<UserProfile> loadedUsers = new ArrayList<UserProfile>();
        // Deserialize the list of User objects from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("usersfile.ser"))) {
            loadedUsers = (ArrayList<UserProfile>) in.readObject();
            in.close();
            //System.out.println("Users loaded from file:");
            /*for (UserProfile user : loadedUsers) {
                System.out.println(user.getUsername() + ": " + user.getName() + " (" + user.getEmail() + ")");
            }*/
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }
    
    //function to add a new user
    public void addUser(UserProfile newUser) {
        ArrayList<UserProfile> users = loadUsersFromFile();
        users.add(newUser);
        storeUsersInFile(users);
    }

    //function to find userprofile by username
    public UserProfile getSpecificUser(String username) {
        ArrayList<UserProfile> users = loadUsersFromFile();
        for (UserProfile user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // if user is not found
    }
    
    //can be used in registeration to check if a username is available
    public boolean AlreadyRegistered(String email,String username) {
        ArrayList<UserProfile> users = loadUsersFromFile();
        for (UserProfile user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                return true;
            }
        }
        
        return false; // if user is not found
    }
    
    //function to edit the data of a specific user
    public void editUserProfile(UserProfile updatedUser) {
        ArrayList<UserProfile> users = loadUsersFromFile();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
        storeUsersInFile(users);
    }
    // function to store posts in files 
    public void storePostsInFile(ArrayList<Post> posts) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("postsfile.ser"))) {
            out.writeObject(posts);
            //System.out.println("Posts saved to file.");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
     public ArrayList<Post> loadpostsFromFile(){
        ArrayList<Post> loadedPosts = new ArrayList<Post>();
        // Deserialize the list of post objects from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("postsfile.ser"))) {
            loadedPosts = (ArrayList<Post>) in.readObject();
            /***for testing
            for(Post p: loadedPosts){
                System.out.println(p.getAuthor()+"test2");
            }****/
            in.close();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return loadedPosts;
    }

     public void addPost(Post newPost) {
        ArrayList<Post> posts = loadpostsFromFile();
        posts.add(newPost);
        storePostsInFile(posts);
    }

    public Post getSpecificPost(int postID) {
        ArrayList<Post> posts = loadpostsFromFile();
        for (Post post : posts) {
            if (post.getpostID().equals(postID)) {
                return post;
            }
        }
        return null; // if post is not found
    }
    public void editPost(Post updatedpost) {
         ArrayList<Post> posts = loadpostsFromFile();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getpostID().equals(updatedpost.getpostID())) {
                posts.set(i, updatedpost);
                break;
            }
        }
        storePostsInFile(posts);
    }
   public void deletePost(int postId) {
    ArrayList<Post> posts = loadpostsFromFile();
    for (int i = 0; i < posts.size(); i++) {
        Post post = posts.get(i);
        if (post.getpostID().equals(postId)) {
            posts.remove(i);
            break;
            //i--; // Adjust the index to account for the removed post
        }
    }
    storePostsInFile(posts);
    }
    
}
