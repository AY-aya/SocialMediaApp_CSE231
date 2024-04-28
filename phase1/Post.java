
package com.mycompany.phase1;

import java.awt.Image;
import java.util.ArrayList;

public class Post extends Interaction{
    private ArrayList<Image> pics;
    private ArrayList<Comment> comments=new ArrayList<Comment>();
    private Integer commentcount;
    private static Integer postIDcounter;
    private Integer postID;
    
    public Post(String author, String description,ArrayList<Image> pics){
        super(author,description);
        this.pics= pics;
        postIDcounter++; 
        this.postID=postIDcounter;
    }
    public Post(String author,ArrayList<Image> pics){
        super(author);
        this.pics= pics;
        postIDcounter++;
        this.postID=postIDcounter;
    }
     
      public Post(String author, String description){
        super(author,description); 
        postIDcounter++;
        this.postID=postIDcounter;
    }
     
      
    public Integer getpostID(){
     return postID;
    }

    public ArrayList<Image> getPics() {
        return pics;
    }

    public void setPics(ArrayList<Image> pics) {
        this.pics = pics;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
    
    public Integer getCommentcount() {
        return commentcount;
    }

    public void AddComment(String user, String description ) {
        Comment x=new Comment(user,description,this.postID);
        comments.add(x);
        this.commentcount++;
    }
    
    public void sharepost(userprofile user){
       // user.share(this);
    }
    
}