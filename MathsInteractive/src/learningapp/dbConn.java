//Author: Jonathan Oliver
package learningapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jonathanoliver
 */

public class dbConn {
    
    public static Connection connectToDB(){
        String url = "jdbc:sqlite:Database.db";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        } 
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "failed to connect to db", "connection failed", JOptionPane.PLAIN_MESSAGE);
        }
        return conn;
    }
    
}
    
    

