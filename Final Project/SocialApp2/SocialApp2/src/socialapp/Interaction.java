package socialapp;
/**
 *
 * @author Aya Yasser
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Interaction implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String author;
    private String description;
    private Date postedOn;
    private int numberOfLikes;
    private ArrayList<String> LikedBy= new ArrayList<>();
    
    public Interaction(){
        
    }
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

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
    
    public void like(String username){
        this.numberOfLikes++;
        LikedBy.add(username);
    }
    public void unlike(String username){
        this.numberOfLikes--;
        LikedBy.remove(username);
    }
    
}
