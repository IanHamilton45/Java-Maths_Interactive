//Author: Johnathan Evans
package forum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JPanel;

/**
 * ForumPost.java - A class for holding the information of a forum post as an object
 * @author Johnathan Evans
 * @version 1.3
 */
public class ForumPost extends JPanel {
    
    private int postId;
    private int threadId;
    private int userId;
    private String firstName;
    private String lastName;
    private String message;
    private String timestamp;
    private int flagged;
    
    /**
     *
     * @param postId the database id of the post
     * @param threadId the database id of the thread the post is connected too
     * @param userId the database id of the user that created the post
     * @param firstName the first name of the user that created the post
     * @param lastName the last name of the user that created the post
     * @param message the post message content
     * @param timestamp the timestamp of when the post was created
     * @param flagged if the post has been flagged as inappropriate 
     */
    public ForumPost(int postId, int threadId, int userId, String firstName, String lastName, String message, String timestamp, int flagged) {
        this.postId = postId;
        this.threadId = threadId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.timestamp = timestamp;
        this.flagged = flagged;
    }
    
    /**
     * Retrieve the posts id
     * @return int, the post id
     */
    public int getPostId() {
        return postId;
    }
    
    /**
     * Retrieve the posts thread id
     * @return int, the posts thread id
     */
    public int getThreadId() {
        return threadId;
    }
    
    /**
     * Retrieve the posts user id
     * @return int, the posts user id
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * Retrieve the post users first name
     * @return String, the posts users first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Retrieve the post users last name
     * @return String, the posts users last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Retrieve the post users full name
     * @return String, combination of the users first and last name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Retrieve the posts message content
     * @return String, the post content
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Retrieve the flagged value
     * @return int, the flagged value
     */
    public int getFlagged() {
        return flagged;
    }
    
    /**
     * Retrieve the posts creation date converted into readable format
     * @return String, the creation date
     */
    public String getDate() {
        DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //Current pattern of the timestamp
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("dd MMM yy HH:mm"); //New pattern to convert too
        LocalDateTime newDate = LocalDateTime.parse(timestamp, oldPattern); //Creates a LocalDateTime object to convert the string
        return newDate.format(newPattern); //Returns the posts creation date in the new format as a String
    }
}
