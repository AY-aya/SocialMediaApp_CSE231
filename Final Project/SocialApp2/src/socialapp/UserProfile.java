package socialapp;
import socialapp.Post;
import java.io.Serializable;
import java.util.ArrayList;



 class UserProfile implements Serializable{
     private static final long serialVersionUID = 1L;
     
	private String bio;
	private String username;
        private String name;
	private String password;
	private String email;
         private String answer; // New field for answer

   
	private ArrayList<String> friendlist; //= new ArrayList<>();;
        private ArrayList<Post> posts=new ArrayList<>();
	private String profilepicture;
	public int friendcounter=0;
        
        
        public UserProfile(){};//empty constructor used in serialization//empty constructor used in serialization//empty constructor used in serialization//empty constructor used in serialization
	// constructor for only username,password, email to be used in registeration and login.
        public UserProfile(String username, String password, String email, String name) {
		this.username=username;
                this.password=password;
                this.email=email;
                this.name=name;
	}
        
	// constructor for all attributes
        public UserProfile(String username, String password, String email,String bio, String profilepicture,String name) {
            this.username=username;
            this.password=password;
            this.email=email;
            this.bio=bio;
            this.profilepicture=profilepicture;
            this.name=name;
	}
         public UserProfile(String username, String password, String email,String answer,String name) {
            this.username=username;
            this.password=password;
            this.email=email;
            this.answer= answer;
            this.name=name;
         }
       public UserProfile(String username, String password, String email, String bio, String profilepicture, String name, String answer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.profilepicture = profilepicture;
        this.name = name;
        this.answer = answer;
    }

    // Getter and setter methods for the answer field
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Other getter and setter methods...


 
        // constructor for only username,password,email,profilepicture
     

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
        
        String getProfilepicture(){
            return profilepicture;
        }
        
        void setBio(String bio){
            this.bio=bio;
        }
        
        void setProfilepicture(String profilepicture){
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
           if(friendlist == null){
               friendlist= new ArrayList<>();
           }
           if (!friendlist.contains(friend)){
               friendlist.add(friend);       
               friendcounter++;}
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
       
       public void createpost(String author, String description, ArrayList<String> pics) {
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
       
    //to save post updates form the gui
    // Method to find the index of a specific post in the user's posts array
    public int findPostIndex(Post post) {
        for (int i = 0; i < posts.size(); i++) {
            //System.out.println(posts.get(i).equals(post));
            if (posts.get(i).equals(post)) {
                System.out.println(i);
                return i;
            }
        }
        return -1; // Post not found
    }
    // Method to replace a post at a specific index in the user's posts array
    public void replacePostAtIndex(int index, Post newPost) {
        if (index >= 0 && index < posts.size()) {
            posts.set(index, newPost);
            System.out.println(posts.get(index));
        } else {
            System.out.println("Invalid index");
        }
    }

    

   
 }
 