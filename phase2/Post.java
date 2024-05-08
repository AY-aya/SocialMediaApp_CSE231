
package com.mycompany.phase1;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Post extends Interaction implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Image> pics;
    private ArrayList<Comment> comments=new ArrayList<>();
    private Integer commentcount=0;
    private static Integer postIDcounter=0;
    private Integer postID;
    private int originalPostID=0;
    
    public Post(){
        
    }
    
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
    
    //constructors for shared posts
    public Post(String author, String description, int SharedPostID){
        super(author, description);
        this.originalPostID = SharedPostID;
    }
    public Post(String author, int SharedPostID){
        super(author);
        this.originalPostID = SharedPostID;
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
//////////////////////////////////
    public static Integer getPostIDcounter() {
        return postIDcounter;
    }

    public Integer getPostID() {
        return postID;
    }

    public int getOriginalPostID() {
        return originalPostID;
    }

    ///////////////
    public void addComment(String user, String description ) {
        Comment x=new Comment(user,description,this.postID);
        comments.add(x);
        this.commentcount++;
    }
    
    //share post method
    public Post sharePost(UserProfile user, String text){
       Post sharedPost= new Post(user.getUsername(),text, this.postID);
       user.share(sharedPost);
       return sharedPost;
    }

    @Override
    public String toString() {
        return "Post{ author= "+this.getAuthor()+" description= "+this.getDescription() + "  pics=" +pics+ ", comments=" + comments + ", commentcount=" + commentcount + ", postID=" + postID + ", originalPostID=" + originalPostID + '}';
    }

    
    
    
    
    
}