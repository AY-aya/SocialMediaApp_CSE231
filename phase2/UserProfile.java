package com.mycompany.phase1;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


 class UserProfile implements Serializable{
     private static final long serialVersionUID = 1L;
     
	private String bio;
	private String username;
        private String name;
	private String password;
	private String email;
	private ArrayList<String> friendlist;
        private ArrayList<Post> posts=new ArrayList<>();
	private Image profilepicture;
	public int friendcounter=0;
        
        
        public UserProfile(){};//empty constructor used in serialization
	// constructor for only username,password, email to be used in registeration and login.
        public UserProfile(String username, String password, String email, String name) {
		this.username=username;
                this.password=password;
                this.email=email;
                this.name=name;
	}
        
	// constructor for all attributes
        public UserProfile(String username, String password, String email,String bio, Image profilepicture,String name) {
            this.username=username;
            this.password=password;
            this.email=email;
            this.bio=bio;
            this.profilepicture=profilepicture;
            this.name=name;
	}
         public UserProfile(String username, String password, String email,String bio,String name) {
            this.username=username;
            this.password=password;
            this.email=email;
            this.bio=bio;
            this.name=name;
         }
       
        
        // constructor for only username,password,email,profilepicture
        public UserProfile(String username, String password, String email,Image profilepicture,String name) {
        	this.username=username;
                this.password=password;
                this.email=email;
                this.profilepicture= profilepicture;
                this.name= name;
}

    public ArrayList<Post> getPosts() {
        return posts;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
        
        String getBio(){
            return bio;
        }
        
        String getUsername(){
            return username;
        }
        
        String getPassword(){
            return password;
        }
        
        String getEmail(){
            return email;
        }
        
        ArrayList getFriends(){
            return friendlist;
        }
        
        Image getProfilepicture(){
            return profilepicture;
        }
        
        void setBio(String bio){
            this.bio=bio;
        }
        
        void setProfilepicture(Image profilepicture){
            this.profilepicture =profilepicture;
        }
        void setUsername(String username){
            this.username=username;
        }
        
        void setEmail(String email){
            this.email=email;
        }
        
        void setPassword(String password){
            this.password=password;
        }
        
       public void addFriend(String friend){
           if (!friendlist.contains(friend)){
               friendlist.add(friend); }      
               friendcounter++;
        }
       
       public void removeFriend(String friend){
           if (friendlist.contains(friend)){    
            friendlist.remove(friend);
            friendcounter--;
            }
        }
       
       // this method gives the user the choice whether to approve or decline a friend request and will be further implemented in GUI part 
       public void Friendrequest(String username,boolean choice ) {
    	   if (choice==true) {
    		   addFriend(username);
    	   }
    	   else {
    		   System.out.println("friend request is declined");
    	   }
       }
       
       
       public void viewFriendList() {
    	    for (String friend : friendlist) {
    	        System.out.println(friend);
    	    }
       } 
       
       public void createpost(String author, String description, ArrayList<Image> pics) {
        Post x=new Post(author,description,pics);
        posts.add(x);
    }
       public void createpost(String author, String description) { //to create post with no pics
        Post x=new Post(author,description);
        posts.add(x);
    }
       
       public void share(Post newpost){
            posts.add(newpost);
       }

       
       //for testing only
    @Override
    public String toString() {
        return "UserProfile{" + "bio=" + bio + ", username=" + username + ", name=" + name + ", password=" + password + ", email=" + email + ", friendlist=" + friendlist + ", posts=" + posts + ", profilepicture=" + profilepicture + ", friendcounter=" + friendcounter + '}';
    }
       
       
 }