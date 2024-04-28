package com.mycompany.phase1;
/**
 *
 * @author Aya Yasser
 */
public class Comment extends Interaction {
    private Integer AssociatedPostID;
    Comment(String author, String description, Integer postID){
        super(author, description);
        this.AssociatedPostID = postID;      
    }

    public Integer getAssociatedPost() {
        return AssociatedPostID;
    }
    
}