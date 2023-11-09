//Author: Ian Hamilton - 15003706
package administration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ian Hamilton - 15003706
 */
@SuppressWarnings("serial")
public class TeachersPanel extends JPanel {

    /**
     * Constructor - Uses the query ResultSet and creates panels dynamically
     * @param conn
     * @param sqlResults 
     */
    public TeachersPanel(Connection conn, ResultSet sqlResults) {
        int[] arrayClasses = new int[3];
        //-SQL to get classes
        String sqlSelectTeacherClass = "SELECT class_ID "
                + "FROM ClassTeacher "
                + "WHERE teacher_ID = ?";

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        try {
            //--Get the teachers information
            while (sqlResults.next()) {
                int userID = sqlResults.getInt("user_ID");
                String name = sqlResults.getString("Name");
                String email = sqlResults.getString("Email");

                //--Execute query
                PreparedStatement pstmt = conn.prepareStatement(sqlSelectTeacherClass);
                pstmt.setInt(1, userID);
                ResultSet rsC = pstmt.executeQuery();
                
                //--Set the classes of the teacher
                int i = 0;
                while (rsC.next()) {
                    arrayClasses[i] = rsC.getInt("class_ID");
                    i++;
                }

                //-Create a dynamic row with the information
                TeacherResultRow rowPanel = new TeacherResultRow(userID, name, email,arrayClasses[0],arrayClasses[1],arrayClasses[2]);
                add(rowPanel); //Add the row to the result panel

                //-Increment the count of results
                DatabaseSearch.incrementResultCount();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }
}
