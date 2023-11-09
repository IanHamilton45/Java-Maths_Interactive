//Author: Johnathan Evans
package forum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * ForumThread.java - A class for holding the information of a forum thread as an object
 * @author Johnathan Evans
 * @version 1.2
 */
public class ForumThread {
    private int threadId;
    private String title;
    private String content;
    private int userId;
    private String firstName;
    private String lastName;
    private String date;
    private int flagged;
    
    /**
     *
     * @param threadId the database id of the thread
     * @param userId the database id of the user that created the thread
     * @param firstName the first name of the user that created the thread
     * @param lastName the last name of the user that created the thread
     * @param title the title of the thread
     * @param content the content of the thread
     * @param date the date that the thread was made
     * @param flagged the flagged value of the thread
     */
    public ForumThread(int threadId, int userId, String firstName, String lastName, String title, String content, String date, int flagged) {
        this.threadId = threadId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.content = content;
        this.date = date;
        this.flagged = flagged;
    }
    
    /**
     * Retrieves the thread id
     * @return int, thread id
     */
    public int getThreadId() {
        return threadId;
    }
    
    /**
     * Retrieves the thread title
     * @return String, thread title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Retrieves the thread content
     * @return String, thread content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Retrieves the thread user id
     * @return int, the thread user id
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * Retrieves the thread users first name
     * @return String, the thread users first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Retrieves the thread users last name
     * @return String, the threads users last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Retrieves the thread users full name
     * @return String, thread users full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Retrieves the threads flagged int, 0 = not flagged, 1 = flagged.
     * @return int, the threads flagged
     */
    public int getFlagged() {
        return flagged;
    }
    
    /**
     * Retrieves the threads creation date in a readable format.
     * @return String, the threads creation date
     */
    public String getDate() {
        DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //Format of the timestamp from the database
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("dd MMM yy HH:mm"); //New format for the timestamp to be converted too
        LocalDateTime newDate = LocalDateTime.parse(date, oldPattern); //New LocalDateTime object is created to convert the timestamp into the new format
        return newDate.format(newPattern); //Returns a string of the date in the new format
    }
    
}
