//Author: Andrew Southam
package menus;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Andrew
 */
public class databaseconn {
    
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

