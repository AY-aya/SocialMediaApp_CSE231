
package socialapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Post extends Interaction implements Serializable, Comparable<Post>{
    private static final long serialVersionUID = 1L;
    
    private ArrayList<String> pics;
    private ArrayList<Comment> comments=new ArrayList<>();
    private Integer commentcount=0;
    private static Integer postIDcounter=0;
    private Integer postID;
    private int originalPostID=0;
    private Post originalPost;
    
    public Post(){
        
    }
    
    public Post(String author, String description,ArrayList<String> pics){
        super(author,description);
        this.pics= pics;
        postIDcounter++; 
        this.postID=postIDcounter;
        
    }
    public Post(String author,ArrayList<String> pics){
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

    public ArrayList<String> getPics() {
        return pics;
    }

    public void setPics(ArrayList<String> pics) {
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
    public Post sharePost(UserProfile user, String text, Post postToshare){
       if(postToshare.getOriginalPost() != null) {
    	   postToshare= postToshare.getOriginalPost();
       }
    	Post sharedPost= new Post(user.getUsername(),text, postToshare.getpostID());
       sharedPost.setOriginalPost(postToshare);
       user.share(sharedPost);
       return sharedPost;
    }

    @Override
    public String toString() {
        return "Post{ author= "+this.getAuthor()+" description= "+this.getDescription() + "  pics=" +pics+"likes count= "+this.getNumberOfLikes()+" comments=" + comments + ", commentcount=" + commentcount + ", postID=" + postID + ", originalPostID=" + originalPostID + '}';
    }

    public void setOriginalPost(Post originalPost) {
        this.originalPost = originalPost;
    }

    public Post getOriginalPost() {
        return originalPost;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(this.getDescription() != null){
            if(this.getDescription().equals(((Post)obj).getDescription()) && this.getAuthor().equals(((Post)obj).getAuthor()))
                return true;
            else
                return false;
        }
        else if(this.getAuthor().equals(((Post)obj).getAuthor()) && this.getPics().equals(((Post)obj).getPics()))
            return true;
            else
                return false;
    }

    public int findCommentIndex(Comment comm) {
        for (int i = 0; i < comments.size(); i++) {
            //System.out.println(posts.get(i).equals(post));
            if (comments.get(i).equals(comm)) {
                System.out.println(i);
                return i;
            }
        }
        return -1; // Post not found
    }
    // Method to replace a post at a specific index in the user's posts array
    public void replaceCommentAtIndex(int index, Comment comm) {
        if (index >= 0 && index < comments.size()) {
            comments.set(index, comm);
            System.out.println(comments.get(index));
        } else {
            System.out.println("Invalid index");
        }
    }

	@Override
	public int compareTo(Post o) {
		return this.getPostedOn().compareTo(o.getPostedOn());
	}
    
    
    
    
}