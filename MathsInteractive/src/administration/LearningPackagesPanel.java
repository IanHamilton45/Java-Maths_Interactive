//Author: Ian Hamilton - 15003706
package administration;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class LearningPackagesPanel extends JPanel {

    /**
     * Constructor - Dynamically creates JPanel with rows of JPanels that have
     * information from the database search results
     *
     * @param sqlResults
     */
    public LearningPackagesPanel(ResultSet sqlResults) {
        //-Set layout to vertical boxes
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        try {
            //--Get the learning pack information
            while (sqlResults.next()) {
                int packID = sqlResults.getInt("pack_ID");
                String packName = sqlResults.getString("Name");
                int isArchived = sqlResults.getInt("archived");
                String quizTitle = sqlResults.getString("title");

                //-Create a dynamic row with the information
                PackageResultRow rowPanel = new PackageResultRow(packID, packName, isArchived, quizTitle);
                add(rowPanel);

                //-Increment the count of results
                DatabaseSearch.incrementResultCount();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }
}
