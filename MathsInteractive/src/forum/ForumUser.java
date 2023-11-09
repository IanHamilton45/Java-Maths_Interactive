//Author: Johnathan Evans
package forum;

/**
 * ForumUser.java - A class that holds all the data for a forum user
 * @author Johnathan Evans
 * @version 1.0
 */
public class ForumUser {
    private int userID;
    private String firstName;
    private String lastName;
    private String userLevel;
    
    public ForumUser(int userID, String firstname, String lastname, String userlevel) {
        this.userID = userID;
        this.firstName = firstname;
        this.lastName = lastname;
        this.userLevel = userlevel;
    }
    
    public int getUserId() {
        return userID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getUserLevel() {
        return userLevel;
    }
    
}
