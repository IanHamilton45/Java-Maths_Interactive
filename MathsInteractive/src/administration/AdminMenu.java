//Author: Ian Hamilton - 15003706
package administration;

import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main menu for an Administrator user
 *
 * @author Ian Hamilton - 15003706
 */
public class AdminMenu extends javax.swing.JFrame {

    int userID, numUsers, numSuspendedUsers, numFlaggedContent;
    String name;
    String userLevel = "ADMIN";

    /**
     * Constructor
     *
     * @param userID
     */
    public AdminMenu(int userID) {
        initComponents(); //Create and display components
        this.userID = userID;
        gatherUserDetails(); //Query DB for logged in user details
        displayUserDetails(); //Display logged in user details

        gatherSummaryDetails(); //Query DB for summary details
        displaySummaryDetails(); //Display sumary details
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblUserDetails = new javax.swing.JLabel();
        lblUserLevel = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnUserRegistration = new javax.swing.JButton();
        btnManageUsers = new javax.swing.JButton();
        btnManageForum = new javax.swing.JButton();
        btnDatabaseSearch = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        lblSummary = new javax.swing.JLabel();
        lblTotalUsers = new javax.swing.JLabel();
        lblTotalUsersSuspended = new javax.swing.JLabel();
        lblTotalFlaggedForum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin Menu");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(179, 218, 255));

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

        btnUserRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUserRegistration.setForeground(new java.awt.Color(5, 24, 42));
        btnUserRegistration.setText("User Registration");
        btnUserRegistration.setPreferredSize(new java.awt.Dimension(228, 35));
        btnUserRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserRegistrationActionPerformed(evt);
            }
        });

        btnManageUsers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnManageUsers.setForeground(new java.awt.Color(5, 24, 42));
        btnManageUsers.setText("Manage Users");
        btnManageUsers.setPreferredSize(new java.awt.Dimension(170, 35));
        btnManageUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageUsersActionPerformed(evt);
            }
        });

        btnManageForum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnManageForum.setForeground(new java.awt.Color(5, 24, 42));
        btnManageForum.setText("Manage Forum");
        btnManageForum.setPreferredSize(new java.awt.Dimension(170, 35));
        btnManageForum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageForumActionPerformed(evt);
            }
        });

        btnDatabaseSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDatabaseSearch.setForeground(new java.awt.Color(5, 24, 42));
        btnDatabaseSearch.setText("Database Search");
        btnDatabaseSearch.setPreferredSize(new java.awt.Dimension(170, 35));
        btnDatabaseSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatabaseSearchActionPerformed(evt);
            }
        });

        lblSummary.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSummary.setForeground(new java.awt.Color(5, 24, 42));
        lblSummary.setText("Summary");

        lblTotalUsers.setText("Total users registered:");

        lblTotalUsersSuspended.setText("Total users suspended:");

        lblTotalFlaggedForum.setText("Forum items to review:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserDetails)
                            .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout)
                        .addGap(29, 29, 29))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnManageUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDatabaseSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnManageForum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUserRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSummary))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalUsersSuspended, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalFlaggedForum, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUserRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageForum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDatabaseSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalUsersSuspended)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalFlaggedForum)
                .addGap(46, 46, 46)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Populates the bottom panel with summary details
     */
    private void displaySummaryDetails() {
        lblTotalUsers.setText("Total users registered: " + this.numUsers);
        lblTotalUsersSuspended.setText("Total users suspended: " + this.numSuspendedUsers);
        lblTotalFlaggedForum.setText("Forum items to review: " + this.numFlaggedContent);
    }

    /**
     * Query the database for a summary of information
     */
    private void gatherSummaryDetails() {
        //-SQL for number of users
        String sqlNumUsers = "SELECT COUNT(user_ID) AS totalUsers FROM LoginCredentials";
        //-SQL for number of suspended users
        String sqlNumSuspended = "SELECT COUNT(user_ID) AS totalSuspended FROM LoginCredentials WHERE date(SuspendedUntil) > date('now')";
        //-SQL for number of flagged threads
        String sqlNumFlaggedThread = "SELECT COUNT(thread_ID) as countFlaggedThread FROM ForumThread WHERE flagged = 1";
        //-SQL for number of flagged thread posts
        String sqlNumFlaggedPost = "SELECT COUNT(post_ID) as countFlaggedPost FROM ThreadPosts WHERE flagged = 1";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //--Get Num Of Users
            Statement stmt1 = conn.createStatement();
            ResultSet resultNumUsers = stmt1.executeQuery(sqlNumUsers);
            while (resultNumUsers.next()) {
                this.numUsers = resultNumUsers.getInt("totalUsers");
            }

            //--Get Num Suspended Users
            ResultSet resultNumSuspended = stmt1.executeQuery(sqlNumSuspended);
            while (resultNumSuspended.next()) {
                this.numSuspendedUsers = resultNumSuspended.getInt("totalSuspended");
            }

            //--Get Num Flagged Forum Thread Content
            ResultSet resultNumFlaggedT = stmt1.executeQuery(sqlNumFlaggedThread);
            while (resultNumFlaggedT.next()) {
                this.numFlaggedContent = resultNumFlaggedT.getInt("countFlaggedThread");
            }

            //--Get Num Flagged Forum Post Content
            ResultSet resultNumFlaggedP = stmt1.executeQuery(sqlNumFlaggedPost);
            while (resultNumFlaggedP.next()) {
                this.numFlaggedContent = this.numFlaggedContent + resultNumFlaggedP.getInt("countFlaggedPost");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Gather logged-in user details based off logged-in userID
     */
    private void gatherUserDetails() {
        //-SQL for FName LName of logged in user
        String sqlUserDetails = "SELECT FName, LName FROM UserDetails WHERE user_ID = ?";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //--Get the FName and LName of user
            PreparedStatement pstmt = conn.prepareStatement(sqlUserDetails);
            pstmt.setInt(1, userID);
            ResultSet userDetailsResults = pstmt.executeQuery();

            while (userDetailsResults.next()) {
                String FName = userDetailsResults.getString("FName");
                String LName = userDetailsResults.getString("LName");
                this.name = (FName + " " + LName); //Assign to 'name'
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Populate top panel with logged-in user details
     */
    private void displayUserDetails() {
        lblUserDetails.setText(name + " (" + Integer.toString(userID) + ")");
        lblUserLevel.setText(userLevel);
    }

    /**
     * Action-event when the user clicks logout
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
     * Action-event when the user clicks the user registration menu option
     *
     * @param evt
     */
    private void btnUserRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserRegistrationActionPerformed
        //--Open the User Registration window centre-screen
        UserRegistration RegistrationForm = new UserRegistration(this.userID, this.name);
        RegistrationForm.setLocationRelativeTo(null);
        RegistrationForm.setVisible(true);
    }//GEN-LAST:event_btnUserRegistrationActionPerformed

    /**
     * Action-event when the user clicks the manage users option
     *
     * @param evt
     */
    private void btnManageUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUsersActionPerformed
        //--Open the Manage Users window centre-screen
        ManageUsers ManageUsersForm = new ManageUsers(this.userID, this.name);
        ManageUsersForm.setLocationRelativeTo(null);
        ManageUsersForm.setVisible(true);
    }//GEN-LAST:event_btnManageUsersActionPerformed

    /**
     * Action-event when the user clicks the database search option
     *
     * @param evt
     */
    private void btnDatabaseSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatabaseSearchActionPerformed
        //--Open the Database Search window centre-screen
        DatabaseSearch DBSearchForm = new DatabaseSearch(this.userID, this.name);
        DBSearchForm.setLocationRelativeTo(null);
        DBSearchForm.setVisible(true);
    }//GEN-LAST:event_btnDatabaseSearchActionPerformed

    /**
     * Action-event when the user clicks the manage forum option
     *
     * @param evt
     */
    private void btnManageForumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageForumActionPerformed
        //--Open the Manage Forum window centre-screen
        ManageForum ManageForumForm = new ManageForum(this.userID, this.name);
        ManageForumForm.setLocationRelativeTo(null);
        ManageForumForm.setVisible(true);
    }//GEN-LAST:event_btnManageForumActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--Set the SeaGlass look and feel
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.getMessage();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            AdminMenu adminFrame = new AdminMenu(1);
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatabaseSearch;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageForum;
    private javax.swing.JButton btnManageUsers;
    private javax.swing.JButton btnUserRegistration;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSummary;
    private javax.swing.JLabel lblTotalFlaggedForum;
    private javax.swing.JLabel lblTotalUsers;
    private javax.swing.JLabel lblTotalUsersSuspended;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    // End of variables declaration//GEN-END:variables
}
