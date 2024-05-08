
package com.mycompany.phase1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Aya Yasser
 */
public class Phase1 {

    public static void main(String[] args) {
        
        FileHandler file = new FileHandler();
        
        /** testing Registeration:
        
        Register reg= new Register("AYA YASSER","ayaUSERNAME","my1234PASS","ayaY@gmail.com");
        UserProfile newUser= reg.validateAndWriteToFile();
        System.out.println(newUser);
        **********/
        
        /** login test
        Login log= new Login("my1234PASS","ayaUSERNAME");
        UserProfile user= log.validate();
        System.out.println(user);
        **********/
        
        // test for login/posts/comments:
        /*
        Login log= new Login("my1234PASS","ayaUSERNAME");
        ArrayList<Post> posts= new ArrayList<>();
        
        UserProfile user1= log.validate();
        
        user1.createpost(user1.getUsername(), "this is my first post");
        user1.createpost(user1.getUsername(), "this is my second post");
        posts = user1.getPosts();
        
        file.storePostsInFile(posts);
        
        Post post1 = file.getSpecificPost(1); //load post1 and display
        System.out.println(post1);
        Post post2 = file.getSpecificPost(2); //load post2 and display
        System.out.println(post2);
        post2.addComment(user1.getUsername(), "this is a comment"); //add a comment to post2
        file.editPost(post2);          //save the edited post
        post2 = file.getSpecificPost(2); //display the updated post2 from the file
        System.out.println(post2);
        *****/
    }
    
    
    
}
