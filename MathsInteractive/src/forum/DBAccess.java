//Author: Johnathan Evans
package forum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DBAccess.java - A class that handles all the database connections
 * @author Johnathan Evans
 * @version 1.4
 */
public class DBAccess {
    
    private String dbFile = "Database.db";
    private Connection connection = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public DBAccess() {
       
    }
    
    /**
     * Opens a new connection to the database file
     */
    private void getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Cleanly closes the resultset, statement and database objects.
     */
    private void closeConnection() {
        try { 
            rs.close(); 
        } catch (Exception e) {}
        try { 
            stmt.close(); 
        } catch (Exception e) {}
        try { 
            connection.close(); 
        } catch (Exception e) {}
    }
    
    /**
     * Collects forum thread from the database and creates a new ForumThread object to contain the data.
     * @param threadId The thread id to collect from the database
     * @return ForumThread object containing the data retrieved
     */
    public ForumThread getThread(int threadId) {
        ForumThread thread = null;
        try {
            getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery("SELECT thread_ID, ForumThread.user_ID, FName, LName, title, content, date_created, flagged FROM ForumThread JOIN UserDetails ON ForumThread.user_ID = UserDetails.user_ID WHERE thread_ID = " + threadId);
            if(rs.next()) {
                thread = new ForumThread(rs.getInt("thread_ID"), rs.getInt("user_ID"), rs.getString("FName"), rs.getString("LName"), rs.getString("title"), rs.getString("content"), rs.getString("date_created"), rs.getInt("flagged"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return thread;
    }
    
    public ArrayList<ForumThread> getThreads() {
        ArrayList<ForumThread> threads = new ArrayList();
        String sql = "SELECT thread_ID, ForumThread.user_ID, FName, LName, title, content, date_created, flagged FROM ForumThread JOIN UserDetails ON ForumThread.user_ID = UserDetails.user_ID";
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                threads.add(new ForumThread(rs.getInt("thread_ID"), rs.getInt("user_ID"), rs.getString("FName"), rs.getString("LName"), rs.getString("title"), rs.getString("content"), rs.getString("date_created"), rs.getInt("flagged")));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return threads;
    }
    
    public ArrayList<ForumPost> getThreadPosts(int thread) {
        ArrayList<ForumPost> posts = new ArrayList();
        String sql = "SELECT post_ID, thread_ID, ThreadPosts.user_ID, FName, LName, message, date_created, flagged FROM ThreadPosts JOIN UserDetails ON ThreadPosts.user_ID = UserDetails.user_ID WHERE thread_ID = ?";
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, thread);
            rs = stmt.executeQuery();
            while(rs.next()) {
                posts.add(new ForumPost(rs.getInt("post_ID"),rs.getInt("thread_ID"), rs.getInt("user_ID"), rs.getString("FName"), rs.getString("LName"), rs.getString("message"), rs.getString("date_created"), rs.getInt("flagged")));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return posts;
    }
    
    /**
     * Collects forum post from the database and creates a new ForumPost object to contain the data.
     * @param postId The post id to collect from the database
     * @return ForumPost object containing the data retrieved
     */
    public ForumPost getPost(int postId) {
        ForumPost post = null;
        try {
            getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT post_ID, thread_ID, ThreadPosts.user_ID, message, date_created, flagged, FName, LName FROM ThreadPosts JOIN UserDetails ON ThreadPosts.user_ID = UserDetails.user_ID WHERE post_ID = " + postId);
            if(rs.next()) {
                post = new ForumPost(rs.getInt("post_ID"), rs.getInt("thread_ID"), rs.getInt("user_ID"), rs.getString("FName"), rs.getString("LName"), rs.getString("message"), rs.getString("date_created"), rs.getInt("flagged"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return post;
    }
    
    /**
     * Uploads a new thread to the database
     * @param userId The user id of the user that created the thread
     * @param title The title content of the thread
     * @param content The main content of the thread
     */
    public void uploadThread(int userId, String title, String content) {
        String sql = "INSERT INTO ForumThread(user_ID, title, content) VALUES(?,?,?)";
        
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, title);
            stmt.setString(3, content);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void updateThread(int threadId, String title, String content) {
        String sql = "UPDATE ForumThread SET title = ?, content = ? WHERE thread_ID = ?";
        
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, threadId);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void uploadPost(int threadId, int userId, String message) {
        String sql = "INSERT INTO ThreadPosts(thread_ID, user_ID, message) VALUES(?,?,?)";
        
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, threadId);
            stmt.setInt(2, userId);
            stmt.setString(3, message);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void updatePost(String message, int postId) {
        String sql = "UPDATE ThreadPosts SET message = ? WHERE post_ID = ?";
        
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, message);
            stmt.setInt(2, postId);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void reportPost(int id) {
        String sql = "UPDATE ThreadPosts SET flagged = 1 WHERE post_ID = ?";
        
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void approvePost(int id) {
        String sql = "UPDATE ThreadPosts SET flagged = 0 WHERE post_ID = ?";
        
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void approveThread(int id) {
        String sql = "UPDATE ForumThread SET flagged = 0 WHERE thread_ID = ?";
        
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void reportThread(int id) {
        String sql = "UPDATE ForumThread SET flagged = 1 WHERE thread_ID = ?";
        
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void removePost(int id) {
        String sql = "DELETE FROM ThreadPosts WHERE post_ID = ?";
        
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public void removeThread(int id) {
        String sql = "DELETE FROM ForumThread WHERE thread_ID = ?";
        
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    public int countPosts(int id) {
        int numPosts = 0;
        String sql = "SELECT COUNT(*) FROM ThreadPosts WHERE thread_ID = ?";
        try{
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                numPosts = rs.getInt(1);
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return numPosts;
    }
    
    public ForumUser getUser(int userId) {
        ForumUser user = null;
        String sql = "SELECT UserDetails.user_ID, FName, LName, UserLevel FROM UserDetails JOIN LoginCredentials ON UserDetails.user_ID = LoginCredentials.user_ID WHERE UserDetails.user_ID = ?";
        try {
            getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                user = new ForumUser(rs.getInt("user_ID"), rs.getString("FName"), rs.getString("LName"), rs.getString("UserLevel"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }
}
