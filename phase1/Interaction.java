package com.mycompany.phase1;
/**
 *
 * @author Aya Yasser
 */

import java.util.ArrayList;
import java.util.Date;

public abstract class Interaction {
    private String author;
    private String description;
    private Date postedOn;
    private int numberOfLikes;
    private ArrayList<String> LikedBy= new ArrayList<String>();
    
    public Interaction(String author, String description){
        this.author = author;
        this.description= description;
        postedOn = new Date();
    }
    public Interaction(String  author){
        this.author = author;
        postedOn = new Date();
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public ArrayList<String> getLikedBy() {
        return LikedBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void like(String username){
        this.numberOfLikes++;
        LikedBy.add(username);
    }
    
}
