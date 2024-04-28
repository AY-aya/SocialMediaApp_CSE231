package com.mycompany.phase1;
import java.awt.Image;
import java.util.ArrayList;


 class userprofile{
	private String bio;
	private String username;
	private String password;
	private String email;
	private ArrayList<userprofile> friendlist;
	private Image profilepicture;
	public int friendcounter=0;
	
	// constructor for only username,password, email to be used in registeration and login.
	public userprofile(String username, String password, String email) {
				this.username=username;
                this.password=password;
                this.email=email;
	}
	// constructor for all attributes
        public userprofile(String username, String password, String email,String bio, Image profilepicture) {
        	this.username=username;
            this.password=password;
            this.email=email;
          	this.bio=bio;
            this.profilepicture=profilepicture;
	}
        // constructor for only username ,password,email,bio
        public userprofile(String username, String password, String email,String bio) {
			this.username=username;
            this.password=password;
            this.email=email;
            this.bio=bio;
}
        // constructor for only username,password,email,profilepicture
        public userprofile(String username, String password, String email,Image profilepicture) {
			this.username=username;
            this.password=password;
            this.email=email;
            this.profilepicture= profilepicture;
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
        
       public void addFriend(userprofile friend){
           if (!friendlist.contains(friend)){
               friendlist.add(friend); }      
           		friendcounter++;
        }
       
       public void removeFriend(userprofile friend){
           if (friendlist.contains(friend)){    
            friendlist.remove(friend);
            friendcounter--;
            }
        }
       
       public void viewFriendList() {
    	    for (userprofile friend : friendlist) {
    	        System.out.println( friend.getUsername() + friend.getBio());
    	    }
       }       
}