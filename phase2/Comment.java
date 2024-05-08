package com.mycompany.phase1;

import java.io.Serializable;

public class Comment extends Interaction implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer AssociatedPostID;
    
    Comment(String author, String description, Integer postID){
        super(author, description);
        this.AssociatedPostID = postID;      
    }

    public Integer getAssociatedPost() {
        return AssociatedPostID;
    }

    @Override
    public String toString() {
        return "Comment:" + "AssociatedPostID=" + AssociatedPostID +" "+ this.getAuthor()+" commented: "+this.getDescription();
    }
    
    
    
}