//Author: Ian Hamilton - 15003706
package administration;

import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Ian Hamilton 15003706
 */
public class ManageUsers extends javax.swing.JFrame {

    private final int userID;
    private final String name;
    private static int numResults = 0;
    private static int selectedUserID = 0;

    public ManageUsers(int userID, String name) {
        initComponents();
        this.userID = userID;
        this.name = name;
        displayUserDetails();

        search();
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
        jSeparator2 = new javax.swing.JSeparator();
        lblSearch = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        inputSearch = new javax.swing.JTextField();
        scrollPane = new javax.swing.JScrollPane();
        lblNoResults = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnSuspend = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnResetPassword = new javax.swing.JButton();
        lblSelected = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Users");
        setName("manageUsersFrame"); // NOI18N
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

        lblSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(5, 24, 42));
        lblSearch.setText("Search:");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(5, 24, 42));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        inputSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputSearch.setForeground(new java.awt.Color(5, 24, 42));
        inputSearch.setPreferredSize(new java.awt.Dimension(288, 30));
        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSearchKeyPressed(evt);
            }
        });

        scrollPane.setForeground(new java.awt.Color(5, 24, 42));
        scrollPane.setMaximumSize(new java.awt.Dimension(480, 260));
        scrollPane.setMinimumSize(new java.awt.Dimension(480, 260));
        scrollPane.setPreferredSize(new java.awt.Dimension(480, 260));

        lblNoResults.setText(" ");
        lblNoResults.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        scrollPane.setViewportView(lblNoResults);

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(5, 24, 42));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSuspend.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSuspend.setForeground(new java.awt.Color(5, 24, 42));
        btnSuspend.setText("Suspend");
        btnSuspend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuspendActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(5, 24, 42));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnResetPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetPassword.setForeground(new java.awt.Color(5, 24, 42));
        btnResetPassword.setText("Reset Password");
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });

        lblSelected.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelected.setForeground(new java.awt.Color(5, 24, 42));
        lblSelected.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSelected.setText(" ");

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(5, 24, 42));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblSearch)
                .addGap(18, 18, 18)
                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResetPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuspend)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(lblSelected)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnSuspend)
                    .addComponent(btnDelete)
                    .addComponent(btnResetPassword)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Update the top panel with the logged-in user's details
     */
    private void displayUserDetails() {
        lblUserDetails.setText(name + " (" + Integer.toString(userID) + ")");
        lblUserLevel.setText("ADMIN");
    }

    /**
     * Search the database for the entered search criteria and populate the
     * JScrollPane
     */
    private void search() {
        numResults = 0;
        //-Trim the input search parameter
        String searchParam = inputSearch.getText().trim();
        //-SQL to get user details
        String sqlSearch = "SELECT LoginCredentials.user_ID, FName || ' ' || LName AS Name, Email, SuspendedUntil, UserLevel "
                + "FROM LoginCredentials JOIN UserDetails ON (LoginCredentials.user_ID = UserDetails.user_ID) "
                + "WHERE Name LIKE '%" + searchParam + "%'";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            Statement stmt = conn.createStatement();
            ResultSet searchResults = stmt.executeQuery(sqlSearch);

            //-Create a panel with result rows of the user details
            UserResultPanel sqlResultPanel = new UserResultPanel(searchResults);
            //-Add the panel to the JScrollPane
            scrollPane.setViewportView(sqlResultPanel);
        } catch (SQLException e) {
            System.out.println("SQL Query Exception");
        }

        //--If there are no users found
        if (numResults == 0) {
            lblNoResults.setText("No Results Found.");
            lblNoResults.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            scrollPane.setViewportView(lblNoResults);
        }
    }

    /**
     * Increment result count by 1
     */
    public static void incrementResultCount() {
        numResults = numResults + 1;
    }

    /**
     * Update the selected user_ID index based off the chosen panel
     *
     * @param selectedID
     */
    public static void updateSelected(String selectedID) {
        selectedUserID = Integer.valueOf(selectedID);
        lblSelected.setText("Selected User ID: " + selectedID);
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
     * Action-event when the user hits enter on the keyboard instead of clicking
     * search
     *
     * @param evt
     */
    private void inputSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyPressed
        //--User has hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }//GEN-LAST:event_inputSearchKeyPressed

    /**
     * Action-event when the user clicks search
     *
     * @param evt
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        search();
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
     * Action-event when the user clicks Edit
     *
     * @param evt
     */
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //--No user is selected
        if (selectedUserID == 0) {
            JOptionPane.showMessageDialog(null, "Select a user.", "No User Selected", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //--Open the Edit User window centre-screen
        EditUser editUserForm = new EditUser(userID, name, selectedUserID);
        editUserForm.setLocationRelativeTo(null);
        editUserForm.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * Action-event when the user clicks Suspend
     *
     * @param evt
     */
    private void btnSuspendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuspendActionPerformed
        //--No user is selected
        if (selectedUserID == 0) {
            JOptionPane.showMessageDialog(null, "Select a user.", "No User Selected", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //--Open the Suspend User window centre-screen
        SuspendUser suspendUserForm = new SuspendUser(selectedUserID);
        suspendUserForm.setLocationRelativeTo(null);
        suspendUserForm.setVisible(true);
    }//GEN-LAST:event_btnSuspendActionPerformed

    /**
     * Action-event when the user clicks delete
     *
     * @param evt
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String userLevel = null;
        //--No user is selected
        if (selectedUserID == 0) {
            JOptionPane.showMessageDialog(null, "Select a user.", "No User Selected", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //--Ask if the user is sure about deleting
        int confirmDialog = JOptionPane.YES_NO_OPTION;
        int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete User", confirmDialog);
        //--User is sure
        if (userOption == JOptionPane.YES_OPTION) {
            try {
                //-Connect to Database
                Connection conn = DBConnectA.connectToDB();
                //-SQL to get the user level of selected user
                String sqlUserLevel = "SELECT UserLevel FROM LoginCredentials WHERE user_ID = ?";

                //--Execute Query
                PreparedStatement pstmt = conn.prepareStatement(sqlUserLevel);
                pstmt.setInt(1, selectedUserID);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    userLevel = rs.getString("UserLevel").toUpperCase();
                }

                //--Handle different user levels
                switch (userLevel) {
                    case "TEACHER": //DELETING A TEACHER
                        handleTeacher(selectedUserID);
                        break;
                    case "STUDENT": //DELETING A STUDENT
                        handleStudent(conn, selectedUserID);
                        break;
                    default: //DELETING AN ADMIN OR CONTENT CREATOR
                        deleteLoginCredentials(conn, selectedUserID);
                        deleteUserDetails(conn, selectedUserID);
                        deleteForumThreads(conn, selectedUserID);
                        deleteForumPosts(conn, selectedUserID);
                        break;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex.getMessage());
            }

            //-Refresh the search results
            search();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * Deletes the student from the database
     *
     * @param conn
     * @param selectedUserID
     */
    private void handleStudent(Connection conn, int selectedUserID) {
        try {
            deleteLoginCredentials(conn, selectedUserID);
            deleteUserDetails(conn, selectedUserID);
            deleteForumThreads(conn, selectedUserID);
            deleteForumPosts(conn, selectedUserID);

            deleteStudentDetails(conn, selectedUserID);
            deleteQuizBuffer(conn, selectedUserID);
            deleteResults(conn, selectedUserID);

            JOptionPane.showMessageDialog(null, "Student Deleted", "Confirmation", JOptionPane.PLAIN_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the number of teachers registered in the database
     *
     * @return num of teachers - 1 (minus selected)
     */
    private int getNumTeachersAvailable() {
        int numTeachersAvailable = 0;
        //-SQL to get number of teachers
        String sqlSelectCountTeachers = "SELECT count(user_ID) AS count "
                + "FROM LoginCredentials "
                + "WHERE UserLevel = 'TEACHER'";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            Statement countStmt = conn.createStatement();
            ResultSet countRs = countStmt.executeQuery(sqlSelectCountTeachers);

            while (countRs.next()) {
                numTeachersAvailable = countRs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //-Return the number of teachers minus teacher being deleted
        return numTeachersAvailable - 1;
    }

    /**
     * Creates a 'replace teacher' window that handles the deletion of a teacher
     *
     * @param selectedUserID
     */
    private void handleTeacher(int selectedUserID) {
        //--Is there a replacement teacher available
        if (getNumTeachersAvailable() == 0) {
            JOptionPane.showMessageDialog(null, "No replacement teacher available", "Error", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //--Open the Replace Teacher window centre-screen
        ReplaceTeacher replaceTeacherFrame = new ReplaceTeacher(selectedUserID);
        replaceTeacherFrame.setLocationRelativeTo(null);
        replaceTeacherFrame.setVisible(true);
    }

    /**
     * Delete the user from the Results table
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    private void deleteResults(Connection conn, int selectedUserID) throws SQLException {
        String sqlDelete = "DELETE FROM Results WHERE student_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Delete threads on the forum relating to the user
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    public static void deleteForumThreads(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete forum threads of the user
        String sqlDelete = "DELETE FROM ForumThread WHERE user_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Delete Forum Posts from the database relating to the deleted user
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    public static void deleteForumPosts(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete forum posts by the user
        String sqlDelete = "DELETE FROM ThreadPosts WHERE user_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    private void deleteQuizBuffer(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete the student's quiz buffer
        String sqlDelete = "DELETE FROM QuizBuffer WHERE student_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Delete student details from the database
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    private void deleteStudentDetails(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete student's details
        String sqlDelete = "DELETE FROM StudentDetails WHERE student_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Delete general user details from the database
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    public static void deleteUserDetails(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete user details
        String sqlDelete = "DELETE FROM UserDetails WHERE user_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Delete user from login credentials
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    public static void deleteLoginCredentials(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete user's login credentials
        String sqlDelete = "DELETE FROM LoginCredentials WHERE user_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Action-event when user clicks reset password
     *
     * @param evt
     */
    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        //--Is there a user selected
        if (selectedUserID == 0) {
            JOptionPane.showMessageDialog(null, "Select a user.", "No User Selected", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        String firstName = null, lastName = null, email = null;
        //-SQL to get user's name and email
        String sqlSelect = "SELECT FName, LName, Email FROM UserDetails WHERE user_ID = ?";
        //-SQL to set users new password
        String updateSQL = ("UPDATE LoginCredentials SET passwordHash = ?, NeedNewPassword = 1 "
                + "WHERE user_ID = ?");

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            pstmt.setInt(1, selectedUserID);

            ResultSet selectResults = pstmt.executeQuery();

            while (selectResults.next()) {
                firstName = selectResults.getString("FName");
                lastName = selectResults.getString("LName");
                email = selectResults.getString("Email");
            }

            //--Generate new password hash
            String newPassword = (firstName + Integer.toString(selectedUserID));
            String newPasswordHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            //--Execute update
            PreparedStatement updateStatement = conn.prepareStatement(updateSQL);
            updateStatement.setString(1, newPasswordHash);
            updateStatement.setInt(2, selectedUserID);

            updateStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Password Reset", "Confirmation", JOptionPane.PLAIN_MESSAGE);

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Update Failed", "SQL Failure", JOptionPane.PLAIN_MESSAGE);
        }

        //--Mail the user their new password
        try {
            String newPassword = (firstName + Integer.toString(selectedUserID));
            Mail newMail = new Mail(1, selectedUserID, firstName, lastName, newPassword, email);

            JOptionPane.showMessageDialog(null, "A new password has been emailed to the user.", "Confirmation", JOptionPane.PLAIN_MESSAGE);
            this.setVisible(false);
            this.dispose();
        } catch (MessagingException e) {
            //--The email failed to send, tell the admin the details
            String msg = ("Email failed. The username is: " + Integer.toString(selectedUserID) + ". The Password is: " + (firstName + Integer.toString(selectedUserID)) + ".");
            JOptionPane.showMessageDialog(null, msg, "Email Failed", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_btnResetPasswordActionPerformed

    /**
     * Action-event when the user clicks back
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
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSuspend;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNoResults;
    private javax.swing.JLabel lblSearch;
    private static javax.swing.JLabel lblSelected;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
