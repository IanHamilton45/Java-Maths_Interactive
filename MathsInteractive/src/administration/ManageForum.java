//Author: Ian Hamilton - 15003706
package administration;

import java.awt.Window;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class ManageForum extends javax.swing.JFrame {

    private final int userID;
    private final String name;
    private static int numResults = 0;

    public ManageForum(int userID, String name) {
        initComponents();
        this.userID = userID;
        this.name = name;
        displayUserDetails();

        listFlaggedContent();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblUserDetails = new javax.swing.JLabel();
        lblUserLevel = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblList = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        scrollPane = new javax.swing.JScrollPane();
        lblNoContent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Forum");
        setMaximumSize(new java.awt.Dimension(719, 637));
        setMinimumSize(new java.awt.Dimension(719, 637));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(179, 218, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(719, 637));
        jPanel2.setMinimumSize(new java.awt.Dimension(719, 637));

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(5, 24, 42));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Additional/logo.png"))); // NOI18N
        lblLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblUserDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserDetails.setForeground(new java.awt.Color(5, 24, 42));
        lblUserDetails.setText("FName LName (UserID)");

        lblUserLevel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserLevel.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel.setText("Admin");

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblList.setForeground(new java.awt.Color(5, 24, 42));
        lblList.setText("Flagged Forum Content");

        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(5, 24, 42));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        scrollPane.setForeground(new java.awt.Color(5, 24, 42));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new java.awt.Dimension(480, 260));
        scrollPane.setMinimumSize(new java.awt.Dimension(480, 260));
        scrollPane.setPreferredSize(new java.awt.Dimension(590, 330));

        lblNoContent.setText(" ");
        lblNoContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        scrollPane.setViewportView(lblNoContent);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserDetails)
                    .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(30, 30, 30))
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(73, 73, 73))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblList))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblUserDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserLevel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Function to list the flagged content
     */
    public static void listFlaggedContent() {
        numResults = 0;
        //-SQL to get the flagged thread information
        String sqlThreadSearch = "SELECT FName || ' ' || LName AS Name, ForumThread.thread_ID, UserDetails.user_ID, date_created, title, content "
                + "FROM UserDetails JOIN ForumThread ON (UserDetails.user_ID = ForumThread.user_ID) "
                + "WHERE flagged = 1";
        //-SQL to get the flagged post information
        String sqlPostsSearch = "SELECT FName || ' ' || LName AS Name, ThreadPosts.post_ID, ForumThread.thread_ID, UserDetails.user_ID, ThreadPosts.date_created, ForumThread.title, message "
                + "FROM UserDetails JOIN ThreadPosts ON (UserDetails.user_ID = ThreadPosts.user_ID) "
                + "JOIN ForumThread ON (ThreadPosts.thread_ID= ForumThread.thread_ID) "
                + "WHERE ThreadPosts.flagged = 1";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //--Execute query for threads
            Statement stmt = conn.createStatement();
            ResultSet sqlThreadResults = stmt.executeQuery(sqlThreadSearch);

            //--Execute query for posts
            Statement stmt2 = conn.createStatement();
            ResultSet sqlPostsResults = stmt2.executeQuery(sqlPostsSearch);

            //-Create a panel with result rows of the flagged content
            FlaggedContentPanel sqlResultPanel = new FlaggedContentPanel(sqlThreadResults, sqlPostsResults);
            //-Add the panel to the JScrollPane
            scrollPane.setViewportView(sqlResultPanel);
        } catch (SQLException e) {
            System.out.println("SQL Query Exception");
        }

        //--If there are no flagged content
        if (numResults == 0) {
            lblNoContent.setText("No Flagged Forum Content Found.");
            lblNoContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            scrollPane.setViewportView(lblNoContent);
        }
    }

    /**
     * Populate the top panel with the logged-in users details
     */
    private void displayUserDetails() {
        lblUserDetails.setText(name + " (" + Integer.toString(userID) + ")");
        lblUserLevel.setText("ADMIN");
    }

    /**
     * Increment result count by 1
     */
    public static void incrementResultCount() {
        numResults = numResults + 1;
    }

    /**
     * ActionEvent for when the user clicks back
     *
     * @param evt
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        //--Open the Admin  window centre-screen
        AdminMenu adminMenuFrame = new AdminMenu(this.userID);
        adminMenuFrame.setLocationRelativeTo(null);
        adminMenuFrame.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * ActionEvent for when the user clicks logout
     *
     * @param evt
     */
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        //--Close all current windows
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        //--Open the Login window centre-screen
        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * ActionEvent for when the user clicks refresh
     *
     * @param evt
     */
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        listFlaggedContent();
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--Set the SeaGlass look and feel
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.getMessage();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblLogo;
    private static javax.swing.JLabel lblNoContent;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    private static javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
