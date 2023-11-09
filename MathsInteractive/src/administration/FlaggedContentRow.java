//Author: Ian Hamilton - 15003706
package administration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * FlaggedContentRow is a JPanel type that creates and populates a JPanel with
 * information
 *
 * @author Ian Hamilton - 15003706
 */
public class FlaggedContentRow extends JPanel {

    //--Variables
    private final javax.swing.JButton btnDelete;
    private final javax.swing.JButton btnRemoveFlag;
    private final javax.swing.JPanel jPanel1;
    private final javax.swing.JScrollPane jScrollPane1;
    private final javax.swing.JLabel lblFlagged;
    private final javax.swing.JLabel lblThread;
    private final javax.swing.JLabel lblUserDetails;
    private final javax.swing.JScrollPane scrlThread;
    private final javax.swing.JTextArea txtAreaContent;
    private final javax.swing.JTextArea txtAreaThread;

    private final String userID, Name, title, content;
    int contentType, contentID; //0 If parent thread; 1 If thread post

    /**
     * EventListener for when the user clicks delete on the result row item
     *
     * @param evt
     */
    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {
        //-SQL to delete the forum thread
        String sqlDelThread = "DELETE FROM ForumThread WHERE thread_ID = " + this.contentID;
        //-SQL to delete the posts of the thread
        String sqlDelPosts = "DELETE FROM ThreadPosts WHERE thread_ID = " + this.contentID;
        //-SQL to delete the specific post
        String sqlDelPost = "DELETE FROM ThreadPosts WHERE post_ID = " + this.contentID;

        //--Ask the user if they are sure about deleting
        int confirmDialog = JOptionPane.YES_NO_OPTION;
        int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Information", confirmDialog);

        if (userOption == JOptionPane.YES_OPTION) { //User is sure
            if (contentType == 0) { //Entire Thread
                try {
                    //-Connect to Database
                    Connection conn = DBConnectA.connectToDB();
                    //--Execute update to posts of thread
                    Statement pstmt1 = conn.createStatement();
                    pstmt1.executeUpdate(sqlDelPosts);

                    //--Execute update to thread
                    Statement pstmt2 = conn.createStatement();
                    pstmt2.executeUpdate(sqlDelThread);
                } catch (SQLException ex) {
                    System.out.println("Del thread error: " + ex.getMessage());
                }
            } else { //Specific post
                try {
                    //-Connect to Database
                    Connection conn = DBConnectA.connectToDB();
                    //--Execute update to specific post
                    Statement pstmt3 = conn.createStatement();
                    pstmt3.executeUpdate(sqlDelPost);
                } catch (SQLException ex) {
                    System.out.println("Del specific post error: " + ex.getMessage());
                }
            } //-End content type selection

            //--Ask the user if they wish to suspend the content poster
            int confirmDialog2 = JOptionPane.YES_NO_OPTION;
            int userOption2 = JOptionPane.showConfirmDialog(null, "Additionally, suspend the user?", "Suspend User", confirmDialog2);

            //--Create new Suspension window
            if (userOption2 == JOptionPane.YES_OPTION) { //User wants to suspend user
                System.out.println("User Clicked Suspend User");
                SuspendUser suspendUserForm = new SuspendUser(Integer.valueOf(this.userID));
                suspendUserForm.setLocationRelativeTo(null);
                suspendUserForm.setVisible(true);
            }

            //-Refresh the list of flagged content
            ManageForum.listFlaggedContent();
        }
    }

    /**
     * EventListener for when the user clicks remove flag on the result row item
     *
     * @param evt
     */
    private void btnRemoveFlagMouseClicked(java.awt.event.MouseEvent evt) {
        //-SQL to update the thread flag
        String sqlUpdateThreadFlag = "UPDATE ForumThread SET flagged = 0 "
                + "WHERE thread_ID = " + this.contentID;
        //-SQL to update the post flag
        String sqlUpdatePostFlag = "UPDATE ThreadPosts SET flagged = 0 "
                + "WHERE post_ID = " + this.contentID;

        //--Ask the user if they are sure about unflagging the content
        int confirmDialog = JOptionPane.YES_NO_OPTION;
        int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Information", confirmDialog);

        //--Remove the flag from the content
        if (userOption == JOptionPane.YES_OPTION) { //User is sure
            if (contentType == 0) { //Entire Thread
                try {
                    //-Connect to Database
                    Connection conn = DBConnectA.connectToDB();
                    //--Execute update
                    Statement pstmt2 = conn.createStatement();
                    pstmt2.executeUpdate(sqlUpdateThreadFlag);
                } catch (SQLException ex) {
                    System.out.println("Remove Flag thread error: " + ex.getMessage());
                }
            } else { //Specific post
                try {
                    //-Connect to Database
                    Connection conn = DBConnectA.connectToDB();
                    //--Execute update
                    Statement pstmt3 = conn.createStatement();
                    pstmt3.executeUpdate(sqlUpdatePostFlag);
                } catch (SQLException ex) {
                    System.out.println("Remove Flag post error: " + ex.getMessage());
                }
            }

            //-Refresh the list of flagged content
            ManageForum.listFlaggedContent();
        }
    }

