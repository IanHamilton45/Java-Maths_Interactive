/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew Ward W15002106
 */
public class DBConnect {

   /**
    * Database Connection Constructor
    */
    public DBConnect() {
    }

    /**
     * Method to connect to database
     * @return connection
     */
    private static Connection connectDB() {
        //database string
        String url = "jdbc:sqlite:Database.db";
        Connection conn = null;
        try {
            //return connection
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to connect to DB", "Connection Failed",
                    JOptionPane.PLAIN_MESSAGE);
        }
        return conn;
    }

}
