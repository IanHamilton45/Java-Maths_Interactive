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
public class FlaggedContentPanel extends JPanel {

    /**
     * Constructor - Represents a panel for all forum content information
     * which is added to the JScrollPane of results
     * @param sqlThreadResults
     * @param sqlPostsResults 
     */
    public FlaggedContentPanel(ResultSet sqlThreadResults, ResultSet sqlPostsResults) {
        //--Set layout to vertical boxes
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        try {            
            //--Get the flagged forum thread information
            while (sqlThreadResults.next()) {
                int SRuserID = sqlThreadResults.getInt("user_ID");
                int threadID = sqlThreadResults.getInt("thread_ID");
                String SRName = sqlThreadResults.getString("Name");
                String SRTitle = sqlThreadResults.getString("title");
                String SRContent = sqlThreadResults.getString("content");

                //-Create a dynamic row with the information
                FlaggedContentRow rowPanel = new FlaggedContentRow(0, threadID, SRuserID, SRName, SRTitle, SRContent);
                add(rowPanel); //Add the row to the result panel
                
                //-Increment the count of results
                ManageForum.incrementResultCount();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception (Thread): " + ex.getMessage());
        }
        
        try {
            //--Get the flagged forum post information
            while (sqlPostsResults.next()) {
                int SRuserID = sqlPostsResults.getInt("user_ID");
                int postID = sqlPostsResults.getInt("post_ID");
                String SRName = sqlPostsResults.getString("Name");
                String SRLinkedTitle = sqlPostsResults.getString("title");
                String SRMessage = sqlPostsResults.getString("message");

                //-Create a dynamic row with the information
                FlaggedContentRow rowPanel = new FlaggedContentRow(1, postID, SRuserID, SRName, SRLinkedTitle, SRMessage);
                add(rowPanel); //Add the row to the result panel
                
                //-Increment the count of results
                ManageForum.incrementResultCount();
            }
        }catch (SQLException ex) {
            System.out.println("SQL Exception (Post): " + ex.getMessage());
        }
    }
}
