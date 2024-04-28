
package com.mycompany.phase1;

/**
 *
 * @author Aya Yasser
 */
public class Phase1 {

    public static void main(String[] args) {
        String usern = "aly649";
        String mail= "zzzzz@gmail.com";
        String pass="yt4#23";
        
        //Login mylogin= new Login(usern, mail, pass);
        System.out.println(new Login(usern, mail, pass).isValidLogin("F:\\uni\\semester 4\\java projects\\phase1\\src\\main\\java\\com\\mycompany\\phase1\\usersFile.txt", mail, pass));
    }
    
    
}
