//Author: Ian Hamilton - 15003706
package administration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class UserResultPanel extends JPanel {

    /**
     * Constructor
     * @param sqlResults ResultSet of DB query
     */
    public UserResultPanel(ResultSet sqlResults) {
        //--Set layout to vertical boxes
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);      
        
        try {
            //--Get the user information
            while (sqlResults.next()) {
                int SRuserID = sqlResults.getInt("user_ID");
                String SRName = sqlResults.getString("Name");
                String SREmail = sqlResults.getString("Email");
                String SRSuspendedUntil = sqlResults.getString("SuspendedUntil");
                String SRUserLevel = sqlResults.getString("UserLevel");
                
                //-Create a dynamic row with the information
                UserResultRow rowPanel = new UserResultRow(SRuserID, SRName, SREmail, SRSuspendedUntil, SRUserLevel);
                add(rowPanel); //Add the row to the result panel

                //-Increment the count of results
                ManageUsers.incrementResultCount();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }
}
