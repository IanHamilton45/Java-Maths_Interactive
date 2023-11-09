//Author: Ian Hamilton - 15003706
package administration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class DBConnectA {

    /**
     * Connect to Database
     *
     * @return database Connection
     */
    public static Connection connectToDB() {
        String url = "jdbc:sqlite:Database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connected to DB", "Connection Failed", JOptionPane.PLAIN_MESSAGE);
        }
        return conn;
    }
}
