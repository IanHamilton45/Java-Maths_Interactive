//Author: Ian Hamilton - 15003706
package administration;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Dynamically added Panel that will then be added to the scroll pane
 * @author Ian Hamilton - 15003706
 */
public class QuizzesPanel extends JPanel {

    /**
     * Constructor - use the db ResultSet and dynamically create 
     * panels with quiz information
     * @param sqlResults 
     */
    public QuizzesPanel(ResultSet sqlResults) {
         //--Set layout to vertical boxes
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        try {
            //--Get the quiz information
            while (sqlResults.next()) {
                int quizID = sqlResults.getInt("quiz_ID");
                String quizTitle = sqlResults.getString("title");
                
                //-Create a dynamic row with the information
                QuizResultRow rowPanel = new QuizResultRow(quizID, quizTitle);
                add(rowPanel);

                //-Increment the count of results
                DatabaseSearch.incrementResultCount();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }
}