    /**
     * Constructor that creates an individual result row with information passed
     * by the database result
     *
     * @param contentType
     * @param contentID
     * @param user_id
     * @param user_name
     * @param title
     * @param content
     */
    public FlaggedContentRow(int contentType, int contentID, int user_id, String user_name, String title, String content) {
        this.contentID = contentID;
        this.contentType = contentType;
        this.userID = String.valueOf(user_id);
        this.Name = user_name;
        this.title = title;
        this.content = content;

        //--Initialise Panel Contents
        jPanel1 = new javax.swing.JPanel();
        lblUserDetails = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnRemoveFlag = new javax.swing.JButton();
        lblThread = new javax.swing.JLabel();
        lblFlagged = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaContent = new javax.swing.JTextArea();
        scrlThread = new javax.swing.JScrollPane();
        txtAreaThread = new javax.swing.JTextArea();

        //--Main Panel
        jPanel1.setBackground(new java.awt.Color(179, 218, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(590, 212));
        jPanel1.setMinimumSize(new java.awt.Dimension(590, 212));
        jPanel1.setPreferredSize(new java.awt.Dimension(590, 212));

        lblUserDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserDetails.setForeground(new java.awt.Color(5, 24, 42));
        lblUserDetails.setText(this.Name + " (" + this.userID + ")");
        lblUserDetails.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblUserDetails.setPreferredSize(new java.awt.Dimension(320, 22));

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(5, 24, 42));
        btnDelete.setText("Delete Post");
        //--Add event handler for button press
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        btnRemoveFlag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRemoveFlag.setForeground(new java.awt.Color(5, 24, 42));
        btnRemoveFlag.setText("Remove Flag");
        //--Add event handler for button press
        btnRemoveFlag.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoveFlagMouseClicked(evt);
            }
        });

        lblThread.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblThread.setForeground(new java.awt.Color(5, 24, 42));
        lblThread.setText("Thread:");
        lblThread.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblThread.setPreferredSize(new java.awt.Dimension(320, 22));

        lblFlagged.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFlagged.setForeground(new java.awt.Color(5, 24, 42));
        lblFlagged.setText("Flagged:");
        lblFlagged.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblFlagged.setPreferredSize(new java.awt.Dimension(320, 22));

        jScrollPane1.setBackground(new java.awt.Color(179, 218, 255));
        jScrollPane1.setForeground(new java.awt.Color(5, 24, 42));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(298, 150));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(298, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(298, 100));
        jScrollPane1.setRequestFocusEnabled(false);

        txtAreaContent.setBackground(new java.awt.Color(179, 218, 255));
        txtAreaContent.setColumns(20);
        txtAreaContent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAreaContent.setForeground(new java.awt.Color(5, 24, 42));
        txtAreaContent.setLineWrap(true);
        txtAreaContent.setText(this.content);
        txtAreaContent.setWrapStyleWord(true);
        txtAreaContent.setBorder(null);
        txtAreaContent.setMinimumSize(new java.awt.Dimension(235, 66));
        txtAreaContent.setEditable(false);
        jScrollPane1.setViewportView(txtAreaContent);

        scrlThread.setBackground(new java.awt.Color(179, 218, 255));
        scrlThread.setForeground(new java.awt.Color(5, 24, 42));
        scrlThread.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlThread.setMinimumSize(new java.awt.Dimension(242, 66));
        scrlThread.setPreferredSize(new java.awt.Dimension(242, 66));

        txtAreaThread.setBackground(new java.awt.Color(179, 218, 255));
        txtAreaThread.setColumns(20);
        txtAreaThread.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAreaThread.setForeground(new java.awt.Color(5, 24, 42));
        txtAreaThread.setLineWrap(true);
        txtAreaThread.setText(this.title);
        txtAreaThread.setWrapStyleWord(true);
        txtAreaThread.setBorder(null);
        txtAreaThread.setMinimumSize(new java.awt.Dimension(235, 66));
        txtAreaThread.setEditable(false);
        scrlThread.setViewportView(txtAreaThread);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFlagged, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblThread, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrlThread, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                .addComponent(btnRemoveFlag)
                                                .addComponent(btnDelete)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblUserDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblUserDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDelete))
                                .addComponent(scrlThread, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(lblFlagged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemoveFlag))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
        );
    }
}
